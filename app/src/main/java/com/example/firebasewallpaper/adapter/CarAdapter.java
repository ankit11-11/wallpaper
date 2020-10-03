package com.example.firebasewallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebasewallpaper.DetailViewActivity;
import com.example.firebasewallpaper.R;
import com.example.firebasewallpaper.models.WallpaperModel;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private Context context;
    private ArrayList<WallpaperModel> wallpaperModels;

    public CarAdapter(Context context, ArrayList<WallpaperModel> wallpaperModels) {
        this.context = context;
        this.wallpaperModels = wallpaperModels;
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {

        holder.modelName.setText(wallpaperModels.get(position).getBrand().getBrandName());
        holder.info.setText(wallpaperModels.get(position).getBrand().getInfo());
        holder.carModel.setText(wallpaperModels.get(position).getBrand().getModel());

        Glide.with(context).load(wallpaperModels.get(position).getBrand().getUrl().get(0)).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return wallpaperModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout container;
        ImageView icon;
        TextView modelName, info, carModel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.image);
            modelName = itemView.findViewById(R.id.model);
            info = itemView.findViewById(R.id.info);
            carModel = itemView.findViewById(R.id.carModel);
            container = itemView.findViewById(R.id.container);

            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetailViewActivity.class);

                    String[] urls = wallpaperModels.get(getLayoutPosition()).getBrand().getUrl().toArray(new String[0]);

                    i.putExtra("imageUrl",urls);
                    i.putExtra("brandName",wallpaperModels.get(getLayoutPosition()).getBrand().getBrandName());
                    i.putExtra("info",wallpaperModels.get(getLayoutPosition()).getBrand().getInfo());
                    context.startActivity(i);
                }
            });
        }
    }
}
