package de.prog3.proj2021;

/*
*
* Abschlussprojekt Programmierung III WiSe 2020/21
* Prüfer:   Prof. Dr.-Ing. Rainer Roosmann
*
* Autoren:  Giuseppe Buccellato,    MatNr. 889000
*           Eric Walter,            MatNr. 883921
*
* Titel:    Food Scout - Resteverwerter App
*
* */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.FavouriteList;
import de.prog3.proj2021.db.FavouriteListDao;
import de.prog3.proj2021.db.Ingredient;
import de.prog3.proj2021.db.IngredientDao;
import de.prog3.proj2021.db.Recipe;
import de.prog3.proj2021.db.RecipeDao;
import de.prog3.proj2021.db.ShoppingListDao;
import de.prog3.proj2021.db.User;
import de.prog3.proj2021.db.UserDao;
import de.prog3.proj2021.viewmodels.MainActivityViewModel;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;
    RecipeRecyclerViewAdapter mAdapter;
    RecyclerView recipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ViewModels
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        //initiate ViewModel
        mMainActivityViewModel.initRecipe();
        //Observe ViewModel for changes
        mMainActivityViewModel.getmRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                mAdapter.notifyDataSetChanged();
            }
        });

        /*
         * Queries recipes from database and passes data to RecipeRecyclerViewAdapter
         * */
        initRecyclerView();

    }

    /*
    * initialise RecipeRecyclerView with Adapter
    * Queries recipes from RecipeRepository and passes data to RecipeRecyclerViewAdapter
    * */
    private void initRecyclerView(){
        recipeRecyclerView = findViewById(R.id.recipeRecyclerView);
        mAdapter = new RecipeRecyclerViewAdapter(this, mMainActivityViewModel.getmRecipes().getValue());
        recipeRecyclerView.setAdapter(mAdapter);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}