package com.b.b;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.google.android.material.badge.BadgeDrawable;
/* loaded from: classes2.dex */
public class FloatingButtonService extends Service {
    public static boolean isRunning = false;
    private Button floatingBtnExit;
    private Button floatingBtnSettings;
    private Button floatingBtnToggle;
    private View mFloatingView;
    private WindowManager mWindowManager;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        final WindowManager.LayoutParams params;
        super.onCreate();
        isRunning = true;
        this.mFloatingView = LayoutInflater.from(this).inflate(R.layout.layout_floating_button, (ViewGroup) null);
        if (Build.VERSION.SDK_INT >= 26) {
            params = new WindowManager.LayoutParams(-2, -2, 2038, 8, -3);
        } else {
            params = new WindowManager.LayoutParams(-2, -2, 2002, 8, -3);
        }
        params.gravity = BadgeDrawable.TOP_START;
        params.x = 0;
        params.y = 100;
        this.mWindowManager = (WindowManager) getSystemService("window");
        this.mWindowManager.addView(this.mFloatingView, params);
        this.floatingBtnToggle = (Button) this.mFloatingView.findViewById(R.id.floating_btn_toggle);
        this.floatingBtnExit = (Button) this.mFloatingView.findViewById(R.id.floating_btn_exit);
        this.floatingBtnSettings = (Button) this.mFloatingView.findViewById(R.id.floating_btn_settings);
        this.floatingBtnToggle.setOnClickListener(new View.OnClickListener() { // from class: com.b.b.FloatingButtonService.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MyAccessibilityService.isServiceRunning) {
                    MyAccessibilityService.isServiceRunning = false;
                    if (MyAccessibilityService.instance != null) {
                        MyAccessibilityService.instance.stopClicker();
                    }
                    Toast.makeText(FloatingButtonService.this, "Kliker Berhenti", 0).show();
                } else {
                    MyAccessibilityService.isServiceRunning = true;
                    if (MyAccessibilityService.instance == null) {
                        Toast.makeText(FloatingButtonService.this, "Aksesibilitas belum terhubung!", 1).show();
                    } else {
                        MyAccessibilityService.instance.startClicker();
                    }
                    Toast.makeText(FloatingButtonService.this, "Kliker Dimulai!", 1).show();
                }
                FloatingButtonService.this.updateButtonState();
            }
        });
        this.floatingBtnExit.setOnClickListener(new View.OnClickListener() { // from class: com.b.b.FloatingButtonService.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FloatingButtonService.this.stopSelf();
            }
        });
        this.floatingBtnSettings.setOnClickListener(new View.OnClickListener() { // from class: com.b.b.FloatingButtonService.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent settingsIntent = new Intent(FloatingButtonService.this, SettingsActivity.class);
                settingsIntent.addFlags(268435456);
                FloatingButtonService.this.startActivity(settingsIntent);
            }
        });
        this.mFloatingView.setOnTouchListener(new View.OnTouchListener() { // from class: com.b.b.FloatingButtonService.4
            private float initialTouchX;
            private float initialTouchY;
            private int initialX;
            private int initialY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == 0) {
                    this.initialX = params.x;
                    this.initialY = params.y;
                    this.initialTouchX = event.getRawX();
                    this.initialTouchY = event.getRawY();
                    return true;
                } else if (action == 1) {
                    if (Math.abs(event.getRawX() - this.initialTouchX) < 10.0f && Math.abs(event.getRawY() - this.initialTouchY) < 10.0f) {
                        v.performClick();
                    }
                    FloatingButtonService.this.updateButtonState();
                    return true;
                } else if (action == 2) {
                    params.x = this.initialX + ((int) (event.getRawX() - this.initialTouchX));
                    params.y = this.initialY + ((int) (event.getRawY() - this.initialTouchY));
                    FloatingButtonService.this.mWindowManager.updateViewLayout(FloatingButtonService.this.mFloatingView, params);
                    FloatingButtonService.this.floatingBtnExit.setVisibility(8);
                    return true;
                } else {
                    return false;
                }
            }
        });
        updateButtonState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateButtonState() {
        if (MyAccessibilityService.isServiceRunning) {
            this.floatingBtnToggle.setText("STOP");
            this.floatingBtnToggle.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.red_500));
            this.floatingBtnExit.setVisibility(0);
        } else {
            this.floatingBtnToggle.setText("START");
            this.floatingBtnToggle.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.purple_500));
            this.floatingBtnExit.setVisibility(8);
        }
        this.floatingBtnSettings.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.teal_700));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        View view = this.mFloatingView;
        if (view != null) {
            this.mWindowManager.removeView(view);
            MyAccessibilityService.isServiceRunning = false;
            if (MyAccessibilityService.instance != null) {
                MyAccessibilityService.instance.stopClicker();
            }
        }
    }
}
