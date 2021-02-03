package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

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
            parentColumn = "recipeId",
            entity = Ingredient.class,
            entityColumn = "ingredientId",
            associateBy = @Junction(value = RecipeIngredientCrossRef.class,
                    parentColumn = "recipeId",
                    entityColumn = "ingredientId")
    )
    public List<Ingredient> ingredients;
}
