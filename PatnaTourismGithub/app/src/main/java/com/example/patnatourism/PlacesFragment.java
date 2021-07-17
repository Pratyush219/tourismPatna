package com.example.patnatourism;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {
    int category;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_places, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar1);
        toolbar.setTitle(getResources().getStringArray(R.array.categories_list)[category]);
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());

        RecyclerView recyclerView = view.findViewById(R.id.categories);
        TypedArray imageIds;
        String[] desc;
        String[] names;
        String[] websites;
        switch (category) {
            case 1:
                imageIds = getResources().obtainTypedArray(R.array.pilgrimages_images);
                desc = getResources().getStringArray(R.array.pilgrimages_desc);
                names = getResources().getStringArray(R.array.pNames);
                websites = getResources().getStringArray(R.array.pilgrimages_website);
                break;
            case 2:
                imageIds = getResources().obtainTypedArray(R.array.infotainment_images);
                desc = getResources().getStringArray(R.array.infotainment_desc);
                names = getResources().getStringArray(R.array.iNames);
                websites = getResources().getStringArray(R.array.infotainment_website);
                break;
            case 3:
                imageIds = getResources().obtainTypedArray(R.array.entertainment_images);
                desc = getResources().getStringArray(R.array.entertainment_desc);
                names = getResources().getStringArray(R.array.eNames);
                websites = getResources().getStringArray(R.array.entertainment_website);
                break;
            case 4:
                imageIds = getResources().obtainTypedArray(R.array.monuments_images);
                desc = getResources().getStringArray(R.array.monuemts_desc);
                names = getResources().getStringArray(R.array.mNames);
                websites = getResources().getStringArray(R.array.monuemts_website);
                break;
            default:
                imageIds = getResources().obtainTypedArray(R.array.places_of_attraction_images);
                desc = getResources().getStringArray(R.array.places_of_attraction_desc);
                names = getResources().getStringArray(R.array.aNames);
                websites = getResources().getStringArray(R.array.places_of_attraction_website);
        }
        Log.i("Location", "In CategoryFragment");
        ArrayList<Introduction> descArrayList = new ArrayList<>();
        for (int i = 0; i < desc.length; i++) {
            descArrayList.add(new Introduction(
                    imageIds.getResourceId(i, 0),
                    desc[i],
                    names[i],
                    websites[i]
            ));
        }
        NavController navController = Navigation.findNavController(view);
        IntroAdapter adapter = new IntroAdapter(navController);
        adapter.setIntros(descArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        imageIds.recycle();
    }
}