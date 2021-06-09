package com.example.lab212security.AdminSubActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab212security.AdminActivity;
import com.example.lab212security.R;
public class LoginActivity extends AppCompatActivity {

    private String passwordList="", codepassword="";
    private float preX =0 , preY=0;
    private RelativeLayout inputArea;
    private TextView numberOfEdit;
    private String numberOfTouch;

    SharedPreferences preferences, preferencesBGImage;
    private Integer[] pictureId={R.mipmap.digitalearth,R.mipmap.androidcolorfull,R.mipmap.ironman,R.mipmap.ironman1,R.mipmap.safari,R.mipmap.spiderman,R.mipmap.wild};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getApplicationContext().getSharedPreferences("MobileSecurity", MODE_PRIVATE);
        preferencesBGImage = getSharedPreferences("BackgroundImage",MODE_PRIVATE);
        init();
        int backgroundImage= preferencesBGImage.getInt("number",0);
        inputArea.setBackgroundResource(pictureId[backgroundImage]);

        inputArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                numberOfTouch = numberOfTouch + "*";
                numberOfEdit.setText(numberOfTouch);
                float x = (int) event.getX();
                float y = (int) event.getY();
                if (preX == 0 && preY == 0) {
                    codepassword = "1";
                } else if (preX < x && preY > y) {
                    codepassword = "1";
                } else if (preX > x && preY > y) {
                    codepassword = "2";
                } else if (preX > x && preY < y) {
                    codepassword = "3";
                } else if (preX < x && preY < y) {
                    codepassword = "4";
                }
                passwordList = passwordList + codepassword;
                preX = x;
                preY = y;
                return false;
            }
        });
    }

    private void init() {
        inputArea = (RelativeLayout) findViewById(R.id.inputArea);
        numberOfEdit = (TextView) findViewById(R.id.numberOfTry);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reset();
    }



    public void login(View v) {
        String savedPassword = preferences.getString("Password", "");
        if (passwordList.equals("")) {
            Toast.makeText(getApplicationContext(), "input password", Toast.LENGTH_SHORT).show();
        }
        if (savedPassword.equals(passwordList)) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "wrong password", Toast.LENGTH_SHORT).show();

        }
        reset();
    }

    public void resetPassword(View v) {
        reset();
    }

    private void reset() {
        preY = 0;
        preX = 0;
        passwordList = "";
        numberOfTouch = "";
        numberOfEdit.setText("");
    }

    @Override
    public void onBackPressed() {
        Intent adIntent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(adIntent);
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
