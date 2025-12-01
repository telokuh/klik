package com.b.b;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.widget.ActivityChooserView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class MyAccessibilityService extends AccessibilityService {
    private static final int MAX_PICKUP_MINUTES = 7;
    private static final String TAG = "MyAccessibilityService";
    private static final String TARGET_PACKAGE = "com.taxsee.driver";
    public static MyAccessibilityService instance;
    public static boolean isServiceRunning = false;
    private Runnable clickRunnable;
    private String[] excludeTexts;
    private String[] includeTexts;
    private Handler handler = new Handler();
    private final long LOOP_DELAY = 1500;
    private int currentClickIndex = 0;
    private List<AccessibilityNodeInfo> allRpNodes = new ArrayList();
    private Set<String> rejectedOrderHashes = new HashSet();
    private boolean isVerifyingScreen = false;
    private String lastClickedHash = null;
    private Random random = new Random();

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
        Log.d(TAG, "Accessibility Service Connected and Instance Set.");
        loadSettings();
        this.rejectedOrderHashes.clear();
    }

    public void clearRejectedIds() {
        this.rejectedOrderHashes.clear();
        Log.i(TAG, "Rejected order hashes cache cleared manually. Session reset.");
    }

    public void loadSettings() {
        SharedPreferences prefs = getSharedPreferences(SettingsActivity.PREFS_NAME, 0);
        String excludeStr = prefs.getString(SettingsActivity.KEY_EXCLUDE, "Indomaret, Alfamart").toLowerCase();
        String includeStr = prefs.getString(SettingsActivity.KEY_INCLUDE, BuildConfig.FLAVOR).toLowerCase();
        String[] rawExclude = excludeStr.split(",");
        String[] rawInclude = includeStr.split(",");
        List<String> cleanedExclude = new ArrayList<>();
        for (String item : rawExclude) {
            String trimmed = item.trim();
            if (!trimmed.isEmpty()) {
                cleanedExclude.add(trimmed);
            }
        }
        List<String> cleanedInclude = new ArrayList<>();
        for (String item2 : rawInclude) {
            String trimmed2 = item2.trim();
            if (!trimmed2.isEmpty()) {
                cleanedInclude.add(trimmed2);
            }
        }
        this.excludeTexts = (String[]) cleanedExclude.toArray(new String[0]);
        this.includeTexts = (String[]) cleanedInclude.toArray(new String[0]);
        Log.i(TAG, "Settings Loaded:");
        Log.i(TAG, "Exclude Array: " + Arrays.toString(this.excludeTexts));
        Log.i(TAG, "Include Array: " + Arrays.toString(this.includeTexts));
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (isServiceRunning && event.getPackageName() != null && event.getPackageName().toString().contains(TARGET_PACKAGE) && this.isVerifyingScreen) {
            int eventType = event.getEventType();
            if (eventType == 2048 || eventType == 32) {
                Runnable runnable = this.clickRunnable;
                if (runnable != null) {
                    this.handler.removeCallbacks(runnable);
                }
                this.handler.postDelayed(new Runnable() { // from class: com.b.b.MyAccessibilityService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MyAccessibilityService.this.verifyAndContinue();
                    }
                }, 500L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verifyAndContinue() {
        if (this.isVerifyingScreen) {
            this.isVerifyingScreen = false;
            AccessibilityNodeInfo rootNode = getRootInActiveWindow();
            if (rootNode == null) {
                startLoopingClicker();
                return;
            }
            String rejectedHash = this.lastClickedHash;
            this.lastClickedHash = null;
            if (rejectedHash == null) {
                Log.e(TAG, "VERIFIKASI GAGAL: Hash order terakhir null. Kembali ke loop.");
                startLoopingClicker();
                return;
            }
            String fullText = getFullNodeText(rootNode).toLowerCase();
            boolean containsExcludeText = false;
            String[] strArr = this.excludeTexts;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String exclude = strArr[i];
                if (TextUtils.isEmpty(exclude) || !fullText.contains(exclude)) {
                    i++;
                } else {
                    containsExcludeText = true;
                    break;
                }
            }
            int pickupTime = extractPickupTime(fullText);
            boolean isPickupTimeTooLong = pickupTime > 7;
            if (containsExcludeText || isPickupTimeTooLong) {
                String reason = containsExcludeText ? "Teks Dihindari" : "Waktu Penjemputan";
                Log.w(TAG, "VERIFIKASI: Pesanan Ditolak! Alasan: " + reason + ". Hash: " + rejectedHash);
                this.rejectedOrderHashes.add(rejectedHash);
                performBack();
            } else {
                Log.i(TAG, "VERIFIKASI: Pesanan Lolos! Waktu penjemputan " + pickupTime + " menit.");
                this.rejectedOrderHashes.clear();
            }
            rootNode.recycle();
        }
    }

    private int extractPickupTime(String fullText) {
        Pattern r = Pattern.compile("(\\d+)\\s*menit");
        Matcher m = r.matcher(fullText);
        if (m.find()) {
            try {
                int time = Integer.parseInt(m.group(1));
                Log.d(TAG, "Extracted Pickup Time: " + time + " minutes.");
                return time;
            } catch (NumberFormatException e) {
                Log.e(TAG, "Error parsing pickup time: " + e.getMessage());
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
        }
        return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
        stopClicker();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        stopClicker();
        isServiceRunning = false;
        instance = null;
        return super.onUnbind(intent);
    }

    public void startClicker() {
        isServiceRunning = true;
        this.currentClickIndex = 0;
        this.rejectedOrderHashes.clear();
        startLoopingClicker();
        Log.d(TAG, "Clicker Started (Loop-based, Blind Click & Verify).");
    }

    public void stopClicker() {
        isServiceRunning = false;
        Runnable runnable = this.clickRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        Log.d(TAG, "Clicker Stopped.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoopingClicker() {
        Runnable runnable = this.clickRunnable;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        this.clickRunnable = new Runnable() { // from class: com.b.b.MyAccessibilityService.2
            @Override // java.lang.Runnable
            public void run() {
                if (MyAccessibilityService.isServiceRunning && !MyAccessibilityService.this.isVerifyingScreen) {
                    MyAccessibilityService.this.performBlindClickAndVerify();
                }
                MyAccessibilityService.this.handler.postDelayed(this, 1500L);
            }
        };
        this.handler.post(this.clickRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performBlindClickAndVerify() {
        AccessibilityNodeInfo clickableParent;
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) {
            return;
        }
        if (this.currentClickIndex >= this.allRpNodes.size()) {
            this.allRpNodes.clear();
            List<AccessibilityNodeInfo> allNodes = new ArrayList<>();
            findNodesRecursive(rootNode, allNodes);
            AccessibilityNodeInfo scrollableContainer = null;
            Iterator<AccessibilityNodeInfo> it = allNodes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AccessibilityNodeInfo node = it.next();
                if (node.isScrollable()) {
                    scrollableContainer = node;
                    break;
                }
            }
            List<AccessibilityNodeInfo> nodesForFiltering = new ArrayList<>();
            if (scrollableContainer != null) {
                for (int i = 0; i < scrollableContainer.getChildCount(); i++) {
                    AccessibilityNodeInfo child = scrollableContainer.getChild(i);
                    if (child != null) {
                        findNodesRecursive(child, nodesForFiltering);
                    }
                }
                Log.d(TAG, "OPTIMASI SCAN: Fokus pada Kontainer Scrollable. Memproses " + nodesForFiltering.size() + " node detail.");
            } else {
                nodesForFiltering = allNodes;
                Log.d(TAG, "SCAN FALLBACK: Tidak ada Kontainer Scrollable. Memproses " + nodesForFiltering.size() + " node penuh.");
            }
            Iterator<AccessibilityNodeInfo> it2 = nodesForFiltering.iterator();
            while (true) {
                int i2 = 0;
                if (!it2.hasNext()) {
                    break;
                }
                AccessibilityNodeInfo node2 = it2.next();
                if (node2.getText() != null && node2.getText().toString().contains("Rp")) {
                    String fullText = getFullNodeText(node2).toLowerCase();
                    boolean passesIncludeCheck = true;
                    String[] strArr = this.includeTexts;
                    if (strArr.length > 0 && !TextUtils.isEmpty(strArr[0].trim())) {
                        passesIncludeCheck = false;
                        String[] strArr2 = this.includeTexts;
                        int length = strArr2.length;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            }
                            String include = strArr2[i2];
                            if (TextUtils.isEmpty(include) || !fullText.contains(include)) {
                                i2++;
                            } else {
                                passesIncludeCheck = true;
                                break;
                            }
                        }
                    }
                    if (passesIncludeCheck && (clickableParent = findClickableParent(node2)) != null) {
                        String currentHash = getFullNodeText(clickableParent);
                        if (this.rejectedOrderHashes.contains(currentHash)) {
                            Log.d(TAG, "Order Dihindari: Hash " + currentHash + " sudah pernah ditolak.");
                        } else {
                            this.allRpNodes.add(clickableParent);
                        }
                    }
                }
            }
            this.currentClickIndex = 0;
            Log.i(TAG, "SCAN BARU: Ditemukan " + this.allRpNodes.size() + " pesanan yang lolos filter INCLUDE & Anti-Ganda.");
            if (this.allRpNodes.isEmpty()) {
                if (!findAndPerformScroll(allNodes)) {
                    Log.w(TAG, "Tidak ada elemen scrollable ditemukan atau sudah di akhir daftar.");
                } else {
                    Log.i(TAG, "Tidak ada pesanan di layar. Mencoba scroll ke bawah.");
                }
            }
        }
        if (this.currentClickIndex < this.allRpNodes.size()) {
            AccessibilityNodeInfo targetNode = this.allRpNodes.get(this.currentClickIndex);
            Rect bounds = new Rect();
            targetNode.getBoundsInScreen(bounds);
            int x = bounds.centerX();
            int y = bounds.centerY();
            String nodeHash = getFullNodeText(targetNode);
            Log.i(TAG, "KLIK BLIND: Klik pesanan #" + (this.currentClickIndex + 1) + ", Hash: " + nodeHash);
            this.lastClickedHash = nodeHash;
            performClick(x, y);
            this.isVerifyingScreen = true;
            this.currentClickIndex = this.currentClickIndex + 1;
            this.handler.removeCallbacks(this.clickRunnable);
        } else {
            Log.d(TAG, "Semua pesanan di layar saat ini sudah di-klik/diverifikasi.");
        }
        rootNode.recycle();
    }

    private void performBack() {
        Log.i(TAG, "AKSI BACK: Menunggu 1 detik lalu kembali ke daftar order.");
        this.handler.postDelayed(new Runnable() { // from class: com.b.b.MyAccessibilityService.3
            @Override // java.lang.Runnable
            public void run() {
                MyAccessibilityService.this.performGlobalAction(1);
                MyAccessibilityService.this.handler.postDelayed(new Runnable() { // from class: com.b.b.MyAccessibilityService.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MyAccessibilityService.this.startLoopingClicker();
                    }
                }, 1000L);
            }
        }, 1000L);
    }

    private boolean findAndPerformScroll(List<AccessibilityNodeInfo> allNodes) {
        AccessibilityNodeInfo scrollableNode = null;
        Iterator<AccessibilityNodeInfo> it = allNodes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AccessibilityNodeInfo node = it.next();
            if (node.isScrollable()) {
                scrollableNode = node;
                break;
            }
        }
        if (scrollableNode != null) {
            boolean scrolled = scrollableNode.performAction(4096);
            return scrolled;
        }
        return false;
    }

    private String getFullNodeText(AccessibilityNodeInfo node) {
        if (node == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        if (node.getText() != null) {
            sb.append(node.getText());
            sb.append(" ");
        }
        if (node.getContentDescription() != null) {
            sb.append(node.getContentDescription());
            sb.append(" ");
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            sb.append(getFullNodeText(node.getChild(i)));
            sb.append(" ");
        }
        return sb.toString().trim().toLowerCase();
    }

    private AccessibilityNodeInfo findClickableParent(AccessibilityNodeInfo node) {
        for (AccessibilityNodeInfo current = node; current != null; current = current.getParent()) {
            if (current.isClickable() && current != getRootInActiveWindow()) {
                return current;
            }
        }
        return null;
    }

    private void findNodesRecursive(AccessibilityNodeInfo node, List<AccessibilityNodeInfo> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        for (int i = 0; i < node.getChildCount(); i++) {
            findNodesRecursive(node.getChild(i), nodes);
        }
    }

    private void performClick(int x, int y) {
        Path clickPath = new Path();
        clickPath.moveTo(x, y);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(clickPath, 0L, 10L));
        dispatchGesture(gestureBuilder.build(), null, null);
    }
}
