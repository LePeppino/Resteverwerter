package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface FavouriteListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavouriteList(FavouriteList favouriteList);

    @Update
    Completable updateFavouriteListInfo(FavouriteList favouriteList);

    @Delete
    Completable deleteFavouriteList(FavouriteList favouriteList);

    // Transaction to return all instances for the relation
    // FavouritesWithRecipes atomically
    @Transaction
    @Query("SELECT * FROM FavouriteList")
    Flowable<List<FavouritesWithRecipes>> getFavouritesWithRecipes();
}
