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

@Dao
public interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertRecipe(Recipe recipe);

    @Update
    Completable updateRecipe(Recipe recipe);

    @Delete
    Completable deleteRecipe(Recipe... recipes);

    @Query("SELECT * FROM Recipe")
    List<Recipe> getRecipes();

    @Transaction
    @Query("SELECT * FROM Recipe")
    List<RecipeWithIngredients> getRecipesWithIngredients(); //TODO: google for n:m queries!!!

}
