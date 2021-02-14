package de.prog3.proj2021.repositories;

/**
 * This class interacts with the Recipe DAO
 * to retrieve and cache recipes from database
 * to pass them to a ViewModel
 *
 * @author Giuseppe Buccellato, Eric Walter
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

    private List<FavouritesWithRecipes> dataSet;
    private FavouritesWithRecipes favouriteList;
    private final FavouriteListDao favouriteListDao;

    /**
     * constructor
     * @param application
     */
    public FavouriteRecipeRepository(Application application){
        AppDatabase favouriteListDB = AppDatabase.getInstance(application);
        favouriteListDao = favouriteListDB.favouriteListDao();
        updateFavouritesWithRecipes();
    }

    /**
     * gets dataset with list of FavouritesWithRecipes
     * @return dataset
     */
    public List<FavouritesWithRecipes> getFavouritesWithRecipes() {
        return dataSet;
    }

    /**
     * gets FavouriteList
     * @return favouriteList
     */
    public FavouritesWithRecipes getFavouriteList(){
        return favouriteList;
    }

    private void updateFavouritesWithRecipes(){
        dataSet = favouriteListDao.getFavouritesWithRecipes();
        favouriteList = favouriteListDao.getFavouritesWithRecipesById();
    }

    /**
     * database update of favouritelist
     * @param favouriteList
     */
    public void update(FavouriteList favouriteList){
        favouriteListDao.update(favouriteList);
        System.out.println("favouriteList updated");
    }

    /**
     * database insert of FavouriteRecipeCrossRef
     * @param favouriteRecipeCrossRef
     */
    public void insertCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.insertFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef inserted");
    }
    /**
     * database update of FavouriteRecipeCrossRef
     * @param favouriteRecipeCrossRef
     */
    public void updateCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.updateFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef updated");
    }
    /**
     * database deletion of FavouriteRecipeCrossRef
     * @param favouriteRecipeCrossRef
     */
    public void deleteCrossRef(FavouriteRecipeCrossRef favouriteRecipeCrossRef){
        favouriteListDao.deleteFavouritesWithRecipes(favouriteRecipeCrossRef);
        System.out.println("favouriteRecipeCrossRef deleted");
    }

    /**
     * getters for different queries
     */
    public LiveData<List<Recipe>> getFavouriteRecipesByQuery(String query){
        return favouriteListDao.getFavouriteRecipesByQuery(query);
    }
}
