package de.prog3.proj2021.models;

/**
 * FavouriteList has 1:1 relation to User class.
 * FavouriteList has n:m relation to Recipe class.
 *
 * @author Giuseppe Buccellato, Eric Walter
 * */

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * create correlation between table entry and POJO
 */
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

    /**
     * constructor
     * @param numOfFavourites
     */
    public FavouriteList(int numOfFavourites){
        setNumOfFavourites(numOfFavourites);
    }

    /**
     * gets id
     * @return id
     */
    public int getId(){ return id;}

    /**
     * sets id
     * @param id
     */
    public void setId(int id){ this.id = id;}

    /**
     * gets number of favourites
     * @return number of favourites
     */
    public int getNumOfFavourites(){return numOfFavourites;}

    /**
     * sets number of favourites
     * @param numOfFavourites
     */
    public void setNumOfFavourites(int numOfFavourites){this.numOfFavourites = numOfFavourites;}
}
