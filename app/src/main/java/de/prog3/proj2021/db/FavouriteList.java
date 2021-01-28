package de.prog3.proj2021.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* FavouriteList has 1:1 relation to User class.
* FavouriteList has n:m relation to Recipe class.
*
* */

@Entity(tableName = "favouriteList_table")
public class FavouriteList {
    @PrimaryKey(autoGenerate = true)
    public int favouriteListId;

    public int userOwnerId; //for reference to parent entity

    //constructor
    public FavouriteList(int favouriteListId){}

    //getter, setter
    public int getFavouriteListId(){ return favouriteListId;}
    public void setFavouriteListId(int favouriteListId){ this.favouriteListId = favouriteListId;}
}
