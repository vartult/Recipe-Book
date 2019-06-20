package com.cellfishpool.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.requests.RecipeAPIClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeAPIClient mRecipeApiClient;

    public static RecipeRepository getInstance(){
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    private RecipeRepository() {
        mRecipeApiClient = RecipeAPIClient.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipeApiClient.getRecipes();
    }


}
