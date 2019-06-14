package com.cellfishpool.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Recipe implements Parcelable {
    private String publisher;
    private String[] ingredients;
    private String Image_URL;
    private String Raing;
    private String Recipe_Id;
    private String title;

    public Recipe(String publisher, String[] ingredients, String image_URL, String raing, String recipe_Id, String title) {
        this.publisher = publisher;
        this.ingredients = ingredients;
        Image_URL = image_URL;
        Raing = raing;
        Recipe_Id = recipe_Id;
        this.title = title;
    }

    protected Recipe(Parcel in) {
        publisher = in.readString();
        ingredients = in.createStringArray();
        Image_URL = in.readString();
        Raing = in.readString();
        Recipe_Id = in.readString();
        title = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getRaing() {
        return Raing;
    }

    public void setRaing(String raing) {
        Raing = raing;
    }

    public String getRecipe_Id() {
        return Recipe_Id;
    }

    public void setRecipe_Id(String recipe_Id) {
        Recipe_Id = recipe_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Recipe() {
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "publisher='" + publisher + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", Image_URL='" + Image_URL + '\'' +
                ", Raing='" + Raing + '\'' +
                ", Recipe_Id='" + Recipe_Id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publisher);
        dest.writeStringArray(ingredients);
        dest.writeString(Image_URL);
        dest.writeString(Raing);
        dest.writeString(Recipe_Id);
        dest.writeString(title);
    }
}
