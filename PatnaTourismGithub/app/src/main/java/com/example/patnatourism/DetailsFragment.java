package com.example.patnatourism;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class DetailsFragment extends Fragment {
    private int imageId;
    private String detail;
    private String name;
    private String website;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageId = getArguments().getInt("image");
            detail = getArguments().getString("detail");
            name = getArguments().getString("name");
            website = getArguments().getString("website");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.place_image);
        TextView textView = view.findViewById(R.id.place_description);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        imageView.setContentDescription(name);
        imageView.setImageResource(imageId);
        textView.setText(detail);
        toolbar.setTitle(name);

        view.findViewById(R.id.website).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(website));
            requireActivity().startActivity(intent);
        });
        view.findViewById(R.id.location).setOnClickListener(v -> {
            String encodedQuery = Uri.encode(name);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + encodedQuery + "&z=20"));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Snackbar.make(view, "No app available", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
    }
}