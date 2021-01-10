package de.prog3.proj2021.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * Ingredient has n:m relation to Recipe class.
 * Ingredient has n:m relation to ShoppingList class.
 *
 * */

@Entity
public class Ingredient {
    @PrimaryKey
    public int ingredientId;

    public String name;
    public int numAvailable;        //for pantry function
    public int numRequired;         //for recipes
    public int numToBuy;            //for shopping list
    public boolean isCheckedOnShoppingList;
    public Unit unit;
    public Type type;

    public enum Unit {
        g,
        ml
    }

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
    public Ingredient(int ingredientId,
                      String name,
                      int numAvailable,
                      int numRequired,
                      int numToBuy,
                      boolean isCheckedOnShoppingList,
                      Unit unit,
                      Type type)
    {
        setId(ingredientId);
        setName(name);
        setNumAvailable(numAvailable);
        setNumRequired(numRequired);
        setNumToBuy(numToBuy);
        setCheckedOnShoppingList(isCheckedOnShoppingList);
        setUnit(unit);
        setType(type);
    }

    //getter, setter
    public int getIngredientId() {
        return ingredientId;
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
    public boolean isCheckedOnShoppingList(){
        return isCheckedOnShoppingList;
    }
    public Unit getUnit() {
        return unit;
    }
    public Type getType() {
        return type;
    }

    public void setId(int ingredientId) {
        this.ingredientId = ingredientId;
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
    public void setCheckedOnShoppingList(boolean isCheckedOnShoppingList){
        this.isCheckedOnShoppingList = isCheckedOnShoppingList;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    public void setType(Type type) {
        this.type = type;
    }

}
