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

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "favouriteRecipeCrossRef_table",
        primaryKeys = {"favouriteListId", "recipeId"},
        foreignKeys = {
        @ForeignKey(entity = FavouriteList.class,
                parentColumns = "fId",
                childColumns = "favouriteListId",
                onDelete = CASCADE),
        @ForeignKey(entity = Recipe.class,
                parentColumns = "rId",
                childColumns = "recipeId",
                onDelete = CASCADE)})
public class FavouriteRecipeCrossRef {
    public int favouriteListId;
    public int recipeId;

    //constructor for database insertion
    public FavouriteRecipeCrossRef(int favouriteListId, int recipeId){
        this.favouriteListId = favouriteListId;
        this.recipeId = recipeId;
    }
}
