package de.prog3.proj2021.models;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/*
* Recipe has n:m relation to FavouriteList class.
* Recipe has n:m relation to Ingredient class.
*
* File author: Giuseppe Buccellato
*
* */

@Entity(tableName = "recipe_table")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int recipeId;

    @NonNull
    public String name = "";
    public int calories;
    public int isFavourite;             //bool value: 0 = false, 1 = true
    @NonNull
    public String description = "";
    @NonNull
    public String instructions = "";
    @NonNull
    public String headerImageUrl = "";

    //constructor
    public Recipe(String name,
                  int calories,
                  int isFavourite,
                  String description,
                  String instructions,
                  String headerImageUrl)
    {
        setName(name);
        setCalories(calories);
        setFavourite(isFavourite);
        setDescription(description);
        setInstructions(instructions);
        setHeaderImageUrl(headerImageUrl);
    }

    //getter, setter
    public int getRecipeId() {return recipeId;}
    private void setRecipeId(int recipeId) {this.recipeId = recipeId;}

    public @NonNull String getName() {return name;}
    private void setName(@NonNull String name) {this.name = name;}

    public int getCalories() {return calories;}
    private void setCalories(int calories) {this.calories = calories;}

    public int isFavourite() {
        return isFavourite;
    }
    private void setFavourite(int favourite) {
        isFavourite = favourite;
    }

    public @NonNull String getDescription() {
        return description;
    }
    private void setDescription(@NonNull String description) {
        this.description = description;
    }

    public @NonNull String getInstructions() {
        return instructions;
    }
    private void setInstructions(@NonNull String instructions) {
        this.instructions = instructions;
    }

    public @NonNull String getHeaderImageUrl() {
        return headerImageUrl;
    }
    private void setHeaderImageUrl(@NonNull String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }

}
