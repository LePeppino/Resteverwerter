package de.prog3.proj2021.db;

/*
 * Data Access Object for Ingredient Model
 *
 * File author: Giuseppe Buccellato
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.prog3.proj2021.models.Ingredient;

@Dao
public interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(Ingredient ingredient);

    @Update
    void updateIngredient(Ingredient ingredient);

    @Delete
    void deleteIngredient(Ingredient... ingredients); //attempts to delete one or more ingredients

    @Query("SELECT * FROM ingredient_table")
    LiveData<List<Ingredient>> getIngredients();

    @Query("SELECT * FROM ingredient_table ORDER BY name ASC")
    LiveData<List<Ingredient>> getIngredientsASC();

}
