package de.prog3.proj2021.db;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import de.prog3.proj2021.models.ShoppingList;
import de.prog3.proj2021.models.User;

/*
 * 1:n Relation reference class for User and ShoppingList
 *
 * */

public class UserWithShoppingLists {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )
    public List<ShoppingList> shoppingLists;
}
