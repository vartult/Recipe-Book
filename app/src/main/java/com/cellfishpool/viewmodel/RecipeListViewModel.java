package com.cellfishpool.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.cellfishpool.models.Recipe;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private MutableLiveData<List<Recipe>> mRecipes = new MutableLiveData<>();

    public RecipeListViewModel() {

    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

}
