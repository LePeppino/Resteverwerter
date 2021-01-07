package de.prog3.proj2021.db;

import android.widget.ArrayAdapter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @PrimaryKey
    public int id;

    public String username;
    public ArrayList<Recipe> favouriteRecipes;

    //constructor
    public User(int id, String username) {
        setId(id);
        setUsername(username);
        setFavouriteRecipes(favouriteRecipes);
    }

    //getter, setter
    public int getId() {return id;}
    private void setId(int id) {this.id = id;}

    public String username() {return username;}
    private void setUsername(String username) {this.username = username;}

    public List<Recipe> getFavouriteRecipes() {return favouriteRecipes;}
    private void setFavouriteRecipes(ArrayList<Recipe> favouriteRecipes) {this.favouriteRecipes = favouriteRecipes;}
}
