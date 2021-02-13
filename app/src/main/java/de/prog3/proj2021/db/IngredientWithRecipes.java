package de.prog3.proj2021.db;

/*
 * n:m Relation class for queries for Recipes containing Ingredients
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.Recipe;

public class IngredientWithRecipes {
    @Embedded
    public Ingredient ingredient;
    @Relation(
            parentColumn = "iId",
            entity = Recipe.class,
            entityColumn = "rId",
            associateBy = @Junction(value = RecipeIngredientCrossRef.class,
                    parentColumn = "ingredientId",
                    entityColumn = "recipeId")
    )
    public List<Recipe> recipes;
}
