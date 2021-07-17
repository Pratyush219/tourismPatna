package com.example.patnatourism;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.IntroViewHolder> {
    private final NavController navController;
    private ArrayList<Introduction> intros = new ArrayList<>();

    public IntroAdapter(NavController navController) {
        this.navController = navController;
    }

    public void setIntros(ArrayList<Introduction> intros) {
        this.intros = intros;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.content_intro_list_item,
                parent,
                false
        );
        return new IntroViewHolder(view);
    }

    @Override
    public void onViewRecycled(@NonNull IntroAdapter.IntroViewHolder holder) {
        super.onViewRecycled(holder);
        Log.i("Status:", "View recycled");
    }

    @Override
    public void onBindViewHolder(@NonNull IntroAdapter.IntroViewHolder holder, int position) {
        holder.imageView.setImageResource(intros.get(position).getImageResourceId());
        holder.textView.setText(intros.get(position).getName() + ":\n" + intros.get(position).getText());

        holder.content.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("image", intros.get(position).getImageResourceId());
            bundle.putString("detail", intros.get(position).getText());
            bundle.putString("name", intros.get(position).getName());
            bundle.putString("website", intros.get(position).getWebsite());
            navController.navigate(R.id.action_placesFragment_to_detailsFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return intros.size();
    }

    public static class IntroViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final LinearLayout content;

        public IntroViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo);
            textView = itemView.findViewById(R.id.para);
            content = itemView.findViewById(R.id.intro);
        }
    }
}
