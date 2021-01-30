package de.prog3.proj2021.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


/*
* ShoppingList has 1:n relation to User class.
* ShoppingList has n:m relation to Ingredient class.
* */

@Entity(tableName = "shoppingList_table")
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    public int shoppingListId;

    public int userCreatorId; //for reference to parent entity

    public String name;
    public int numUncheckedItems;

    //constructor
    public ShoppingList(String name,
                        int numUncheckedItems)
    {
        setName(name);
        setNumUncheckedItems(numUncheckedItems);
    }

    //getter, setter
    public int getShoppingListId() {
        return shoppingListId;
    }
    public String getName() {
        return name;
    }
    public int getNumUncheckedItems() {
        return numUncheckedItems;
    }

    public void setShoppingListId(int shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumUncheckedItems(int numUncheckedItems) {
        this.numUncheckedItems = numUncheckedItems;
    }

}
