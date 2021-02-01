package de.prog3.proj2021.models;

/*
 * Ingredient has n:m relation to Recipe class.
 * Ingredient has n:m relation to ShoppingList class.
 *
 * File author: Giuseppe Buccellato
 *
 * */

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredient_table")
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public int ingredientId;

    @NonNull
    public String name = "";
    public int unit;                        //for Unit enum (uses converters)
    public int type;                        //for Type enum (uses converters)
    public int numAvailable;                //for pantry function
    public int numRequired;                 //for recipes
    public int numToBuy;                    //for shopping list
    public int isCheckedOnShoppingList;     //bool value: 0 = false, 1 = true

    //constructor
    public Ingredient(String name,
                      int unit,
                      int type,
                      int numAvailable,
                      int numRequired,
                      int numToBuy,
                      int isCheckedOnShoppingList)
    {
        setName(name);
        setUnit(unit);
        setType(type);
        setNumAvailable(numAvailable);
        setNumRequired(numRequired);
        setNumToBuy(numToBuy);
        setCheckedOnShoppingList(isCheckedOnShoppingList);
    }

    //getter, setter
    public int getIngredientId() {
        return ingredientId;
    }
    public @NonNull String getName() {
        return name;
    }
    public int getUnit() {
        return unit;
    }
    public int getType() {
        return type;
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
    public int isCheckedOnShoppingList(){
        return isCheckedOnShoppingList;
    }


    public void setId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
    public void setName(@NonNull String name) {
        this.name = name;
    }
    public void setUnit(int unit) {
        this.unit = unit;
    }
    public void setType(int type) {
        this.type = type;
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
    public void setCheckedOnShoppingList(int isCheckedOnShoppingList){
        this.isCheckedOnShoppingList = isCheckedOnShoppingList;
    }

    /*
     * Enums for Ingredient Unit and Type
     * with constructors and getters
     * and methods in DataConverter class
     * */
    public enum Unit {
        g(1),
        ml(2),
        pcs(3);

        public final int unitValue;

        //enum constructor
        Unit(int newUnitValue){
            unitValue = newUnitValue;
        }

        //enum getter
        public int getUnitValue(){
            return unitValue;
        }
    }

    public enum Type {
        DAIRY(1),
        VEGETABLES(2),
        FRUITS(3),
        GRAINS(4),
        SPICES(5),
        MEATS(6),
        FISH(7),
        SEAFOOD(8),
        CONDIMENTS(9),
        DAIRY_ALTERNATES(10),
        SWEETS(11),
        BEVERAGES(12);

        public final int typeValue;

        //enum constructor
        Type(int newTypeValue){
            typeValue = newTypeValue;
        }

        //enum getter
        public int getTypeValue(){
            return typeValue;
        }
    }

}
