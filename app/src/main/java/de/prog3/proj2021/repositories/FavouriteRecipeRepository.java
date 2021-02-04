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

public class FavouriteRecipeRepository {

    private List<FavouritesWithRecipes> dataSet;
    private final FavouriteListDao favouriteListDao;

    //constructor
    public FavouriteRecipeRepository(Application application){
        AppDatabase favouriteListDB = AppDatabase.getInstance(application);
        favouriteListDao = favouriteListDB.favouriteListDao();
        updateFavouritesWithRecipes();
    }

    //getter and setter for favouriteList dataSet
    public List<FavouritesWithRecipes> getFavouritesWithRecipes() {
        return dataSet;
    }

    private void updateFavouritesWithRecipes(){
        dataSet = favouriteListDao.getFavouritesWithRecipes();
    }

    /*
     * database operations communicating with FavouriteListDao
     * update() is to update the numOfFavourites on the only existing instance of FavouriteList
     */
    public void update(FavouriteList favouriteList){
        favouriteListDao.update(favouriteList);
        System.out.println("favouriteList updated");
    }

    public void insertCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.insertFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef inserted");
    }

    public void updateCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.updateFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef updated");
    }

    public void deleteCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.deleteFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef deleted");
    }

    /*
     * getters for different queries here
     */


}
