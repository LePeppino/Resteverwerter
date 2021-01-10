package de.prog3.proj2021.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* FavouriteList has 1:1 relation to User class.
* FavouriteList has n:m relation to Recipe class.
*
* */

@Entity
public class FavouriteList {
    @PrimaryKey
    public int favouriteListId;

    public int userOwnerId; //for reference to parent entity

}
