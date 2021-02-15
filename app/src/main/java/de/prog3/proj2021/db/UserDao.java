package de.prog3.proj2021.db;

/**
 * Data Access Object for User Model
 * This DAO is NOT currently in use for the application
 * as there is not yet enough functionality available
 * to make proper use of it
 *
 * @author Giuseppe Buccellato
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

import de.prog3.proj2021.models.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Transaction
    @Query("SELECT * FROM user_table")
    LiveData<List<UserWithFavouriteList>> getUserWithFavouriteList();

    @Transaction
    @Query("SELECT * FROM user_table")
    LiveData<List<UserWithShoppingLists>> getUserWithShoppingLists();

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getUsers();

    @Query("SELECT * FROM user_table WHERE id = :userId")
    LiveData<List<User>> getUserById(int userId);
}
