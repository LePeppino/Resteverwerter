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

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "recipeIngredientCrossRef_table",
        primaryKeys = {"recipeId", "ingredientId"},
        foreignKeys = {
        @ForeignKey(entity = Recipe.class,
                parentColumns = "id",
                childColumns = "recipeId",
                onDelete = CASCADE),
        @ForeignKey(entity = Ingredient.class,
                parentColumns = "id",
                childColumns = "ingredientId",
                onDelete = CASCADE)})
public class RecipeIngredientCrossRef {
    public int recipeId;
    public int ingredientId;
}
