package de.prog3.proj2021.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
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

    @Query("SELECT username FROM user")
    String getUsername();

    @Query("SELECT favouriteRecipes FROM user")
    List<Recipe> getFavourites();

}
