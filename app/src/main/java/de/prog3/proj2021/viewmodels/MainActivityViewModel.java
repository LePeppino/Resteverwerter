package de.prog3.proj2021.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.db.Recipe;
import de.prog3.proj2021.repositories.RecipeRepository;

public class MainActivityViewModel extends AndroidViewModel {

    private final RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> mRecipes;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        recipeRepository = new RecipeRepository(application);
    }

    //fetch recipe LiveData from recipeRepository
    public void initRecipes(){
        if(mRecipes != null){
            return;
        }
        mRecipes = recipeRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getmRecipes(){
        return mRecipes;
    }
}
