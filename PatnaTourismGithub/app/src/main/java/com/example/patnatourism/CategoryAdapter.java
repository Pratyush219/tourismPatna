package com.example.patnatourism;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final NavController navController;
    private ArrayList<Category> categories = new ArrayList<>();

    public CategoryAdapter(NavController navController) {
        this.navController = navController;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @Override
    public void onViewRecycled(@NonNull CategoryAdapter.ViewHolder holder) {
        super.onViewRecycled(holder);
        Log.i("Status:", "View recycled");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_for_categories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.category.setText(categories.get(position).getCategory());
        holder.thumbnail.setImageResource(categories.get(position).getImageResourceId());
        holder.categoryItem.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("category", position);
            navController.navigate(R.id.action_categoryFragment_to_placesFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomImageView thumbnail;
        private final TextView category;
        private final LinearLayout categoryItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.text);
            thumbnail = itemView.findViewById(R.id.image);
            categoryItem = itemView.findViewById(R.id.place_category);
        }
    }
}
