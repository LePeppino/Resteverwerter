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
    public ArrayList<Ingredient> ingredientList;

    @Ignore
    Bitmap headerImage; //TODO: implement header photo for recipes

    //public String description;
    //public String instructions;

    //constructor
    public Recipe(int id, String name, int calories, ArrayList<Ingredient> ingredientList){
        setId(id);
        setName(name);
        setCalories(calories);
        setIngredientList(ingredientList);
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
}
