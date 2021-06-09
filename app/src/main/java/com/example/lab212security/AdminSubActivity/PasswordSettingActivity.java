package com.example.lab212security.AdminSubActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lab212security.AdminSubActivity.Database.DatabaseHelper;
import com.example.lab212security.R;


public class PasswordSettingActivity extends AppCompatActivity {

    private SharedPreferences preferencesStorePWD,preferencesBgImage,preferencesImageAdd;
    private SharedPreferences.Editor editor;
    private String savePassword="", codepassword="";
    private float preX =0 , preY=0;
    private LinearLayout inputArea;

    private Integer[] pictureId={R.mipmap.digitalearth,R.mipmap.androidcolorfull,R.mipmap.ironman,R.mipmap.ironman1,R.mipmap.safari,R.mipmap.spiderman,R.mipmap.wild};

    private DatabaseHelper myDb;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_setting);

        myDb = new DatabaseHelper(this);
        preferencesStorePWD = getSharedPreferences("MobileSecurity",MODE_PRIVATE);
        preferencesBgImage = getSharedPreferences("BackgroundImage",MODE_PRIVATE);
        preferencesImageAdd = getSharedPreferences("BackgroundImageAdd",MODE_PRIVATE);
        editor = preferencesStorePWD.edit();

        init();

        inputArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
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

                Log.e("TAG",codepassword);
                savePassword = savePassword + codepassword;
                preX = x;
                preY = y;
                return false;

            }
        });
    }

    private void init() {
        int number = preferencesBgImage.getInt("number", 0);
        String uri = preferencesImageAdd.getString("Uri", "");
        inputArea = (LinearLayout) findViewById(R.id.inputArea);
        if (!uri.equals("")) {
            inputArea.setBackgroundResource(Integer.parseInt(uri));
        } else {
            inputArea.setBackgroundResource(pictureId[number]);// add image view to addimage from Uri.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reset();
    }

    public void savingPassword(View v){
        editor.putString("Password",savePassword);
        editor.apply();
        finish();
    }

    @Override
    public void onBackPressed() {

        finish();
    }
    public void resetPassword(View v){
        reset();
    }

    private void reset(){
        preY=0;
        preX=0;
        savePassword="";
    }
}
