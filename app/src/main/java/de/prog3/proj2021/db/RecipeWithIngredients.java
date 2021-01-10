package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

/*
 * n:m Relation class for queries for Recipes containing Ingredients
 *
 * */

public class RecipeWithIngredients {
    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "recipeId",
            entityColumn = "ingredientId",
            associateBy = @Junction(RecipeIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}
