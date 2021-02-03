package de.prog3.proj2021.db;

/*
 * n:m Relation cross reference class for FavouriteList and Recipe
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

/*
 * n:m Relation class for queries for FavouriteList containing Recipes
 *
 * */

import java.util.List;

import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.Recipe;

public class FavouritesWithRecipes {
    @Embedded
    public FavouriteList favouriteList;
    @Relation(
            parentColumn = "id",
            entity = Recipe.class,
            entityColumn = "id",
            associateBy = @Junction(value = FavouriteRecipeCrossRef.class,
                    parentColumn = "favouriteListId",
                    entityColumn = "recipeId")
    )
    public List<Recipe> recipes;
}
