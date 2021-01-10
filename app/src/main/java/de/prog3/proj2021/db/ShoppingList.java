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

    //constructor
    public ShoppingList(int id,
                        String name,
                        int numUncheckedItems,
                        ArrayList<Ingredient> uncheckedItems,
                        ArrayList<Ingredient> checkedItems)
    {
        setId(id);
        setName(name);
        setNumUncheckedItems(numUncheckedItems);
        setUncheckedItems(uncheckedItems);
        setCheckedItems(checkedItems);
    }

    //getter, setter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getNumUncheckedItems() {
        return numUncheckedItems;
    }
    public ArrayList<Ingredient> getUncheckedItems() {
        return uncheckedItems;
    }
    public ArrayList<Ingredient> getCheckedItems() {
        return checkedItems;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumUncheckedItems(int numUncheckedItems) {
        this.numUncheckedItems = numUncheckedItems;
    }
    public void setUncheckedItems(ArrayList<Ingredient> uncheckedItems) {
        this.uncheckedItems = uncheckedItems;
    }
    public void setCheckedItems(ArrayList<Ingredient> checkedItems) {
        this.checkedItems = checkedItems;
    }
}
