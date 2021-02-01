package de.prog3.proj2021.db;

/*
 * n:m Relation cross reference class for FavouriteList and Recipe
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Entity;

@Entity(tableName = "favouriteRecipeCrossRef_table",
        primaryKeys = {"favouriteListId", "recipeId"})
public class FavouriteRecipeCrossRef {
    public int favouriteListId;
    public int recipeId;
}
