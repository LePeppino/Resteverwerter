package de.prog3.proj2021.db;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @PrimaryKey
    public int id;

    public String name;
    public int calories;
    public boolean isFavourite;
    public ArrayList<Ingredient> ingredientList; //TODO: find a way to store List of ingredients into database
    public String description;
    public String instructions;
    //public ArrayList<String> keywords; //keywords for search-feature?

    @Ignore
    Bitmap headerImage; //TODO: implement header photo for recipes

    //constructor
    public Recipe(int id,
                  String name,
                  int calories,
                  boolean isFavourite,
                  ArrayList<Ingredient> ingredientList,
                  String description,
                  String instructions,
                  Bitmap headerImage)
    {
        setId(id);
        setName(name);
        setCalories(calories);
        setFavourite(isFavourite);
        setIngredientList(ingredientList);
        setDescription(description);
        setInstructions(instructions);
        setHeaderImage(headerImage);
    }

    //getter, setter
    public int getId() {return id;}
    private void setId(int id) {this.id = id;}

    public String getName() {return name;}
    private void setName(String name) {this.name = name;}

    public int getCalories() {return calories;}
    private void setCalories(int calories) {this.calories = calories;}

    public List<Ingredient> getIngredientList() {return ingredientList;}
    private void setIngredientList(ArrayList<Ingredient> ingredientList) {this.ingredientList = ingredientList;}

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
