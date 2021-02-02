package de.prog3.proj2021.repositories;

/*
 * This class interacts with the Recipe DAO
 * to retrieve and cache recipes from database
 * to pass them to a ViewModel
 *
 * File author: Giuseppe Buccellato
 * */

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.AppDatabase;
import de.prog3.proj2021.db.FavouriteRecipeCrossRef;
import de.prog3.proj2021.db.ShoppingListDao;
import de.prog3.proj2021.db.ShoppingListIngredientCrossRef;
import de.prog3.proj2021.db.ShoppingListWithIngredients;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.models.ShoppingList;

public class ShoppingListRepository {

    private LiveData<List<ShoppingListWithIngredients>> dataSet;
    private final ShoppingListDao shoppingListDao;

    //constructor
    public ShoppingListRepository(Application application){
        AppDatabase shoppingListDB = AppDatabase.getInstance(application);
        shoppingListDao = shoppingListDB.shoppingListDao();
        updateShoppingListWithIngredients();
    }

    //getter and setter for shoppingList dataSat
    public LiveData<List<ShoppingListWithIngredients>> getShoppingListWithIngredients() {
        return dataSet;
    }

    private void updateShoppingListWithIngredients(){
        dataSet = shoppingListDao.getShoppingListWithIngredients();
    }

    /*
     * database operations communicating with FavouriteListDao
     */
    public void insert(ShoppingList shoppingList){
        shoppingListDao.insertShoppingList(shoppingList);
        System.out.println("shoppingList inserted");
    }
    public void update(ShoppingList shoppingList){
        shoppingListDao.updateShoppingList(shoppingList);
        System.out.println("shoppingList updated");
    }
    public void delete(ShoppingList shoppingList){
        shoppingListDao.deleteShoppingList(shoppingList);
        System.out.println("shoppingList deleted");
    }

    public void insertCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingListDao.insertShoppingListWithIngredients(shoppingListIngredientCrossRef);
        System.out.println("shoppingListWithIngredientCrossRef inserted");
    }
    public void updateCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingListDao.updateShoppingListWithIngredients(shoppingListIngredientCrossRef);
        System.out.println("shoppingListWithIngredientCrossRef updated");
    }
    public void deleteCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingListDao.deleteShoppingListWithIngredients(shoppingListIngredientCrossRef);
        System.out.println("shoppingListWithIngredientCrossRef deleted");
    }

    /*
     * getters for different queries here
     */

}
