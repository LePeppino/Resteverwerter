package de.prog3.proj2021.db;

/*
* Database class for App
*
* File author: Giuseppe Buccellato
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

@Database(entities = {
        User.class,
        FavouriteList.class,
        FavouriteRecipeCrossRef.class,
        Recipe.class,
        RecipeIngredientCrossRef.class,
        Ingredient.class,
        ShoppingList.class,
        ShoppingListIngredientCrossRef.class},
        version = 3,
        exportSchema = false)

@TypeConverters({DataConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract FavouriteListDao favouriteListDao();
    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract ShoppingListDao shoppingListDao();

    private static AppDatabase instance;

    //create an instance of the database and preload with data
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            //.addCallback(roomCallback)
                            .createFromAsset("database/food_scout.db")
                            .build();
                }
            }
        }
        return instance;
    }

    /*
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new thread
            Runnable runnable = () -> {

            };
            new Thread(runnable).start();
        }
    };
    */

}
