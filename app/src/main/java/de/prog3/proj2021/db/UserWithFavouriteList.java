package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.User;

/**
* 1:1 Relation reference class for User and FavouriteList
*
* @author Giuseppe Buccellato
*/

public class UserWithFavouriteList {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "id",            //primary key for parent entity User
            entityColumn = "userOwnerId"    //primary key reference from child entity FavouriteList
    )
    public FavouriteList favouriteList;
}
