package de.prog3.proj2021.db;

/*
 * n:m Relation cross reference class for Recipe and Ingredient
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Entity;
import androidx.room.ForeignKey;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.Recipe;

@Entity(primaryKeys = {"recipeId", "ingredientId"},
        foreignKeys = {
        @ForeignKey(entity = Recipe.class,
                parentColumns = "recipeId",
                childColumns = "recipeId"),
        @ForeignKey(entity = Ingredient.class,
                parentColumns = "ingredientId",
                childColumns = "ingredientId")})
public class RecipeIngredientCrossRef {
    public int recipeId;
    public int ingredientId;
}
