package de.prog3.proj2021.db;

/*
 * n:m Relation cross reference class for FavouriteList and Recipe
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Entity;
import androidx.room.ForeignKey;

import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.Recipe;

@Entity(primaryKeys = {"favouriteListId", "recipeId"},
        foreignKeys = {
        @ForeignKey(entity = FavouriteList.class,
                parentColumns = "favouriteListId",
                childColumns = "favouriteListId"),
        @ForeignKey(entity = Recipe.class,
                parentColumns = "recipeId",
                childColumns = "recipeId")})
public class FavouriteRecipeCrossRef {
    public int favouriteListId;
    public int recipeId;
}
