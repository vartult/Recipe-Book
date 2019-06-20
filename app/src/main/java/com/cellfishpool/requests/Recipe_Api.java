package com.cellfishpool.requests;

import com.cellfishpool.requests.responses.RecipeResponse;
import com.cellfishpool.requests.responses.RecipeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Recipe_Api {


        // SEARCH
        @GET("api/search")
        Call<RecipeSearchResponse> searchRecipe(
                @Query("key") String key,
                @Query("q") String query,
                @Query("page") String page
        );

        // GET RECIPE REQUEST
        @GET("api/get")
        Call<RecipeResponse> getRecipe(
                @Query("key") String key,
                @Query("rId") String recipe_id
        );

}
