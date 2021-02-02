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
import androidx.room.Update;

import java.util.List;

import de.prog3.proj2021.models.ShoppingList;

@Dao
public interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingList(ShoppingList shoppingList);

    @Update
    void updateShoppingList(ShoppingList shoppingList);

    @Delete
    void deleteShoppingList(ShoppingList shoppingList);

    @Query("SELECT * FROM shoppingList_table")
    LiveData<List<ShoppingList>> getAllShoppingLists();

}
