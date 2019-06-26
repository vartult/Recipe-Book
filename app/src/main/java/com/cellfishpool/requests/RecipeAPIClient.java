package com.cellfishpool.requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.cellfishpool.models.Recipe;
import com.cellfishpool.AppExecutors;
import com.cellfishpool.requests.responses.RecipeSearchResponse;
import com.cellfishpool.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class RecipeAPIClient {

    private static RecipeAPIClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;
    private RetrieveRecipesRunnable mretrieveRecipesRunnable;

    public static RecipeAPIClient getInstance(){
        if(instance == null){
            instance = new RecipeAPIClient();
        }
        return instance;
    }

    private RecipeAPIClient() {
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes(){
        return mRecipes;
    }

    
    public void searchRecipesAPI(String query, int pageNumber){
        if(mretrieveRecipesRunnable != null){
            mretrieveRecipesRunnable = null;
        }
        mretrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);
        final Future handler = AppExecutors.getInstance().NetworkIO().submit(mretrieveRecipesRunnable);

        // Set a timeout for the data refresh
        AppExecutors.getInstance().NetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know it timed out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveRecipesRunnable implements Runnable{
        private String query;
        private int pageNumber;
        boolean cancel_req;

        public RetrieveRecipesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancel_req=false;
        }

        @Override
        public void run() {
            try {
                Response response=getRecipes(query,pageNumber).execute();
                if(cancel_req==true){
                    return;
                }
                if(response.code()==200){
                    List<Recipe> list = new ArrayList<>(((RecipeSearchResponse)response.body()).getRecipes());
                    if(pageNumber==1){
                        mRecipes.postValue(list);
                    }
                    else {
                        List<Recipe> currentRecipe = mRecipes.getValue();
                        currentRecipe.addAll(list);

                        mRecipes.postValue(currentRecipe);
                    }
                }
                else{
                    String error = response.errorBody().string();
                    Log.e("Hello", "run: "+error);
                    mRecipes.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mRecipes.postValue(null);
            }
        }

        private Call<RecipeSearchResponse> getRecipes(String query,int pageNumber){
            return ServiceGenerator.getRecipeApi().searchRecipe(
                    Constants.API_Key,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequest(){
            Log.d("hello","Cancel Request: Cancelling the search query");
        }
    }

    public void cancelRequest(){
        if(mretrieveRecipesRunnable!=null){
            mretrieveRecipesRunnable.cancelRequest();
        }
    }
}
