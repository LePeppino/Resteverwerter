package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingList(ShoppingList shoppingList);

    @Update
    void updateShoppingList(ShoppingList shoppingList);

    @Delete
    void deleteShoppingList(ShoppingList shoppingList);

    @Query("SELECT name FROM ShoppingList")
    String getShoppingListName();

    @Query("SELECT numUncheckedItems FROM ShoppingList")
    int getNumUncheckedItems();

    @Query("SELECT uncheckedItems FROM ShoppingList")
    List<Ingredient> getUncheckedItems();

    @Query("SELECT checkedItems FROM ShoppingList")
    List<Ingredient> getCheckedItems();
}
