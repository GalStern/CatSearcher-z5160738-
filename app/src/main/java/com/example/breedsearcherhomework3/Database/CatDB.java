package com.example.breedsearcherhomework3.Database;

import com.example.breedsearcherhomework3.Activity.Cat;

import java.util.HashMap;
import java.util.List;

public class CatDB {
    private static HashMap<String, Cat> breeds = new HashMap<>();

    public static HashMap<String, Cat> getBreeds() {
        return breeds;
    }

    public static Cat getBreedByID(String id) {
        return breeds.get(id);
    }

    public static void saveBreedsToFakeDatabase(List<Cat> breedToSave) {
        for(int i = 0; i < breedToSave.size(); i++) {
            Cat breed = breedToSave.get(i);
            breeds.put(breed.getId(), breed);
        }
        System.out.println(breeds);
    }
}
