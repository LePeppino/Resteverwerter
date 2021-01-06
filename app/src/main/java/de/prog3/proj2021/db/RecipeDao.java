package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);

    @Delete
    void deleteRecipe(Recipe... recipes);

    @Query("SELECT ingredientList as Ingredients FROM Recipe")
    List<Ingredient> getIngredients();

    @Query("SELECT name FROM Recipe")
    String getRecipeName();

    @Query("SELECT calories FROM Recipe")
    int getCalories();

    //TODO: Queries for recipe description, instructions and main photo
}
