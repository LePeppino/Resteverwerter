package de.prog3.proj2021.models;

/*
 * FavouriteList has 1:1 relation to User class.
 * FavouriteList has n:m relation to Recipe class.
 *
 * File author: Giuseppe Buccellato
 * */

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "favouriteList_table",
        foreignKeys =
        @ForeignKey(entity = User.class,
        parentColumns = "uId",
        childColumns = "userOwnerId",
        onDelete = CASCADE))
public class FavouriteList {
    @PrimaryKey(autoGenerate = true)
    public int fId;

    private int numOfFavourites;

    //for reference to parent entity
    public int userOwnerId;

    //constructor
    public FavouriteList(int numOfFavourites){
        setNumOfFavourites(numOfFavourites);
    }

    //getter, setter
    public int getfId(){ return fId;}
    public void setfId(int fId){ this.fId = fId;}

    public int getNumOfFavourites(){return numOfFavourites;}
    public void setNumOfFavourites(int numOfFavourites){this.numOfFavourites = numOfFavourites;}
}
