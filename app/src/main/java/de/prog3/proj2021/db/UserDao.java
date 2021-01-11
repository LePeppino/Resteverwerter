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
import io.reactivex.Single;

/*
* UserDao uses RxJava framework for asynchronous queries
* with return types Completable and Single<T>

* */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    @Update
    Completable updateUserInfo(User user);

    @Delete
    Completable deleteUser(User user);

    // Transaction to return all instances for the relation
    // UserWithFavouriteList atomically
    @Transaction
    @Query("SELECT * FROM User")
    Single<List<UserWithFavouriteList>> getUsersWithFavouriteLists();

    @Transaction
    @Query("SELECT * FROM User")
    Single<List<UserWithShoppingLists>> getUserWithShoppingLists();

    @Query("SELECT * FROM User WHERE userId = :userId")
    Single<User> getUserById(int userId);
}
