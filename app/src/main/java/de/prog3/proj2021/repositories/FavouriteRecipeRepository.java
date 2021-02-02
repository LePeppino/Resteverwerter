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

public class FavouriteRecipeRepository {

    private LiveData<List<FavouritesWithRecipes>> dataSet;
    private final FavouriteListDao favouriteListDao;

    //constructor
    public FavouriteRecipeRepository(Application application){
        AppDatabase favouriteListDB = AppDatabase.getInstance(application);
        favouriteListDao = favouriteListDB.favouriteListDao();
        updateFavouritesWithRecipes();
    }

    //getter and setter for favouriteList dataSet
    public LiveData<List<FavouritesWithRecipes>> getFavouritesWithRecipes() {
        return dataSet;
    }

    private void updateFavouritesWithRecipes(){
        dataSet = favouriteListDao.getFavouritesWithRecipes();
    }

    /*
     * database operations communicating with FavouriteListDao
     */
    public void insert(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.insertFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef inserted");
    }

    public void update(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.updateFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef updated");
    }

    public void delete(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.deleteFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef deleted");
    }

    /*
     * getters for different queries here
     */


}
