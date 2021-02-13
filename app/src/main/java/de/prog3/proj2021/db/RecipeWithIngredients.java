package de.prog3.proj2021.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.Recipe;

/*
 * n:m Relation class for queries for Recipes containing Ingredients
 *
 * File author: Giuseppe Buccellato
 * */

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "rId",
            entityColumn = "iId",
            associateBy = @Junction(value = RecipeIngredientCrossRef.class,
                    parentColumn = "recipeId",
                    entityColumn = "ingredientId")
    )
    public List<Ingredient> ingredients;

//    public RecipeWithIngredients(Recipe recipe, List<Ingredient> ingredients){
//        setRecipe(recipe);
//        setIngredients(ingredients);
//    }
//
//    public RecipeWithIngredients(RecipeWithIngredients recipeWithIngredients){
//        this.recipe = new Recipe(recipeWithIngredients.recipe);
//        this.ingredients = new ArrayList<>(recipeWithIngredients.ingredients);
//    }
//
//    public Recipe getRecipe() {
//        return recipe;
//    }
//
//    private void setRecipe(Recipe recipe) {
//        this.recipe = recipe;
//    }
//
//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    private void setIngredients(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
}
