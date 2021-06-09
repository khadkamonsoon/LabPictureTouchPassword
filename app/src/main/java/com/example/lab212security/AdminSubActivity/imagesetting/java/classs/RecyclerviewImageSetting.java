package com.example.lab212security.AdminSubActivity.imagesetting.java.classs;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab212security.R;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class RecyclerviewImageSetting extends RecyclerView.Adapter<RecyclerviewImageSetting.MyViewHolder> {
    private List<Integer> receivedImage ;
    private static DefaultPicturesActivity defaultPicturesActivity = new DefaultPicturesActivity();
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_image_setting,parent,false);
        MyViewHolder vh = new MyViewHolder(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.images.setBackgroundResource(receivedImage.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultPicturesActivity.setSharedPreferenceImage(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return receivedImage.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView images;
        public MyViewHolder(View v) {
            super(v);
            images = (ImageView)itemView.findViewById(R.id.images);
        }
    }

    public RecyclerviewImageSetting(List<Integer> receivedImage){
        this.receivedImage = receivedImage;
    }

}
