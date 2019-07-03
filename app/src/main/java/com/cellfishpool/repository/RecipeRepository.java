package com.cellfishpool.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.requests.RecipeAPIClient;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeAPIClient mRecipeApiClient;
    private int mPage;
    private  String mQuery;
    private  String RecipeID;

    public String getRecipeID() {
        return RecipeID;
    }

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

    public LiveData<Recipe> getRecipe(){
        return mRecipeApiClient.getRecipe();
    }

    public void searchRecipesApi(String query, int pageNumber){
        if(pageNumber == 0){
            pageNumber = 1;
        }
        mPage=pageNumber;
        mQuery=query;
        mRecipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void searchRecipebyID(String Recipe_id){

        RecipeID=Recipe_id;
        mRecipeApiClient.searchRecipeById(Recipe_id);
    }

    public void search_more(){
        searchRecipesApi(mQuery, mPage+1);
    }

    public void cancelRequest(){
            mRecipeApiClient.cancelRequest();
    }


}
