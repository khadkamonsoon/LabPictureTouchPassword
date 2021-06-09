package com.example.lab212security.AdminSubActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab212security.AdminActivity;
import com.example.lab212security.R;

public class ChangePassword extends AppCompatActivity {
    private EditText txtBackupPassword;
    private Button btnCheck;
    private String originalBackupPassword;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtBackupPassword.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"please Enter Backup Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(txtBackupPassword.getText().toString().equals(originalBackupPassword)){
                    Intent mintent = new Intent(ChangePassword.this, PasswordSettingActivity.class);
                    startActivity(mintent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sorry wrong backup password ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(){
        preferences = getSharedPreferences("passwordCollection",MODE_PRIVATE);
        originalBackupPassword=preferences.getString("backupPassword","");
        txtBackupPassword=(EditText)findViewById(R.id.txtBackupPassword);
        btnCheck=(Button)findViewById(R.id.btnCheck);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}

