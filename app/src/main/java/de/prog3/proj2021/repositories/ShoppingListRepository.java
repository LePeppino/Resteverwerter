package de.prog3.proj2021.repositories;

/**
 * This class interacts with the ShoppingListDao interface
 * to retrieve and cache ShoppingLists with Ingredients from database
 * to pass them to a ViewModel
 *
 * @author Giuseppe Buccellato
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

    /**
     * constructor
     * @param application
     */
    public ShoppingListRepository(Application application){
        AppDatabase shoppingListDB = AppDatabase.getInstance(application);
        shoppingListDao = shoppingListDB.shoppingListDao();
        updateShoppingListWithIngredients();
    }

    /**
     * getter for shoppingList dataSat
     * @return live data of list of shoppingListWithIngredients
     */
    public LiveData<List<ShoppingListWithIngredients>> getShoppingListWithIngredients() {
        return dataSet;
    }

    /**
     * update shoppingListWithIngredients
     */
    private void updateShoppingListWithIngredients(){
        dataSet = shoppingListDao.getShoppingListWithIngredients();
    }

    /*
     * database operations communicating with FavouriteListDao
     */

    /**
     * inserts shopping list
     * @param shoppingList
     */
    public void insert(ShoppingList shoppingList){
        shoppingListDao.insertShoppingList(shoppingList);
        System.out.println("shoppingList inserted");
    }

    /**
     * updates shopping list
     * @param shoppingList
     */
    public void update(ShoppingList shoppingList){
        shoppingListDao.updateShoppingList(shoppingList);
        System.out.println("shoppingList updated");
    }

    /**
     * deletes shopping list
     * @param shoppingList
     */
    public void delete(ShoppingList shoppingList){
        shoppingListDao.deleteShoppingList(shoppingList);
        System.out.println("shoppingList deleted");
    }

    /**
     * inserts ShoppingListIngredientCrossRef
     * @param shoppingListIngredientCrossRef
     */
    public void insertCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingListDao.insertShoppingListWithIngredients(shoppingListIngredientCrossRef);
        System.out.println("shoppingListWithIngredientCrossRef inserted");
    }

    /**
     * updates ShoppingListIngredientCrossRef
     * @param shoppingListIngredientCrossRef
     */
    public void updateCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingListDao.updateShoppingListWithIngredients(shoppingListIngredientCrossRef);
        System.out.println("shoppingListWithIngredientCrossRef updated");
    }

    /**
     * deletes ShoppingListIngredientCrossRef
     * @param shoppingListIngredientCrossRef
     */
    public void deleteCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingListDao.deleteShoppingListWithIngredients(shoppingListIngredientCrossRef);
        System.out.println("shoppingListWithIngredientCrossRef deleted");
    }


}
