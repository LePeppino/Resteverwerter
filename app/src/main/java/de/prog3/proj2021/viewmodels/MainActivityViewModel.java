package de.prog3.proj2021.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.db.Recipe;
import de.prog3.proj2021.repositories.RecipeRepository;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Recipe>> mRecipes;

    public void initRecipe(){
        if(mRecipes != null){
            return;
        }
        RecipeRepository mRepo = RecipeRepository.getInstance();
        mRecipes = mRepo.getRecipes();
    }

    public MutableLiveData<List<Recipe>> getmRecipes(){
        return mRecipes;
    }
}
