package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Completable;

@Dao
public interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertIngredient(Ingredient ingredient);

    @Update
    Completable updateIngredient(Ingredient ingredient);

    @Delete
    Completable deleteIngredient(Ingredient... ingredients); //attempts to delete one or more ingredients

}
