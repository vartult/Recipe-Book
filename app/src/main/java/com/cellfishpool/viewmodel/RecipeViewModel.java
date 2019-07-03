package com.cellfishpool.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.repository.RecipeRepository;
import com.cellfishpool.requests.responses.RecipeResponse;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;

    public RecipeViewModel() {
        mRecipeRepository= RecipeRepository.getInstance();
    }

    public LiveData<Recipe> getRecipe(){
        return mRecipeRepository.getRecipe();
    }

    public void searchRecipeById(String recipeId){
        mRecipeRepository.searchRecipebyID(recipeId);
    }

}
