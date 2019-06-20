package com.cellfishpool;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.requests.Recipe_Api;
import com.cellfishpool.requests.ServiceGenerator;
import com.cellfishpool.requests.responses.RecipeResponse;
import com.cellfishpool.requests.responses.RecipeSearchResponse;
import com.cellfishpool.util.constants;
import com.cellfishpool.viewmodel.RecipeListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;
import timber.log.Timber;

public class RecipeListActivity extends Base_Activity {

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        Button hello =findViewById(R.id.hello);

    }

    private void subscribeObservers(){

        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {

            }
        });
    }


    private void testRetrofitRequest(){
        Recipe_Api recipe_api= ServiceGenerator.getRecipeApi();

        Call<RecipeSearchResponse> responseCall= recipe_api.searchRecipe(
                constants.API_Key,
                "Bread",
                "1"
        );

        responseCall.enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                Log.d("Hello","Response Server %s"+ response.toString());
                //Timber.d("Response Server %s", response.toString());
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(),"rUNNING PROPERLY",Toast.LENGTH_LONG).show();
                    Log.d("Hello", "Response"+response.body().toString());
                    //Timber.d(String.format("Response came %s", response.body().toString()));
                    if(response.body().getRecipes()!=null){
                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
                    for(Recipe recipe: recipes){

                        Log.d("Hello","response is %s" + recipe.toString());
                    }}
                }
                else{

                    Toast.makeText(getApplicationContext()," not rUNNING PROPERLY",Toast.LENGTH_LONG).show();
                    try{
                        Timber.d(response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

            }
        });

    }

    private void RetrofitRecipeCheck(){
        Recipe_Api recipe_api= ServiceGenerator.getRecipeApi();

        Call<RecipeResponse> recipeResponse= recipe_api.getRecipe(
                constants.API_Key,
                "12345"
        );

        recipeResponse.enqueue(new Callback<RecipeResponse>() {

            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Log.d("Hello", "Response"+response.toString());
                if(response.code()==200){
                    Log.d("Hello", "Response"+response.body().toString());
                    //Timber.d(String.format("Response came %s", response.body().toString()));
                        Recipe recipe=response.body().getRecipe();
                        Log.d("Hello","Response "+recipe.toString());

                }
                else{

                    Toast.makeText(getApplicationContext()," not rUNNING PROPERLY",Toast.LENGTH_LONG).show();
                    try{
                        Timber.d(response.errorBody().string());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {

            }
        });
    }
}
