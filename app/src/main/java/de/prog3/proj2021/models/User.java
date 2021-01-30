package de.prog3.proj2021.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* User has 1:1 relation to FavouriteList class.
* User has 1:n relation to ShoppingList class.
*
* */

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int userId;

    public String username;

    @Embedded
    public FavouriteList favouriteList;

    //constructor
    public User(String username) {
        setUsername(username);
    }

    //getter, setter
    public int getUserId() {return userId;}
    private void setUserId(int id) {this.userId = id;}

    public String username() {return username;}
    private void setUsername(String username) {this.username = username;}

}
