package com.example.breedsearcherhomework3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.breedsearcherhomework3.Activity.CatDetailActivity;
import com.example.breedsearcherhomework3.Activity.Cat;

//jihjkj
import com.example.breedsearcherhomework3.R;

import java.util.List;

public class BreedSearchAdapter extends RecyclerView.Adapter<BreedSearchAdapter.BreedSearchViewHolder>{

    private List<Cat> breedsToAdapt;

    public void setData(List<Cat> articlesToAdapt) {
        this.breedsToAdapt = articlesToAdapt;
    }

    @NonNull
    @Override
    public BreedSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_viewholder, parent, false);

        BreedSearchViewHolder breedViewHolder = new BreedSearchViewHolder(view);
        return breedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BreedSearchViewHolder holder, int position) {
        final Cat breedAtPosition = breedsToAdapt.get(position);
        holder.bind(breedAtPosition);
    }

    @Override
    public int getItemCount() {
        return breedsToAdapt.size();
    }

    public class BreedSearchViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView breedName;

        public BreedSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            this.breedName = itemView.findViewById(R.id.breedName);
        }

        public void bind(final Cat breed) {
            breedName.setText(breed.getName());

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Context context = view.getContext();

                    Intent intent = new Intent(context, CatDetailActivity.class);
                    intent.putExtra("id", breed.getId());
                    context.startActivity(intent);
                }
            });

        }

    }
}
