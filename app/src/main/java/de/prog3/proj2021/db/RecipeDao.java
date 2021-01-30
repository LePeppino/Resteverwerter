package de.prog3.proj2021.db;

/*
 * Data Access Object for Recipe Model
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

import de.prog3.proj2021.models.Recipe;

@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe... recipes);

    @Query("DELETE FROM recipe_table")
    void deleteAllRecipes();

    @Query("SELECT * FROM recipe_table")
    LiveData<List<Recipe>> getRecipes();

    @Query("SELECT * FROM recipe_table ORDER BY name ASC")
    LiveData<List<Recipe>> getRecipesASC();

    @Query("SELECT * FROM recipe_table ORDER BY name DESC")
    LiveData<List<Recipe>> getRecipesDESC();

    @Transaction
    @Query("SELECT * FROM recipe_table")
    LiveData<List<RecipeWithIngredients>> getRecipesWithIngredients(); //TODO: google for n:m queries!!!

}
