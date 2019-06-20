package com.cellfishpool;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.requests.Recipe_Api;
import com.cellfishpool.requests.ServiceGenerator;
import com.cellfishpool.requests.responses.RecipeResponse;
import com.cellfishpool.requests.responses.RecipeSearchResponse;
import com.cellfishpool.util.Constants;
import com.cellfishpool.viewmodel.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RecipeListActivity extends Base_Activity {

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        subscribeObservers();

        Button hello =findViewById(R.id.hello);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });

    }

    private void subscribeObservers(){

        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes!=null){
                    for(Recipe i: recipes){
                        Log.d("Hello", "Live data: "+ i.getTitle());
                    }
                }

            }
        });
    }

    private void searchRecipeApi(String query, Integer pageNumber){

        mRecipeListViewModel.searchRecipesApi(query,pageNumber);
    }

    private void testRetrofitRequest(){
        searchRecipeApi("Bread",1);
    }

}
