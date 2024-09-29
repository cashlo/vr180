package com.google.vr180.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<String> imagePaths = new ArrayList<>();
    private SparseBooleanArray selectedPositions = new SparseBooleanArray();


    public ImageAdapter(Context context) {
        this.context = context;
    }

    public void setImages(List<String> paths) {
        this.imagePaths = paths;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imagePath = imagePaths.get(position);
        Glide.with(context).load(imagePath).into(holder.imageView);

        // Set visibility of selection overlay
        boolean isSelected = selectedPositions.get(position, false);
        holder.checkBox.setChecked(isSelected);

        // Handle item click
        holder.imageView.setOnClickListener(v -> {
            if (selectedPositions.get(position, false)) {
                selectedPositions.delete(Integer.valueOf(position));
                holder.checkBox.setChecked(false);
            } else {
                selectedPositions.put(position, true);
                holder.checkBox.setChecked(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagePaths.size();
    }

    public List<String> getSelectedPaths() {
        List<String> selectedPaths = new ArrayList<>();
        for (int i = 0; i < selectedPositions.size(); i++) {
            if (selectedPositions.valueAt(i)) {
                int position = selectedPositions.keyAt(i);
                if (position >= 0 && position < imagePaths.size()) {
                    selectedPaths.add(imagePaths.get(position));
                }
            }
        }
        return selectedPaths;
    }


    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CheckBox checkBox;

        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
