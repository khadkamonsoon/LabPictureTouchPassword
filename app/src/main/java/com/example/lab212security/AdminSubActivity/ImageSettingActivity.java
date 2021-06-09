package com.example.lab212security.AdminSubActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.lab212security.AdminActivity;
import com.example.lab212security.AdminSubActivity.imagesetting.java.classs.DefaultPicturesActivity;
import com.example.lab212security.R;

public class ImageSettingActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 1;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_setting);
        preferences = getSharedPreferences("BackgroundImageAdd",MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void defaultPictures(View v){
        Intent dpIntent = new Intent(getApplicationContext(), DefaultPicturesActivity.class);
        startActivity(dpIntent);
    }

    public void gallery(View v){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,SELECT_PICTURE);}

    @Override
    public void onBackPressed() {
        Intent adIntent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(adIntent);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Result code is RESULT_OK only if the user selects an Image
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case SELECT_PICTURE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    editor.putString("Uri",selectedImage.toString());
                    editor.apply();
                    break;
            }
    }
    public String getPath(Uri uri) {
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            Log.e("TAG","null");
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }


}
