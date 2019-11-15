package com.example.breedsearcherhomework3.Database;

import com.example.breedsearcherhomework3.Activity.Cat;

import java.util.HashMap;

public class FavouriteCatDB {

        private static HashMap<String, Cat> favBreeds = new HashMap<>();

        public static HashMap<String, Cat> getFavBreeds() {
            return favBreeds;
        }

    }

