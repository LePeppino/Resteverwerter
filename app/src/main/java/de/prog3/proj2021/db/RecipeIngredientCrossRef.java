package de.prog3.proj2021.db;

import androidx.room.Entity;

/*
 * n:m Relation cross reference class for Recipe and Ingredient
 *
 * File author: Giuseppe Buccellato
 * */

@Entity(primaryKeys = {"recipeId", "ingredientId"})
public class RecipeIngredientCrossRef {
    public int recipeId;
    public int ingredientId;
}
