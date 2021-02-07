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
        parentColumns = "id",
        childColumns = "userOwnerId",
        onDelete = CASCADE))
public class FavouriteList {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private int numOfFavourites;

    //for reference to parent entity
    public int userOwnerId;

    //constructor
    public FavouriteList(int numOfFavourites){
        setNumOfFavourites(numOfFavourites);
    }

    //getter, setter
    public int getId(){ return id;}
    public void setId(int id){ this.id = id;}

    public int getNumOfFavourites(){return numOfFavourites;}
    public void setNumOfFavourites(int numOfFavourites){this.numOfFavourites = numOfFavourites;}
}
