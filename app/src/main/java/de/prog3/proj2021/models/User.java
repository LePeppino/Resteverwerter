package de.prog3.proj2021.models;

/**
 * User has 1:1 relation to FavouriteList class.
 * User has 1:n relation to ShoppingList class.
 *
 * @author Giuseppe Buccellato, Eric Walter
 * */

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * create correlation between table entry and POJO
 */
@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String username = "";

    /**
     * constructor
     */
    public User(String username) {
        setUsername(username);
    }

    /**
     * getter, setter
     */
    public int getId() {return id;}
    private void setId(int id) {this.id = id;}

    public @NonNull String getUsername() {return username;}
    private void setUsername(@NonNull String username) {this.username = username;}

}
