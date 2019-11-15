package com.example.breedsearcherhomework3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.breedsearcherhomework3.Adapter.FavouriteAdapter;
import com.example.breedsearcherhomework3.Database.FavouriteCatDB;
import com.example.breedsearcherhomework3.Activity.Cat;
import com.example.breedsearcherhomework3.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteBreedFragment extends Fragment {

    public FavouriteBreedFragment() {
        // Required empty public constructor iuhkvlfkjhgfd
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourite_r_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter();
        List<Cat> list = new ArrayList<Cat>(FavouriteCatDB.getFavBreeds().values());
        favouriteAdapter.setData(list);
        recyclerView.setAdapter(favouriteAdapter);

        return view;

    }
}
