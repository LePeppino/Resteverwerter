package de.prog3.proj2021.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class ShoppingList {
    @PrimaryKey
    public int id;

    public String name;
    public int numUncheckedItems;
    public ArrayList<Ingredient> uncheckedItems;
    public ArrayList<Ingredient> checkedItems;

}
