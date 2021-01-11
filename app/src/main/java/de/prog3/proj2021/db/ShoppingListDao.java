package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertShoppingList(ShoppingList shoppingList);

    @Update
    Completable updateShoppingList(ShoppingList shoppingList);

    @Delete
    Completable deleteShoppingList(ShoppingList shoppingList);

}
