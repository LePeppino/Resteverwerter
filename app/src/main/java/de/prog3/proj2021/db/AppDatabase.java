package de.prog3.proj2021.db;

/**
* Database class for App
*
* @author Giuseppe Buccellato, Eric Walter
*/

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.prog3.proj2021.converters.DataConverter;
import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.models.ShoppingList;
import de.prog3.proj2021.models.User;

/**
 * Database Annotation with declaration of Entities.
 * Version number increased during the development of this app.
 * exportSchema was never used.
 */
@Database(entities = {
        User.class,
        FavouriteList.class,
        FavouriteRecipeCrossRef.class,
        Recipe.class,
        RecipeIngredientCrossRef.class,
        Ingredient.class,
        ShoppingList.class,
        ShoppingListIngredientCrossRef.class},
        version = 5, //TODO: needs to be incremented every time the db schema is altered
        exportSchema = false)

/**
 * Type converters
 * CURRENTLY NOT IN USE
 */
@TypeConverters({DataConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FavouriteListDao favouriteListDao();
    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract ShoppingListDao shoppingListDao();

    private static AppDatabase instance;

    /**
     * create an instance of the database and preload with data
     * also, implemented with singleton pattern so only one instance ever exists
     * @param context that is to be passed from Application
     * @return instance
     */
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            /**
                             * destructive migration permanently deletes
                             * all previous data when updating database
                             * version number due to changes to database schema!
                             * currently disabled for demonstration purposes
                             */
                            //.fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .createFromAsset("database/food_scout.db")
                            .build();
                }
            }
        }
        return instance;
    }

}
