package com.cellfishpool.requests.responses;

import com.cellfishpool.models.Recipe;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeResponse {

    @SerializedName("recipe")
    @Expose
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }
}
