package com.example.lab212security;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lab212security.AdminSubActivity.ImageSettingActivity;
import com.example.lab212security.AdminSubActivity.LoginActivity;
import com.example.lab212security.AdminSubActivity.PasswordSettingActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

    }

    public void passwordSetting(View v){
        Intent psIntent = new Intent(getApplicationContext(), PasswordSettingActivity.class);
        startActivity(psIntent);
    }


    public void ImageSetting(View v){
        Intent bsIntent = new Intent(getApplicationContext(), ImageSettingActivity.class);
        startActivity(bsIntent);
    }

    public void login(View v){
        Intent lIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(lIntent);

    }

    public void exit(View v){
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
