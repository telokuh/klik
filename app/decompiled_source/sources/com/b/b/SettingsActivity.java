package com.b.b;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
/* loaded from: classes2.dex */
public class SettingsActivity extends AppCompatActivity {
    public static final String KEY_EXCLUDE = "excludeText";
    public static final String KEY_INCLUDE = "includeText";
    public static final String PREFS_NAME = "ClickerSettings";
    private Button btnSaveSettings;
    private EditText editExcludeText;
    private EditText editIncludeText;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.editExcludeText = (EditText) findViewById(R.id.edit_exclude_text);
        this.editIncludeText = (EditText) findViewById(R.id.edit_include_text);
        this.btnSaveSettings = (Button) findViewById(R.id.btn_save_settings);
        loadSettings();
        this.btnSaveSettings.setOnClickListener(new View.OnClickListener() { // from class: com.b.b.SettingsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                SettingsActivity.this.saveSettings();
            }
        });
        Button btnClearSession = (Button) findViewById(R.id.btn_clear_session);
        btnClearSession.setOnClickListener(new View.OnClickListener() { // from class: com.b.b.SettingsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (MyAccessibilityService.instance != null) {
                    MyAccessibilityService.instance.clearRejectedIds();
                    Toast.makeText(SettingsActivity.this, "Sesi Order Ditolak telah Dihapus!", 1).show();
                    return;
                }
                Toast.makeText(SettingsActivity.this, "Layanan Clicker Belum Aktif. Cache otomatis kosong.", 1).show();
            }
        });
    }

    private void loadSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        String exclude = prefs.getString(KEY_EXCLUDE, "Indomaret");
        String include = prefs.getString(KEY_INCLUDE, BuildConfig.FLAVOR);
        this.editExcludeText.setText(exclude);
        this.editIncludeText.setText(include);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveSettings() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = prefs.edit();
        String exclude = this.editExcludeText.getText().toString().trim();
        String include = this.editIncludeText.getText().toString().trim();
        editor.putString(KEY_EXCLUDE, exclude);
        editor.putString(KEY_INCLUDE, include);
        editor.apply();
        if (MyAccessibilityService.instance != null) {
            MyAccessibilityService.instance.loadSettings();
        }
        Toast.makeText(this, "Pengaturan berhasil disimpan!", 0).show();
        finish();
    }
}
