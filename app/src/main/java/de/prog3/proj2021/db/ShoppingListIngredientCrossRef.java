package de.prog3.proj2021.db;

/*
 * n:m Relation cross reference class for ShoppingList and Ingredient
 *
 * */

import androidx.room.Entity;
import androidx.room.ForeignKey;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.ShoppingList;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "shoppingListIngredientCrossRef_table",
        primaryKeys = {"shoppingListId", "ingredientId"},
        foreignKeys = {
        @ForeignKey(entity = ShoppingList.class,
                parentColumns = "id",
                childColumns = "shoppingListId",
                onDelete = CASCADE),
        @ForeignKey(entity = Ingredient.class,
                parentColumns = "id",
                childColumns = "ingredientId",
                onDelete = CASCADE)})
public class ShoppingListIngredientCrossRef {
    public int shoppingListId;
    public int ingredientId;
}
