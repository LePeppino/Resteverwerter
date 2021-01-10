package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateUserInfo(User user);

    @Delete
    void deleteUser(User user);

    // Transaction to return all instances for the relation
    // UserWithFavouriteList atomically
    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithFavouriteList> getUsersWithFavouriteLists();

    @Transaction
    @Query("SELECT * FROM User")
    public List<UserWithShoppingLists> getUserWithShoppingLists();
}
