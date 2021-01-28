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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
import de.prog3.proj2021.db.Recipe;
import de.prog3.proj2021.viewmodels.MainActivityViewModel;
import io.github.mthli.sugartask.SugarTask;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mMainActivityViewModel;
    RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    RecyclerView recipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate ViewModels
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //initiate ViewModels
        mMainActivityViewModel.initRecipes();

        // Passes LiveData from MainActivityViewModel to RecipeRecyclerViewAdapter
        initRecyclerView();

        //Observe ViewModel for changes
        mMainActivityViewModel.getmRecipes().observe(this, recipes -> { //Observable lambda expression
            recipeRecyclerViewAdapter.setmRecipes(recipes);
            //recipeRecyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Recipe View Model changed", Toast.LENGTH_SHORT).show();
        });

    }

    /*
    * initialise RecipeRecyclerView with Adapter
    * Queries recipes from RecipeRepository and passes data to RecipeRecyclerViewAdapter
    * */
    private void initRecyclerView(){
        recipeRecyclerView = findViewById(R.id.recipeRecyclerView);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recipeRecyclerView.setHasFixedSize(true);

        //TODO: getValue always returns null, even if data is there. WHY.
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(this, mMainActivityViewModel.getmRecipes().getValue());
        recipeRecyclerView.setAdapter(recipeRecyclerViewAdapter);

    }
}