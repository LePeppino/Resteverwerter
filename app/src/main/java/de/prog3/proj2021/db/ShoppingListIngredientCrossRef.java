package de.prog3.proj2021.db;

import androidx.room.Entity;

/*
 * n:m Relation cross reference class for ShoppingList and Ingredient
 *
 * */

@Entity(primaryKeys = {"shoppingListId", "ingredientId"})
public class ShoppingListIngredientCrossRef {
    public int shoppingListId;
    public int ingredientId;
}
