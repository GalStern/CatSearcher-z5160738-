package com.example.breedsearcherhomework3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.breedsearcherhomework3.Database.CatDB;
import com.example.breedsearcherhomework3.Database.FavouriteCatDB;
import com.example.breedsearcherhomework3.R;
import com.google.gson.Gson;


public class CatDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cat_info_view);

        Intent intent = getIntent();
        final String intentID = intent.getStringExtra("id");

        final Cat breed = CatDB.getBreedByID(intentID);

        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        TextView weightMetric = findViewById(R.id.weightMetric);
        TextView temperament  = findViewById(R.id.temperament);
        TextView origin = findViewById(R.id.origin);
        TextView lifespan = findViewById(R.id.lifespan);
        TextView wiki = findViewById(R.id.wikipedia);
        TextView dogFriendly = findViewById(R.id.dogFriendly);
        Button button = findViewById(R.id.addBreedButton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FavouriteCatDB.getFavBreeds().put(intentID, breed);
                Toast.makeText(getBaseContext(),"Cat breed added", Toast.LENGTH_SHORT).show();
                System.out.println("cat breed added");
            }
        });

        String apikey = "9048ded8-c948-4337-8bcc-11c199ff632f";
        String url = "https://api.thecatapi.com/v1/images/search?api_key="+apikey+"&breed_id="+breed.getId();

        setCatImage(url, this.findViewById(android.R.id.content));

        setText(breed.getName(), name);
        setText("Description: " + breed.getDescription(), description);
        setText("Weight range (metric): "+ breed.getWeight().getMetric() + " kg", weightMetric);
        setText("Temperament: "+ breed.getTemperament(), temperament);
        setText("Origin: " + breed.getOrigin(), origin);
        setText("Lifespan: " + breed.getLife_span() + " years", lifespan);
        setText("Wiki link: " + breed.getWikipedia_url(), wiki);
        setText("Dog friendliness: " + Integer.toString(breed.getDog_friendly()), dogFriendly);

    }

    private void setText(String string, TextView textView){
        try{
            textView.setText(string);

        }
        catch(NullPointerException ex){
            textView.setText(R.string.no_value);
        }
        System.out.println(textView.getText());

    }

    private void setCatImage(String url, final View view){

        RequestQueue mRequestQueue = Volley.newRequestQueue(view.getContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            //"response" is basically the string that contains the json data
            public void onResponse(String response) {

                Gson gson = new Gson();

                System.out.println("This is the value of response;" + response);
                Image[] catImage = gson.fromJson(response, Image[].class);
                ImageView catImageView = findViewById(R.id.imageView);

                try {
                    Glide.with(getApplicationContext()).load(catImage[0].getUrl()).into(catImageView);
                    System.out.println("Image was loaded");


                } catch (Exception e) {
                    System.out.println("Json does not work");
                    e.printStackTrace();
                    Glide.with(getApplicationContext()).load("https://cdn2.thecatapi.com/images/dN6eoeLjY.jpg").into(catImageView);
                }

            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
                ImageView catImageView = findViewById(R.id.imageView);
                Glide.with(getApplicationContext()).load("https://cdn2.thecatapi.com/images/dN6eoeLjY.jpg").into(catImageView);
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);

        mRequestQueue.add(stringRequest);

    }




}
