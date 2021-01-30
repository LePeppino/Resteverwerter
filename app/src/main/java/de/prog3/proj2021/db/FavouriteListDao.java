package de.prog3.proj2021.db;

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

@Dao
public interface FavouriteListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavouriteList(FavouriteList favouriteList);

    @Update
    void updateFavouriteListInfo(FavouriteList favouriteList);

    @Delete
    void deleteFavouriteList(FavouriteList favouriteList);

    // Transaction to return all instances for the relation
    // FavouritesWithRecipes atomically
    @Transaction
    @Query("SELECT * FROM favouriteList_table")
    LiveData<List<FavouritesWithRecipes>> getFavouritesWithRecipes();
}
