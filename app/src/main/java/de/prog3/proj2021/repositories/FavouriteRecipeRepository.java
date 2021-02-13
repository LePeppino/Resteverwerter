package de.prog3.proj2021.repositories;

/*
 * This class interacts with the Recipe DAO
 * to retrieve and cache recipes from database
 * to pass them to a ViewModel
 *
 * File author: Giuseppe Buccellato
 * */

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.FavouriteListDao;
import de.prog3.proj2021.db.FavouriteRecipeCrossRef;
import de.prog3.proj2021.db.FavouritesWithRecipes;
import de.prog3.proj2021.models.FavouriteList;
import de.prog3.proj2021.models.Recipe;

public class FavouriteRecipeRepository {

    private LiveData<FavouritesWithRecipes> favouriteList;

    private final FavouriteListDao favouriteListDao;

    //constructor
    public FavouriteRecipeRepository(Application application){
        AppDatabase favouriteListDB = AppDatabase.getInstance(application);
        favouriteListDao = favouriteListDB.favouriteListDao();
        updateFavouritesWithRecipes();
    }

    //setter
    private void updateFavouritesWithRecipes(){
        favouriteList = favouriteListDao.getFavouritesWithRecipesById();
    }

    //getter for dataSet
    public LiveData<FavouritesWithRecipes> getFavouriteList(){
        return favouriteList;
    }


    /*
     * database operations communicating with FavouriteListDao
     * update() updates the numOfFavourites on the only existing instance of FavouriteList
     */
    public void update(FavouriteList favouriteList){
        AppDatabase.databaseExecutor.execute(() -> {
            favouriteListDao.update(favouriteList);
        });
        System.out.println("favouriteList updated");
    }

    public void insertCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        AppDatabase.databaseExecutor.execute(() -> {
            favouriteListDao.insertFavouritesWithRecipes(favouriteRecipeCrossRef);
        });
        System.out.println("favouriteRecipeCrossRef inserted");
    }

    public void deleteCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        AppDatabase.databaseExecutor.execute(() -> {
            favouriteListDao.deleteFavouritesWithRecipes(favouriteRecipeCrossRef);
        });
        System.out.println("favouriteRecipeCrossRef deleted");
    }

    /*
     * getters for different queries here
     */
    public LiveData<List<Recipe>> getFavouriteRecipesByQuery(String query){
        return favouriteListDao.getFavouriteRecipesByQuery(query);
    }
}
