package com.cellfishpool;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.cellfishpool.models.Recipe;
import com.cellfishpool.viewmodel.RecipeListViewModel;
import com.cellfishpool.viewmodel.RecipeViewModel;

import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class Recipe_Activity extends Base_Activity {

    // UI components
    private AppCompatImageView mRecipeImage;
    private TextView mRecipeTitle, mRecipeRank;
    private LinearLayout mRecipeIngredientsContainer;
    private ScrollView mScrollView;

    private RecipeViewModel mRecipeViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recipe);
        mRecipeImage = findViewById(R.id.recipe_image);
        mRecipeTitle = findViewById(R.id.recipe_title);
        mRecipeRank = findViewById(R.id.recipe_social_score);
        mRecipeIngredientsContainer = findViewById(R.id.ingredients_container);
        mScrollView = findViewById(R.id.parent);


        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        showProgressBar(true);
        subscribeObservers();
        getComingIntent();
    }

    private void subscribeObservers() {
        mRecipeViewModel.getRecipe().observe(this, new Observer<Recipe>() {
            @Override
            public void onChanged(@Nullable Recipe recipe) {
                Log.d("Hello", "testing");
                if(recipe != null) {
                    Log.d("Hello", recipe.getTitle());
                    for (String Ing : recipe.getIngredients()) {
                        Log.d("Hello Ing", Ing);
                    }
                }
            }
        });
    }


    private void getComingIntent(){
        if(getIntent().hasExtra("recipe")){
            Recipe recipe = getIntent().getParcelableExtra("recipe");
            Log.d("hello","The recipe is "+ recipe.getRecipe_id());
            mRecipeViewModel.searchRecipeById(recipe.getRecipe_id());
        }
    }
}














