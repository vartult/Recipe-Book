package com.cellfishpool.requests;

import com.cellfishpool.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder= new Retrofit.Builder()
            .baseUrl(Constants.base_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();
    private static Recipe_Api recipeApi=retrofit.create(Recipe_Api.class);

    public static Recipe_Api getRecipeApi(){
        return recipeApi;
    }
}
