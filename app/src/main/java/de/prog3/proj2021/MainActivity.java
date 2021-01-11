package de.prog3.proj2021;

/*
*
* Abschlussprojekt Programmierung III WiSe 2020/21
* Prüfer:   Prof. Dr.-Ing. R. Roosmann
*
* Autoren:  Giuseppe Buccellato,    MatNr. 889000
*           Eric Walter,            MatNr. 883921
*
* Titel:    Food Scout - Resteverwerter App
*
* */

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.FavouriteList;
import de.prog3.proj2021.db.FavouriteListDao;
import de.prog3.proj2021.db.Ingredient;
import de.prog3.proj2021.db.IngredientDao;
import de.prog3.proj2021.db.RecipeDao;
import de.prog3.proj2021.db.ShoppingListDao;
import de.prog3.proj2021.db.User;
import de.prog3.proj2021.db.UserDao;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create an instance of the database and preload with data
        //TODO: create preloaded database asset or file to insert data
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Sample.db")
                //.createFromAsset("database/myapp.db")
                //.createFromFile("database/myapp")
                .build();

        //instantiation of the Data Access Objects
        UserDao userDao = db.userDao();
        FavouriteListDao favouriteListDao = db.favouriteListDao();
        RecipeDao recipeDao = db.recipeDao();
        IngredientDao ingredientDao = db.ingredientDao();
        ShoppingListDao shoppingListDao = db.shoppingListDao();

        //example usage of database interaction
        User fritz = new User(1,"fritz");
        FavouriteList fav = new FavouriteList(1);

        userDao.insertUser(fritz);
        favouriteListDao.insertFavouriteList(fav);

        Single<User> user = userDao.getUserById(fritz.getUserId());


        userDao.deleteUser(fritz);
        favouriteListDao.deleteFavouriteList(fav);

    }
}