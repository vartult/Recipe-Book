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

    //private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

    public RecipeListViewModel() {
        mRecipeRepository= RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

}
