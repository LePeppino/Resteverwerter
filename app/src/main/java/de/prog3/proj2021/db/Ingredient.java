package de.prog3.proj2021.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey
    public int id;

    public String name;
    public int numAvailable;        //for pantry function
    public int numRequired;         //for recipes
    public int numToBuy;            //for shopping list
    public Unit unit;
    public Type type;

    public enum Unit {
        g,
        ml,
        pinch}

    public enum Type {
        DAIRY,
        VEGETABLES,
        FRUITS,
        GRAINS,
        SPICES,
        MEATS,
        FISH,
        SEAFOOD,
        CONDIMENTS,
        DAIRY_ALTERNATES,
        SWEETS,
        BEVERAGES
    }
}
