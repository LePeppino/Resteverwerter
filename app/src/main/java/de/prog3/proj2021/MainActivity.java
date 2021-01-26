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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
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

    private ArrayList<String> mRecipeNames = new ArrayList<>();
    private ArrayList<String> mRecipeDescriptions = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


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

        //RecipeRecyclerViewAdapter
        //initRecipeList();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new FragmentHome();
                            break;
                        case R.id.nav_picker:
                            selectedFragment = new FragmentPicker();
                            break;
                        case R.id.nav_shopping_list:
                            selectedFragment = new FragmentShoppingList();
                            break;
                        case R.id.nav_favourites:
                            selectedFragment = new FragmentFavorites();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    /*
    * Queries recipes from database and passes data to RecipeRecyclerViewAdapter
    * */
    /*private void initRecipeList(){
        //TODO: Query database

        //example data
        mImageUrls.add("");
        mRecipeNames.add("Rezept #1");
        mRecipeDescriptions.add("lorem ipsum");

        mImageUrls.add("");
        mRecipeNames.add("Rezept #2");
        mRecipeDescriptions.add("lorem ipsum 2");

        initRecyclerView();
    }*/

    /*
    * initialise RecipeRecyclerView with Adapter
    * */
   /* private void initRecyclerView(){
        RecyclerView recipeRecyclerView = findViewById(R.id.recipeRecyclerView);
        RecipeRecyclerViewAdapter adapter = new RecipeRecyclerViewAdapter(this, mRecipeNames, mRecipeDescriptions, mImageUrls);
        recipeRecyclerView.setAdapter(adapter);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }*/
}