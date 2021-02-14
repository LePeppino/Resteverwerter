package de.prog3.proj2021.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
* Recipe has n:m relation to FavouriteList class.
* Recipe has n:m relation to Ingredient class.
*
* @author Giuseppe Buccellato
*
* */
/**
 * create correlation between table entry and POJO
 */
@Entity(tableName = "recipe_table")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name = "";
    public int calories;
    public int isFavourite;             //bool value: 0 = false, 1 = true
    @NonNull
    public String description = "";
    @NonNull
    public String instructions = "";
    @NonNull
    public String headerImageUrl = "";

    //constructor
    public Recipe(String name,
                  int calories,
                  int isFavourite,
                  String description,
                  String instructions,
                  String headerImageUrl)
    {
        setName(name);
        setCalories(calories);
        setFavourite(isFavourite);
        setDescription(description);
        setInstructions(instructions);
        setHeaderImageUrl(headerImageUrl);
    }

    /**
     * gets id
     * @return id
     */
    public int getId() {return id;}

    private void setId(int id) {this.id = id;}

    /**
     * gets name
     * @return name
     */
    public @NonNull String getName() {return name;}

    /**
     * sets name
     * @param name to be set
     */
    private void setName(@NonNull String name) {this.name = name;}

    /**
     * gets calories
     * @return calories
     */
    public int getCalories() {return calories;}

    /**
     * sets calories
     * @param calories to be set
     */
    private void setCalories(int calories) {this.calories = calories;}

    /**
     * gets favourite status
     * @return favourite status
     */
    public int isFavourite() {
        return isFavourite;
    }

    /**
     * sets favourite status
     * @param favourite status
     */
    public void setFavourite(int favourite) {
        isFavourite = favourite;
    }

    /**
     * gets description
     * @return description
     */
    public @NonNull String getDescription() {
        return description;
    }

    /**
     * sets description
     * @param description
     */
    private void setDescription(@NonNull String description) {
        this.description = description;
    }

    /**
     * gets instructions
     * @return instructions
     */
    public @NonNull String getInstructions() {
        return instructions;
    }

    /**
     * sets instructions
     * @param instructions to set
     */
    private void setInstructions(@NonNull String instructions) {
        this.instructions = instructions;
    }

    /**
     * gets image url
     * @return string of url
     */
    public @NonNull String getHeaderImageUrl() {
        return headerImageUrl;
    }

    /**
     * sets the image url
     * @param headerImageUrl
     */
    private void setHeaderImageUrl(@NonNull String headerImageUrl) {
        this.headerImageUrl = headerImageUrl;
    }

}
