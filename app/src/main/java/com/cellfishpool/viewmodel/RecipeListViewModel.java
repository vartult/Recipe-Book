package com.cellfishpool.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.repository.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private boolean mIsViewingRecipes;

    //private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

    public RecipeListViewModel() {
        mIsViewingRecipes = false;
        mRecipeRepository= RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, Integer pageNumber){
        mIsViewingRecipes=true;
        mRecipeRepository.searchRecipesApi(query,pageNumber);
    }
    public boolean ismIsViewingRecipes(){
        return mIsViewingRecipes;
    }
    public void setmIsViewingRecipes(boolean isViewingRecipes){
        mIsViewingRecipes = isViewingRecipes;
    }

}
