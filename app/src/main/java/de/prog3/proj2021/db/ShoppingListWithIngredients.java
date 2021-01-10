package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

/*
 * n:m Relation class for queries for ShoppingLists containing Ingredients.
 *
 * */

public class ShoppingListWithIngredients {
    @Embedded
    public ShoppingList shoppingList;
    @Relation(
            parentColumn = "shoppingListId",
            entityColumn = "ingredientId",
            associateBy = @Junction(ShoppingListIngredientCrossRef.class)
    )
    public List<Ingredient> ingredients;
}
