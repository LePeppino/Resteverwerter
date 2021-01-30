package de.prog3.proj2021.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.repositories.RecipeRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> mRecipes;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
        mRecipes = recipeRepository.getRecipes();
    }

    public void insert(Recipe recipe){
        recipeRepository.insert(recipe);
    }

    public void update(Recipe recipe){
        recipeRepository.update(recipe);
    }

    public void delete(Recipe recipe){
        recipeRepository.delete(recipe);
    }

    public LiveData<List<Recipe>> getmRecipes(){
        return mRecipes;
    }
}
