package com.b.b;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkOverlayPermission();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        checkOverlayPermission();
    }

    private void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
            return;
        }
        checkAccessibilityService();
    }

    private void checkAccessibilityService() {
        if (isAccessibilityServiceEnabled(getPackageName(), MyAccessibilityService.class.getName())) {
            startFloatingButtonService();
            return;
        }
        Toast.makeText(this, "1. Aktifkan 'Auto Klik Sederhana' di Pengaturan Aksesibilitas!", 1).show();
        Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
        startActivity(intent);
    }

    private void startFloatingButtonService() {
        if (FloatingButtonService.isRunning) {
            Toast.makeText(this, "Tombol mengambang sudah aktif!", 0).show();
            finish();
            return;
        }
        startService(new Intent(this, FloatingButtonService.class));
        Toast.makeText(this, "Tombol mengambang dimulai!", 0).show();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (Build.VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(this)) {
                checkAccessibilityService();
                return;
            } else {
                Toast.makeText(this, "Izin Overlay ditolak.", 0).show();
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isAccessibilityServiceEnabled(String pkgName, String serviceName) {
        String settingValue;
        String expectedServiceName = pkgName + "/" + serviceName;
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException e) {
        }
        TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');
        if (accessibilityEnabled == 1 && (settingValue = Settings.Secure.getString(getContentResolver(), "enabled_accessibility_services")) != null) {
            colonSplitter.setString(settingValue);
            while (colonSplitter.hasNext()) {
                String accessibilityService = colonSplitter.next();
                if (accessibilityService.equalsIgnoreCase(expectedServiceName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
