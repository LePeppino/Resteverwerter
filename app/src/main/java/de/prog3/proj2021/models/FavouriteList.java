package de.prog3.proj2021.models;

/*
 * FavouriteList has 1:1 relation to User class.
 * FavouriteList has n:m relation to Recipe class.
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favouriteList_table")
public class FavouriteList {
    @PrimaryKey(autoGenerate = true)
    public int favouriteListId;

    private int numOfFavourites;

    //for reference to parent entity
    public int userOwnerId;

    //constructor
    public FavouriteList(int numOfFavourites){
        setNumOfFavourites(numOfFavourites);
    }

    //getter, setter
    public int getFavouriteListId(){ return favouriteListId;}
    public void setFavouriteListId(int favouriteListId){ this.favouriteListId = favouriteListId;}

    public int getNumOfFavourites(){return numOfFavourites;}
    private void setNumOfFavourites(int numOfFavourites){this.numOfFavourites = numOfFavourites;}
}
