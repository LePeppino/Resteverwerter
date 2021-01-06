package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface IngredientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(Ingredient ingredient);

    @Update
    void updateIngredient(Ingredient ingredient);

    @Delete
    void deleteIngredient(Ingredient... ingredients); //attempts to delete one or more ingredients

    @Query("SELECT name FROM Ingredient")
    String getIngredientName();

    @Query("SELECT numAvailable FROM Ingredient")
    int getNumAvailable();

    @Query("SELECT numRequired FROM Ingredient")
    int getNumRequired();

    @Query("SELECT numToBuy FROM Ingredient")
    int getNumToBuy();

    @Query("SELECT type FROM Ingredient")
    Ingredient.Type getType();

    @Query("SELECT unit FROM Ingredient")
    Ingredient.Unit getUnit();
}
