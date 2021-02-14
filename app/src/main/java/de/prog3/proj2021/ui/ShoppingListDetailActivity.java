package de.prog3.proj2021.ui;

/**
 * UI ShoppingListDetailActivity.
 * Gets passed the shoppingListId of the chosen ShoppingList.
 * Instantiates a ViewModel to retrieve
 * ShoppingList data from repository.
 *
 *
 * @author Eric Walter, Giuseppe Buccellato
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.ShoppingDetailRecyclerViewAdapter;
import de.prog3.proj2021.db.IngredientWithRecipes;
import de.prog3.proj2021.db.ShoppingListIngredientCrossRef;
import de.prog3.proj2021.db.ShoppingListWithIngredients;
import de.prog3.proj2021.models.Ingredient;
import de.prog3.proj2021.viewmodels.IngredientViewModel;
import de.prog3.proj2021.viewmodels.ShoppingListViewModel;

public class ShoppingListDetailActivity extends AppCompatActivity {

    ShoppingDetailRecyclerViewAdapter shoppingDetailAdapter;
    RecyclerView shoppingDetailRecyclerView;
    ShoppingListViewModel mShoppingListViewModel;
    IngredientViewModel mIngredientViewModel;

    private final List<String> ingredientNameList = new ArrayList<>();
    private final List<Ingredient> currentIngredientList = new ArrayList<>();
    private ShoppingListWithIngredients currentList;


    int currentShoppingListId = 1;

    FloatingActionButton fab;
    TextView ifEmptyNotifier;

    /**
     * onCreate method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_detail);

        //get passed shoppingListId from chosen ShoppingList
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            currentShoppingListId = extras.getInt("currentShoppingListId");
        }

        //instantiate RecyclerView and Adapter
        initRecyclerView();

        //instantiate ViewModel, Observer and Views
        initViewModel();

        //fill nameList with ingredient names
        initNameList();

        //initiate Floating Action Button
        initFab();
    }

    /**
     * initialise shoppingDetailRecyclerView with Adapter
     * */
    private void initRecyclerView(){
        shoppingDetailRecyclerView = findViewById(R.id.shoppingListIngredientsRecycler);
        shoppingDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingDetailRecyclerView.setHasFixedSize(true);

        shoppingDetailAdapter = new ShoppingDetailRecyclerViewAdapter(this);
        shoppingDetailRecyclerView.setAdapter(shoppingDetailAdapter);
    }

    /**
     * Observe ViewModel for changes, query ShoppingLists from
     * ShoppingListRepository and pass data to RecyclerViewAdapter
     */
    private void initViewModel(){
        mShoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        mShoppingListViewModel.getMShoppingListsWithIngredients().observe(this, shoppingLists -> { //Observable lambda expression
            if(shoppingLists != null){
                setCurrentList(shoppingLists);
            }else { System.out.println("shoppingLists is null!");}
            initViews();
            shoppingDetailAdapter.setMShoppingListWithIngredients(this.currentList);
            shoppingDetailAdapter.notifyDataSetChanged();
        });
    }

    /**
    * finds chosen ShoppingListWithIngredients among all lists
    * */
    private void setCurrentList(List<ShoppingListWithIngredients> shoppingLists){
        for(ShoppingListWithIngredients list : shoppingLists){
            if(list.shoppingList.getId() == currentShoppingListId){
                this.currentList = new ShoppingListWithIngredients(list);
            }
        }
    }

    /**
    * initiate info message if no items exist on list
    * */
    private void initViews(){
        ifEmptyNotifier = findViewById(R.id.ifListEmptyNotifier);

        if(!this.currentList.ingredients.isEmpty()){
            ifEmptyNotifier.setVisibility(View.GONE);
        }else{
            ifEmptyNotifier.setVisibility(View.VISIBLE);
        }
    }

    /**
     * initialise floating action button
     * for adding ingredients to shoppingList
     * */
    private void initFab(){
        fab = findViewById(R.id.shoppingDetailFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
    }

    /**
    * create alert dialog to add ingredient
    * */
    private void showAddDialog(){
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set dialog message and title
        builder.setTitle("Add ingredient to shopping list");

        //set layout for dialog
        View v = LayoutInflater.from(ShoppingListDetailActivity.this).
                inflate(R.layout.dialog_layout_add_ingredient, null, false);
        builder.setView(v);

        //create text box for name input
        final AutoCompleteTextView nameEditText = v.findViewById(R.id.nameTextBox);
        ArrayAdapter<String> ingredientAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ingredientNameList);
        nameEditText.setAdapter(ingredientAdapter);
        //create text box for amount input
        final EditText amountEditText = v.findViewById(R.id.amountTextBox);

        //set dialog positive option
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString();
                String amount = amountEditText.getText().toString();
                //convert String amount to int value
                int value = 0;
                if (!amount.equals("")){
                    value = Integer.parseInt(amount);
                }
                //check if inputs are valid. if yes, add ingredient, else cancel
                if(!name.equals("") && ingredientNameList.contains(name) && value >= 1){
                    addIngredientToList(name, value);
                }else{
                    Toast.makeText(ShoppingListDetailActivity.this, "Name cannot be empty and amount must be 1 or more.", Toast.LENGTH_LONG).show();
                }
            }
        });
        //set dialog negative option as null to dismiss
        builder.setNegativeButton("Cancel", null);
        builder.setIcon(R.drawable.ic_baseline_shopping_basket_24);

        //create and show configured dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * fill the name lists with ingredient titles
     * */
    private void initNameList(){
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        ingredientNameList.clear();
        mIngredientViewModel.getMIngredients().observe(this, ingredientList -> {
            setCurrentIngredientList(currentIngredientList);
            ingredientNameList.clear();
            for(Ingredient ingredient : ingredientList){
                ingredientNameList.add(ingredient.getName());
            }
        });
    }

    /**
    * setter for current ingredient list
    * */
    private void setCurrentIngredientList(List<Ingredient> currentIngredientList){
        this.currentIngredientList.addAll(currentIngredientList);
    }

    /**
    * add ingredient to list, if it's not already
    * */
    private void addIngredientToList(String name, int amount){
        //fetch ingredient by name
        for(Ingredient ingredient : currentIngredientList){
            if(ingredient.getName().equals(name) && !currentList.ingredients.contains(ingredient)){
                setNewIngredient(ingredient, amount);
                updateIngredientInDB(ingredient);
            }else if(currentList.ingredients.contains(ingredient)){
                updateExistingIngredient(ingredient);
            }
        }
        //update ingredient list via adapter
        shoppingDetailAdapter.setMShoppingListWithIngredients(currentList);
        shoppingDetailAdapter.notifyDataSetChanged();
    }

    /**
     * set given amount as numToBuy
     * and increment number of unchecked items on current ShoppingList
     * */
    private void setNewIngredient(Ingredient newIngredient, int amount){
        newIngredient.setNumToBuy(amount);
        currentList.ingredients.add(newIngredient);
        currentList.shoppingList.setNumUncheckedItems(
                currentList.shoppingList.getNumUncheckedItems() + 1
        );
    }

    /**
    * updates ingredient on shopping list if already existing
    * */
    private void updateExistingIngredient(Ingredient ingredient){
        for(Ingredient i : currentList.ingredients){
            if(i.getName().equals(ingredient.getName())){
                i.setNumToBuy(ingredient.getNumToBuy());
            }
        }
    }

    /**
     * updates ingredient in db
     * @param newIngredient
     */
    private void updateIngredientInDB(Ingredient newIngredient){
        mIngredientViewModel.update(newIngredient);

        //update ShoppingListIngredientCrossRef table
        ShoppingListIngredientCrossRef crossRef = new ShoppingListIngredientCrossRef(
                currentList.shoppingList.getId(), newIngredient.getId());
        mShoppingListViewModel.insertShoppingIngredientCrossRef(crossRef);
    }

}