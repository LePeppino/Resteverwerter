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
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.Recipe;

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

    @Query("SELECT * FROM ingredient_table ORDER BY type, name ASC")
    LiveData<List<Ingredient>> getIngredientsByTypeASC();

    @Query("SELECT * FROM ingredient_table WHERE name LIKE :query ORDER BY type, name ASC")
    LiveData<List<Ingredient>> getIngredientsByQuery(String query);

    @Query("SELECT * FROM ingredient_table WHERE name LIKE :query")
    Ingredient getSingleIngredientByQuery(String query);

    @Transaction
    @Query("SELECT * FROM ingredient_table")
    List<IngredientWithRecipes> getIngredientWithRecipes();

}
