package com.example.bks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class cropAdapter extends RecyclerView.Adapter<cropAdapter.Viewholder> {
    private Context context;
   private List<CropModel> cropModels;

    public cropAdapter(Context context, List<CropModel> cropModels) {
        this.context = context;
        this.cropModels = cropModels;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.crop_view,parent,false);
        return new cropAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        CropModel cropModel= cropModels.get(position);
        holder.cropName.setText(cropModel.getCrop_name());
        Glide.with(context).load(cropModel.getCrop_image()).into(holder.cropImage);
    }

    @Override
    public int getItemCount() {
        return cropModels.size()    ;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView cropName;
        ImageView cropImage;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            cropImage=itemView.findViewById(R.id.Cropimage);
            cropName=itemView.findViewById(R.id.cropName);


        }
    }
}
