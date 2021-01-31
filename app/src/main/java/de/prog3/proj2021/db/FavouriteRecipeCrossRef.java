package de.prog3.proj2021.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

/*
 * n:m Relation cross reference class for FavouriteList and Recipe
 *
 * File author: Giuseppe Buccellato
 * */

@Entity(primaryKeys = {"favouriteListId", "recipeId"})
public class FavouriteRecipeCrossRef {
    public int favouriteListId;
    public int recipeId;
}
