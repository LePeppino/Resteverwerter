package de.prog3.proj2021.db;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Recipe {
    @PrimaryKey
    public int id;

    public String name;
    public int calories;
    public ArrayList<Ingredient> ingredientList;

    @Ignore
    Bitmap headerImage;

    //public String description;
    //public String instructions;

}
