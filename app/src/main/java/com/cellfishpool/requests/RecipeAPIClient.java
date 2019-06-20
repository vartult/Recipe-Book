package com.cellfishpool.requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.AppExecutors;
import com.cellfishpool.util.Constants;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RecipeAPIClient {

    private static RecipeAPIClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeAPIClient getInstance(){
        if(instance == null){
            instance = new RecipeAPIClient();
        }
        return instance;
    }

    private RecipeAPIClient() {
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipes;
    }

    public void searchRecipeAPI(){
        final Future handler = AppExecutors.getInstance().NetworkIO().submit(new Runnable() {
            @Override
            public void run() {
                //retrive data

            }
        });

        AppExecutors.getInstance().NetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancel process after 4 seconds
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }
}
