package de.prog3.proj2021.db;

/*
 * Data Access Object for FavouriteList Model
 *
 * File author: Giuseppe Buccellato
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.Recipe;

@Dao
public interface FavouriteListDao {
    @Update
    void update(FavouriteList favouriteList);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertFavouritesWithRecipes(FavouriteRecipeCrossRef favouriteRecipeCrossRef);

    @Update
    void updateFavouritesWithRecipes(FavouriteRecipeCrossRef favouriteRecipeCrossRef);

    @Delete
    void deleteFavouritesWithRecipes(FavouriteRecipeCrossRef favouriteRecipeCrossRef);

    @Transaction
    @Query("SELECT * FROM favouriteList_table")
    List<FavouritesWithRecipes> getFavouritesWithRecipes();
}
