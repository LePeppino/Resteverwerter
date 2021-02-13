package de.prog3.proj2021.db;

/*
 * Data Access Object for Recipe Model
 * and for RecipeWithIngredientCrossRef
 *
 * File author: Giuseppe Buccellato
 */

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef);

    @Update
    void updateRecipeIngredientCrossRef(RecipeIngredientCrossRef recipeIngredientCrossRef);

    @Delete
    void deleteRecipeIngredientCrossRef(RecipeIngredientCrossRef... recipeIngredientCrossRef);

    @Query("SELECT * FROM recipe_table ORDER BY name ASC")
    LiveData<List<Recipe>> getRecipesASC();

    @Query("SELECT * FROM recipe_table WHERE name LIKE :query ORDER BY name ASC")
    LiveData<List<Recipe>> getRecipesByQuery(String query);

    @Query("SELECT * FROM recipe_table WHERE name LIKE :query ORDER BY name ASC")
    LiveData<Recipe> getSingleRecipeByQuery(String query);

    @Transaction
    @Query("SELECT * FROM recipe_table where rId = :id")
    LiveData<RecipeWithIngredients> getRecipeWithIngredientsById(int id);

    @Transaction
    @Query("SELECT * FROM recipe_table r\n" +
            "join recipeIngredientCrossRef_table x on r.rId = x.recipeId\n" +
            "join ingredient_table i on i.iId = x.ingredientId\n" +
            "order by name asc")
    LiveData<List<RecipeWithIngredients>> getRecipesWithIngredients();

}
