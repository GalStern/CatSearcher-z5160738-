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
import com.example.breedsearcherhomework3.R;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavBreedsViewHolder>{


    private List<Cat> favBreedsToAdapt;

    public void setData(List<Cat> articlesToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.favBreedsToAdapt = articlesToAdapt;
    }

    @NonNull
    @Override
    public FavouriteAdapter.FavBreedsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_viewholder, parent, false);

        FavouriteAdapter.FavBreedsViewHolder bookViewHolder = new FavouriteAdapter.FavBreedsViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.FavBreedsViewHolder holder, int position) {
        final Cat breedAtPosition = favBreedsToAdapt.get(position);
        holder.bind(breedAtPosition);


    }

    @Override
    public int getItemCount() {
        return favBreedsToAdapt.size();
    }

    public class FavBreedsViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView breedName;

        public FavBreedsViewHolder(@NonNull View itemView) {
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
