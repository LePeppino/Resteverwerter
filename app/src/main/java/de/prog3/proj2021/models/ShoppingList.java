package de.prog3.proj2021.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;


/*
* ShoppingList has 1:n relation to User class.
* ShoppingList has n:m relation to Ingredient class.
*
* File author: Giuseppe Buccellato
* */

@Entity(tableName = "shoppingList_table",
        foreignKeys =
        @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userCreatorId",
        onDelete = CASCADE))
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name = "";
    public int numUncheckedItems;

    public int userCreatorId; //for reference to parent entity

    //constructor
    public ShoppingList(String name,
                        int numUncheckedItems)
    {
        setName(name);
        setNumUncheckedItems(numUncheckedItems);
    }

    //getter, setter
    public int getId() {
        return id;
    }
    public @NonNull String getName() {
        return name;
    }
    public int getNumUncheckedItems() {
        return numUncheckedItems;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    public void setNumUncheckedItems(int numUncheckedItems) {
        this.numUncheckedItems = numUncheckedItems;
    }

}
