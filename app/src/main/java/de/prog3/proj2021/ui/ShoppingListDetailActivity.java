package de.prog3.proj2021.ui;

/*
 * UI ShoppingListDetailActivity.
 * Gets passed the shoppingListId of the chosen ShoppingList.
 * Instantiates a ViewModel to retrieve
 * ShoppingList data from repository.
 *
 *
 * File authors: Giuseppe Buccellato
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

    ShoppingListWithIngredients currentList;

    int currentShoppingListId = 1;

    FloatingActionButton fab;
    TextView ifEmptyNotifier;

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

    /*
     * initialise shoppingDetailRecyclerView with Adapter
     * */
    private void initRecyclerView(){
        shoppingDetailRecyclerView = findViewById(R.id.shoppingListIngredientsRecycler);
        shoppingDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoppingDetailRecyclerView.setHasFixedSize(true);

        shoppingDetailAdapter = new ShoppingDetailRecyclerViewAdapter(this);
        shoppingDetailRecyclerView.setAdapter(shoppingDetailAdapter);
    }

    /*
     * Observe ViewModel for changes, query ShoppingLists from
     * ShoppingListRepository and pass data to RecyclerViewAdapter
     */
    private void initViewModel(){
        mShoppingListViewModel = new ViewModelProvider(this).get(ShoppingListViewModel.class);

        mShoppingListViewModel.getMShoppingListsWithIngredients().observe(this, shoppingLists -> { //Observable lambda expression
            setCurrentList(shoppingLists);
            initViews();
            shoppingDetailAdapter.setMShoppingListWithIngredients(currentList);
            shoppingDetailAdapter.notifyDataSetChanged();
        });
    }

    /*
    * finds chosen ShoppingListWithIngredients among all lists
    * */
    private void setCurrentList(List<ShoppingListWithIngredients> shoppingLists){
        for(ShoppingListWithIngredients list : shoppingLists){
            if(list.shoppingList.getId() == currentShoppingListId){
                this.currentList = list;
            }
        }
    }

    /*
    * initiate Views
    * */
    private void initViews(){
        ifEmptyNotifier = findViewById(R.id.ifListEmptyNotifier);

        if(!currentList.ingredients.isEmpty()){
            ifEmptyNotifier.setVisibility(View.GONE);
        }else{
            ifEmptyNotifier.setVisibility(View.VISIBLE);
        }
    }

    /*
     * initialise floating action button
     * for adding ingredients to shoppingList
     * */
    private void initFab(){
        fab = findViewById(R.id.shoppingDetailFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog(currentShoppingListId);
            }
        });
    }

    /*
    * create alert dialog to add ingredient
    * */
    private void showAddDialog(int currentShoppingListId){
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //set dialog message and title
        builder.setTitle("Add ingredient to ShoppingList");

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
                if(!name.equals("") && value >= 1){
                    addIngredientToList(name, value, currentShoppingListId);
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

    /*
     * fill the name lists with ingredient titles
     * */
    private void initNameList(){
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        ingredientNameList.clear();
        List<IngredientWithRecipes> tmp = mIngredientViewModel.getMIngredientWithRecipes();
        for(IngredientWithRecipes ingredientWithRecipes : tmp){
            ingredientNameList.add(ingredientWithRecipes.ingredient.getName());
        }
    }

    /*
    * add ingredient to list, duh
    * */
    private void addIngredientToList(String name, int amount, int currentShoppingListId){
        //TODO: setup existing or new ingredient to add

        //currentList.ingredients.add(newIngredient);
        //TODO: update crossRef table

        //update ingredient list via adapter
        //shoppingDetailAdapter.notifyDataSetChanged();
    }

}