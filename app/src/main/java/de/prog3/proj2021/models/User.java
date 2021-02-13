package de.prog3.proj2021.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* User has 1:1 relation to FavouriteList class.
* User has 1:n relation to ShoppingList class.
*
* File author: Giuseppe Buccellato
* */

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uId;

    @NonNull
    public String username = "";

    //constructor
    public User(String username) {
        setUsername(username);
    }

    //getter, setter
    public int getuId() {return uId;}
    private void setuId(int uId) {this.uId = uId;}

    public String username() {return username;}
    private void setUsername(@NonNull String username) {this.username = username;}

}
