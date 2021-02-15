package de.prog3.proj2021.viewmodels;

/**
 * This ViewModel class retrieves data from
 * ShoppingListRepository and passes it to the UI
 *
 * @author Giuseppe Buccellato
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import de.prog3.proj2021.db.ShoppingListIngredientCrossRef;
import de.prog3.proj2021.db.ShoppingListWithIngredients;
import de.prog3.proj2021.models.ShoppingList;
import de.prog3.proj2021.repositories.ShoppingListRepository;

public class ShoppingListViewModel extends AndroidViewModel {

    private final ShoppingListRepository shoppingRepository;
    private final LiveData<List<ShoppingListWithIngredients>> mShoppingListsWithIngredients;

    /**
     * Constructor gets passed application context by Activity/Fragment
     * and forwards it to repository class.
     * */
    public ShoppingListViewModel(@NonNull Application application) {
        super(application);
        shoppingRepository = new ShoppingListRepository(application);
        mShoppingListsWithIngredients = shoppingRepository.getShoppingListWithIngredients();
    }

    /**
     * Database communication methods for single objects and cross references
     * */
    public void insert(ShoppingList shoppingList){shoppingRepository.insert(shoppingList);}
    public void update(ShoppingList shoppingList){shoppingRepository.update(shoppingList);}
    public void delete(ShoppingList shoppingList){shoppingRepository.delete(shoppingList);}

    public void insertShoppingIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingRepository.insertCrossRef(shoppingListIngredientCrossRef);
    }
    public void updateShoppingIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingRepository.updateCrossRef(shoppingListIngredientCrossRef);
    }
    public void deleteShoppingIngredientCrossRef(ShoppingListIngredientCrossRef shoppingListIngredientCrossRef){
        shoppingRepository.deleteCrossRef(shoppingListIngredientCrossRef);
    }

    /**
     * getters to pass data to UI
     */
    public LiveData<List<ShoppingListWithIngredients>> getMShoppingListsWithIngredients(){
        return mShoppingListsWithIngredients;
    }

}
