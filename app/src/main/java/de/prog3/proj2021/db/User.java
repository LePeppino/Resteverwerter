package de.prog3.proj2021.db;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class User {
    @PrimaryKey
    public int id;

    public String username;

    public ArrayList<Recipe> favouriteRecipes;

    @Ignore
    Bitmap profilePicture;

}
