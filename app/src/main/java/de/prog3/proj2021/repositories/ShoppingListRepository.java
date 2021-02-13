package de.prog3.proj2021.repositories;

/*
 * This class interacts with the ShoppingListDao interface
 * to retrieve and cache ShoppingLists with Ingredients from database
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

    private LiveData<List<ShoppingList>> dataSet;
    private LiveData<List<ShoppingListWithIngredients>> CrossRefDataSet;

    private final ShoppingListDao shoppingListDao;

    //constructor
    public ShoppingListRepository(Application application){
        AppDatabase shoppingListDB = AppDatabase.getInstance(application);
        shoppingListDao = shoppingListDB.shoppingListDao();
        updateShoppingListWithIngredients();
    }

    //setter
    private void updateShoppingListWithIngredients(){
        dataSet = shoppingListDao.getShoppingLists();
        CrossRefDataSet = shoppingListDao.getShoppingListWithIngredients();
    }

    //getter for shoppingList dataSat
    public LiveData<List<ShoppingList>> getShoppingLists() {
        return dataSet;
    }
    public LiveData<List<ShoppingListWithIngredients>> getShoppingListWithIngredients() {
        return CrossRefDataSet;
    }

    /*
     * database operations communicating with FavouriteListDao
     */
    public void insert(ShoppingList shoppingList){
        AppDatabase.databaseExecutor.execute(() ->
                shoppingListDao.insertShoppingList(shoppingList)
        );
        System.out.println("shoppingList inserted");
    }
    public void update(ShoppingList shoppingList){
        AppDatabase.databaseExecutor.execute(() -> {
            shoppingListDao.updateShoppingList(shoppingList);
        });
        System.out.println("shoppingList updated");
    }
    public void delete(ShoppingList shoppingList){
        AppDatabase.databaseExecutor.execute(() -> {
            shoppingListDao.deleteShoppingList(shoppingList);
        });
        System.out.println("shoppingList deleted");
    }

    public void insertCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        AppDatabase.databaseExecutor.execute(() -> {
            shoppingListDao.insertShoppingListWithIngredients(shoppingListIngredientCrossRef);
        });
        System.out.println("shoppingListWithIngredientCrossRef inserted");
    }
    public void updateCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        AppDatabase.databaseExecutor.execute(() -> {
            shoppingListDao.updateShoppingListWithIngredients(shoppingListIngredientCrossRef);
        });
        System.out.println("shoppingListWithIngredientCrossRef updated");
    }
    public void deleteCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        AppDatabase.databaseExecutor.execute(() -> {
            shoppingListDao.deleteShoppingListWithIngredients(shoppingListIngredientCrossRef);
        });
        System.out.println("shoppingListWithIngredientCrossRef deleted");
    }

    /*
     * getters for different queries here
     */

}
