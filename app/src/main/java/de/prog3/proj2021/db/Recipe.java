package de.prog3.proj2021.db;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/*
* Recipe has n:m relation to FavouriteList class.
* Recipe has n:m relation to Ingredient class.
*
* */

@Entity
public class Recipe {
    @PrimaryKey
    public int recipeId;

    public String name;
    public int calories;
    public boolean isFavourite;
    public String description;
    public String instructions;
    //TODO: keywords list for search-feature?

    @Ignore
    Bitmap headerImage = null; //TODO: implement header photo for recipes

    //constructor
    public Recipe(int recipeId,
                  String name,
                  int calories,
                  boolean isFavourite,
                  String description,
                  String instructions)
    {
        setId(recipeId);
        setName(name);
        setCalories(calories);
        setFavourite(isFavourite);
        setDescription(description);
        setInstructions(instructions);
        //setHeaderImage(headerImage);
    }

    //getter, setter
    public int getRecipeIdId() {return recipeId;}
    private void setId(int recipeId) {this.recipeId = recipeId;}

    public String getName() {return name;}
    private void setName(String name) {this.name = name;}

    public int getCalories() {return calories;}
    private void setCalories(int calories) {this.calories = calories;}

    public boolean isFavourite() {
        return isFavourite;
    }
    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Bitmap getHeaderImage() {
        return headerImage;
    }
    public void setHeaderImage(Bitmap headerImage) {
        this.headerImage = headerImage;
    }
}
