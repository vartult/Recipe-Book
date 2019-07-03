package com.cellfishpool.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.repository.RecipeRepository;
import com.cellfishpool.requests.responses.RecipeResponse;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private  String RecipeID;

    public String getRecipeID() {
        return RecipeID;
    }


    public RecipeViewModel() {
        mRecipeRepository= RecipeRepository.getInstance();
    }

    public LiveData<Recipe> getRecipe(){
        return mRecipeRepository.getRecipe();
    }

    public void searchRecipeById(String recipeId){
        RecipeID=recipeId;
        mRecipeRepository.searchRecipebyID(recipeId);
    }

}
