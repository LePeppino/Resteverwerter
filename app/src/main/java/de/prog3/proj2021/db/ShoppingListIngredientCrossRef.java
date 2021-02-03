package de.prog3.proj2021.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.models.ShoppingList;

/*
 * n:m Relation cross reference class for ShoppingList and Ingredient
 *
 * */

@Entity(primaryKeys = {"shoppingListId", "ingredientId"},
        foreignKeys = {
        @ForeignKey(entity = ShoppingList.class,
                parentColumns = "shoppingListId",
                childColumns = "shoppingListId"),
        @ForeignKey(entity = Ingredient.class,
                parentColumns = "ingredientId",
                childColumns = "ingredientId")})
public class ShoppingListIngredientCrossRef {
    public int shoppingListId;
    public int ingredientId;
}
