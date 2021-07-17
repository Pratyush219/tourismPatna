package com.example.patnatourism;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.start_up_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.categories);
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        TypedArray imagesList = getResources().obtainTypedArray(R.array.categoriesImagesList);
        for (int i = 0; i < imagesList.length(); i++) {
            categoryArrayList.add(new Category(
                    imagesList.getResourceId(i % 5, 0),
                    getResources().getStringArray(R.array.categories_list)[i % 5]
            ));
        }
        Toolbar toolbar = view.findViewById(R.id.toolbar1);
        Log.i("Toolbar id is"," "+toolbar.getId());
        toolbar.setTitle(R.string.home);
        toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
        NavController navController = Navigation.findNavController(view);
        CategoryAdapter adapter = new CategoryAdapter(navController);
        adapter.setCategories(categoryArrayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), getResources().getInteger(R.integer.span_count),RecyclerView.VERTICAL,false));

        imagesList.recycle();
    }
}