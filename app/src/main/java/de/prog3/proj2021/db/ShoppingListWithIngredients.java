package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.ShoppingList;

/*
 * n:m Relation class for queries for ShoppingLists containing Ingredients.
 *
 * File author: Giuseppe Buccellato
 * */

public class ShoppingListWithIngredients {
    @Embedded
    public ShoppingList shoppingList;
    @Relation(
            parentColumn = "id",
            entity = Ingredient.class,
            entityColumn = "id",
            associateBy = @Junction(value = ShoppingListIngredientCrossRef.class,
                    parentColumn = "shoppingListId",
                    entityColumn = "ingredientId")
    )
    public List<Ingredient> ingredients;

    //constructor
    public ShoppingListWithIngredients(ShoppingList shoppingList, List<Ingredient> ingredients){
        this.shoppingList = new ShoppingList(shoppingList);
        this.ingredients = new ArrayList<>(ingredients);
    }

    //copy
    public ShoppingListWithIngredients(ShoppingListWithIngredients shoppingListWithIngredients){
        this.shoppingList = new ShoppingList(shoppingListWithIngredients.shoppingList);
        this.ingredients = new ArrayList<>(shoppingListWithIngredients.ingredients);
    }
}
