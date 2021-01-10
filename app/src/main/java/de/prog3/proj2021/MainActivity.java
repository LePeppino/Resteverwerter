package de.prog3.proj2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.FavouriteListDao;
import de.prog3.proj2021.db.Ingredient;
import de.prog3.proj2021.db.IngredientDao;
import de.prog3.proj2021.db.RecipeDao;
import de.prog3.proj2021.db.ShoppingListDao;
import de.prog3.proj2021.db.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create an instance of the database and preload with data
        //TODO: create preloaded database asset or file to insert data
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Sample.db")
                //.createFromAsset("database/myapp.db")
                .build();

        //instantiation of the Data Access Objects
        UserDao userDao = db.userDao();
        FavouriteListDao favouriteListDao = db.favouriteListDao();
        RecipeDao recipeDao = db.recipeDao();
        IngredientDao ingredientDao = db.ingredientDao();
        ShoppingListDao shoppingListDao = db.shoppingListDao();

        //userDao.getUsername(); //example usage of database interaction

    }
}