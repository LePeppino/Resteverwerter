package de.prog3.proj2021.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.Recipe;
import de.prog3.proj2021.db.RecipeDao;
import de.prog3.proj2021.db.RecipeWithIngredients;

/*
* This class interacts with the Recipe Data Access Object to retrieve
* */

public class RecipeRepository {

    private RecipeDao recipeDao;
    private static RecipeRepository instance;
    private List<Recipe> dataSet = new ArrayList<>();

    //constructor
    public RecipeRepository(Application application){
        AppDatabase recipeDB = AppDatabase.getDatabase(application);
        recipeDao = recipeDB.recipeDao();
    }

    public RecipeRepository() {}

    /*
    * Singleton pattern
    * */
    public static RecipeRepository getInstance(){
        if(instance == null){
            instance = new RecipeRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Recipe>> getRecipes(){
        setRecipes();

        MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    /*
    * Queries recipe data from database into dataSet Array
    * */
    private void setRecipes(){
        //TODO: Query database
        //insert data to dataSet Array
        Recipe testrecipe = new Recipe(1, "döner", 500, false, "legga", "essen", "");
        recipeDao.insertRecipe(testrecipe); //TODO: find mistake...

        //dataSet = recipeDao.getRecipes();
    }
}
