package com.example.breedsearcherhomework3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.breedsearcherhomework3.Adapter.BreedSearchAdapter;
import com.example.breedsearcherhomework3.Database.CatDB;
import com.example.breedsearcherhomework3.Activity.Cat;
import com.example.breedsearcherhomework3.R;
import com.google.gson.Gson;

import java.util.Arrays;

public class BreedSearchFragment  extends Fragment {
    public BreedSearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.cat_search_r_view, container, false);

        final SearchView searchBar = view.findViewById(R.id.searchBar);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String url = "https://api.thecatapi.com/v1/breeds/search?q=" + searchBar.getQuery();
                searchBreed(url, view);
                System.out.println("Search results found");
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                String url = "https://api.thecatapi.com/v1/breeds/search?q=" + searchBar.getQuery();
                searchBreed(url, view);
                return false;
            }
        });

        return view;

    }

    private void searchBreed(String url, final View view){

        RequestQueue mRequestQueue = Volley.newRequestQueue(view.getContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            //"response" is basically the string that contains the json data
            public void onResponse(String response) {
                System.out.println("Thing works");
                RecyclerView recyclerView = view.findViewById(R.id.rv);
                LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                BreedSearchAdapter breedAdapter = new BreedSearchAdapter();

                Gson gson = new Gson();

                Cat[] breedSearchResult = gson.fromJson(response, Cat[].class);
                CatDB.saveBreedsToFakeDatabase(Arrays.asList(breedSearchResult));
                breedAdapter.setData(Arrays.asList(breedSearchResult));
                recyclerView.setAdapter(breedAdapter);
                System.out.println("Adapter has been set");
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Failed");
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);

        mRequestQueue.add(stringRequest);

    }

}
