package de.prog3.proj2021.viewmodels;

/*
 * This ViewModel class retrieves data from
 * IngredientRepository and passes it to the UI
 *
 * File author: Giuseppe Buccellato
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.IngredientWithRecipes;
import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.repositories.IngredientRepository;

public class IngredientViewModel extends AndroidViewModel {

    private final IngredientRepository ingredientRepository;
    private final LiveData<List<Ingredient>> mIngredients;
    private final List<IngredientWithRecipes> mIngredientWithRecipes;

    public IngredientViewModel(@NonNull Application application){
        super(application);
        ingredientRepository = new IngredientRepository(application);
        mIngredients = ingredientRepository.getIngredients();
        mIngredientWithRecipes = ingredientRepository.getIngredientWithRecipes();
    }

    public void insert(Ingredient ingredient){
        ingredientRepository.insert(ingredient);
    }
    public void update(Ingredient ingredient){
        ingredientRepository.update(ingredient);
    }
    public void delete(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }

    public LiveData<List<Ingredient>> getMIngredients(){
        return mIngredients;
    }

    public List<IngredientWithRecipes> getMIngredientWithRecipes(){
        return mIngredientWithRecipes;
    }
    public LiveData<List<Ingredient>> getMIngredientsByQuery(String query){
        return ingredientRepository.getMIngredientsByQuery(query);
    }
    public Ingredient getSingleIngredientByQuery(String query){
        return ingredientRepository.getSingleIngredientByQuery(query);
    }
}
