package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

/*
 * n:m Relation class for queries for FavouriteList containing Recipes
 *
 * */

import java.util.List;

public class FavouritesWithRecipes {
    @Embedded
    public FavouriteList favouriteList;
    @Relation(
            parentColumn = "favouriteListId",
            entityColumn = "recipeId",
            associateBy = @Junction(FavouriteRecipeCrossRef.class)
    )
    public List<Recipe> recipes;
}
