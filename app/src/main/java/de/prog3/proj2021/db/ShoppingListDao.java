package de.prog3.proj2021.db;

/*
 * Data Access Object for ShoppingList Model
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

import de.prog3.proj2021.models.ShoppingList;

@Dao
public interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingList(ShoppingList shoppingList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingListWithIngredients(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef);

    @Update
    void updateShoppingList(ShoppingList shoppingList);

    @Update
    void updateShoppingListWithIngredients(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef);

    @Delete
    void deleteShoppingList(ShoppingList shoppingList);

    @Delete
    void deleteShoppingListWithIngredients(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef);

    @Transaction
    @Query("SELECT * FROM shoppingList_table WHERE userCreatorId = 1")
    LiveData<List<ShoppingListWithIngredients>> getShoppingListWithIngredients();

}
