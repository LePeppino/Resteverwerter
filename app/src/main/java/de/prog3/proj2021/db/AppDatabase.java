package de.prog3.proj2021.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

//TODO: include CrossRef Entities?
@Database(entities = {
        User.class,
        FavouriteList.class,
        FavouriteRecipeCrossRef.class,
        Recipe.class,
        RecipeIngredientCrossRef.class,
        Ingredient.class,
        ShoppingList.class,
        ShoppingListIngredientCrossRef.class},
        version = 1,
        exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FavouriteListDao favouriteListDao();
    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract ShoppingListDao shoppingListDao();

}
