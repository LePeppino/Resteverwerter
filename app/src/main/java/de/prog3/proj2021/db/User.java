package de.prog3.proj2021.db;

import android.widget.ArrayAdapter;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/*
* User has 1:1 relation to FavouriteList class.
* User has 1:n relation to ShoppingList class.
*
* */

@Entity
public class User {
    @PrimaryKey
    public int userId;

    public String username;

    @Embedded
    public FavouriteList favouriteList;

    //constructor
    public User(int userId, String username) {
        setUserId(userId);
        setUsername(username);
    }

    //getter, setter
    public int getUserId() {return userId;}
    private void setUserId(int id) {this.userId = id;}

    public String username() {return username;}
    private void setUsername(String username) {this.username = username;}

}
