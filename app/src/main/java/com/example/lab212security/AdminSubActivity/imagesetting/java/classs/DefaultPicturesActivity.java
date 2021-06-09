package com.example.lab212security.AdminSubActivity.imagesetting.java.classs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lab212security.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultPicturesActivity extends AppCompatActivity {
    private Integer[] pictureId={R.mipmap.digitalearth,R.mipmap.androidcolorfull,R.mipmap.ironman,R.mipmap.ironman1,R.mipmap.safari,R.mipmap.spiderman,R.mipmap.wild};
    private List<Integer> recyclerImage = new ArrayList<Integer>();

    public SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_pictures);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        preferences = getSharedPreferences("BackgroundImage",MODE_PRIVATE);
        editor = preferences.edit();
        addRecylcerView();
    }

    public void addRecylcerView(){
        for(Integer image: pictureId) {
            recyclerImage.add(image);
        }
        mAdapter = new RecyclerviewImageSetting(recyclerImage);
        recyclerView.setAdapter(mAdapter);
    }

    public void setSharedPreferenceImage(int position){
        editor.putInt("number",position);
        editor.apply();
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

