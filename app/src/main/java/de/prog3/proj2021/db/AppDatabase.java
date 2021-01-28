package de.prog3.proj2021.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    private static AppDatabase instance;

    //create an instance of the database and preload with data
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                            .fallbackToDestructiveMigration()
                            //.addCallback(roomCallback)
                            //.createFromAsset("database/food_scout.db")
                            .build();
                }
            }
        }
        return instance;
    }

//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//
//        }
//    };

}
