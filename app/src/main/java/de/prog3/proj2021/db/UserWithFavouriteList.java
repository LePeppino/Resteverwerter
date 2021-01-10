package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Relation;

/*
* 1:1 Relation reference class for User and FavouriteList
*
* */

public class UserWithFavouriteList {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId", //primary key for parent entity User
            entityColumn = "userOwnerId" //primary key reference from child entity FavouriteList
    )
    public FavouriteList favouriteList;
}
