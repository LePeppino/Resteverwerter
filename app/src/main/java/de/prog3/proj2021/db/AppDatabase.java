package de.prog3.proj2021.db;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//https://stackoverflow.com/questions/52187050/android-multiple-database-one-project
//https://stackoverflow.com/questions/57473172/android-room-one-database-with-multiple-tables

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
    //TODO: create preloaded database asset or file to insert data
//    AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Sample.db")
//            //.createFromAsset("database/myapp.db")
//            //.createFromFile("database/myapp")
//            .build();

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class, "AppDatabase.db")
                    .build();
        }
        return instance;
    }

    //Queries:
    //AppDatabase.getDatabase(getApplicationContext()).UserDao().getAll();

    //instantiation of the Data Access Objects
//    UserDao userDao = db.userDao();
//    FavouriteListDao favouriteListDao = db.favouriteListDao();
//    RecipeDao recipeDao = db.recipeDao();
//    IngredientDao ingredientDao = db.ingredientDao();
//    ShoppingListDao shoppingListDao = db.shoppingListDao();

}
