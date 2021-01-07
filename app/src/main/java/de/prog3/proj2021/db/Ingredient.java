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

    //constructor
    public Ingredient(int id, String name, int numAvailable,
                      int numRequired, int numToBuy, Unit unit, Type type) {
        setId(id);
        setName(name);
        setNumAvailable(numAvailable);
        setNumRequired(numRequired);
        setNumToBuy(numToBuy);
        setUnit(unit);
        setType(type);
    }

    //getter, setter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getNumAvailable() {
        return numAvailable;
    }
    public int getNumRequired() {
        return numRequired;
    }
    public int getNumToBuy() {
        return numToBuy;
    }
    public Unit getUnit() {
        return unit;
    }
    public Type getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumAvailable(int numAvailable) {
        this.numAvailable = numAvailable;
    }
    public void setNumRequired(int numRequired) {
        this.numRequired = numRequired;
    }
    public void setNumToBuy(int numToBuy) {
        this.numToBuy = numToBuy;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public void setType(Type type) {
        this.type = type;
    }

}
