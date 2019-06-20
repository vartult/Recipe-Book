package com.cellfishpool;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;
import timber.log.Timber;

public class RecipeListActivity extends Base_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hello =findViewById(R.id.hello);

        hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Test",Toast.LENGTH_SHORT).show();
                testRetrofitRequest();
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
                Timber.d("Response Server %s", response.toString());
                if(response.code()==200){
                    Toast.makeText(getApplicationContext(),"rUNNING PROPERLY",Toast.LENGTH_LONG).show();
                    Log.d("Hello", "Response"+response.body().toString());
                    //Timber.d(String.format("Response came %s", response.body().toString()));
                    if(response.body().getRecipes()!=null){
                    List<Recipe> recipes = new ArrayList<>(response.body().getRecipes());
                    for(Recipe i: recipes){

                        Log.d("Hello","response is %s" + i.toString());
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
}
