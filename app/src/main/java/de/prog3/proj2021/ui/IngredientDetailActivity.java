package de.prog3.proj2021.ui;

/*
 * UI IngredientDetailActivity.
 * Gets passed the ingredientId of the chosen ingredient.
 * Instantiates a ViewModel to retrieve
 * ingredient data from repository.
 *
 *
 * File authors: Giuseppe Buccellato
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.IngredientDetailRecyclerViewAdapter;
import de.prog3.proj2021.db.IngredientWithRecipes;
import de.prog3.proj2021.viewmodels.IngredientViewModel;

public class IngredientDetailActivity extends AppCompatActivity {

    //ViewModels
    IngredientViewModel mIngredientViewModel;

    //Associated recipes RecyclerView
    IngredientDetailRecyclerViewAdapter ingredientDetailRecyclerViewAdapter;
    RecyclerView ingredientDetailRecyclerView;

    //Ingredient data
    IngredientWithRecipes currentIngredient = new IngredientWithRecipes();
    int currentIngredientId = 1;

    //Views and Buttons
    TextView ingredientTitle;
    ImageView typeImage;
    TextView type;
    TextView numAvailable;
    TextView numAssociated;
    Button addToShoppingListButton;
    Button addAsOwnedButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail);

        //get passed ingredientId from chosen ingredient
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            currentIngredientId = extras.getInt("currentIngredientId");
        }

        //instantiate IngredientDetailRecyclerView and Adapter
        initRecyclerView();
        //instantiate ViewModel
        initIngredientViewModel();
        //assign Views
        initViews();
    }

    /*
    * initialise RecyclerView and Adapter
    * */
    private void initRecyclerView(){
        ingredientDetailRecyclerView = findViewById(R.id.associatedRecipesRecyclerView);
        ingredientDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredientDetailRecyclerView.setHasFixedSize(true);

        ingredientDetailRecyclerViewAdapter = new IngredientDetailRecyclerViewAdapter(this);
        ingredientDetailRecyclerView.setAdapter(ingredientDetailRecyclerViewAdapter);
    }

    /*
    * initialise ViewModel, retrieve crossRef data and pass it to Adapter
    * */
    private void initIngredientViewModel(){
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        List<IngredientWithRecipes> ingredientsWithRecipes = mIngredientViewModel.getMIngredientWithRecipes();

        //set currentIngredient and pass to RecyclerView
        setCurrentIngredient(ingredientsWithRecipes, currentIngredientId);
        ingredientDetailRecyclerViewAdapter.setIngredientWithRecipes(currentIngredient);
    }

    /*
     * set currentIngredient for this Activity
     */
    private void setCurrentIngredient(List<IngredientWithRecipes> ingredientList, int currentIngredientId){
        for(IngredientWithRecipes ingredient : ingredientList){
            if(ingredient.ingredient.getId() == currentIngredientId){
                currentIngredient = ingredient;
            }
        }
    }

    /*
    * initiates the Views in Activity apart from RecyclerView
    * */
    private void initViews(){
        ingredientTitle = findViewById(R.id.ingredientDetailTitleText);
        typeImage = findViewById(R.id.ingredientDetailTypeImage);
        type = findViewById(R.id.ingredientTypeText);
        numAvailable = findViewById(R.id.numAvailableText);
        numAssociated = findViewById(R.id.numAssociatedRecipesText);
        addToShoppingListButton = findViewById(R.id.addToShoppingListButton);
        addAsOwnedButton = findViewById(R.id.addAsOwnedButton);

        //convert ingredient params from int to String value
        String ingredientUnit = fromIntegerToUnitString(currentIngredient.ingredient.getUnit());
        String ingredientType = fromIntegerToTypeString(currentIngredient.ingredient.getType());
        String numAssociatedRecipes = "" + currentIngredient.recipes.size();

        //concatenate numAvailable + ingredientUnit
        String numAvailableAmount = currentIngredient.ingredient.getNumAvailable() + " " + ingredientUnit;

        //get typeImage by number from assets
        String typeImageUri = "file:///android_asset/typeImages/"
                + currentIngredient.ingredient.getType() + ".webp";

        Glide.with(this)
                .asBitmap()
                .load(Uri.parse(typeImageUri))          // takes String from above
                .error(R.mipmap.ic_launcher)            // on error placeholder
                .placeholder(R.mipmap.ic_launcher)      // placeholder image
                .into(typeImage);                       // destination View

        //pass chosen ingredient details to layout
        ingredientTitle.setText(currentIngredient.ingredient.getName());
        type.setText(ingredientType);
        numAvailable.setText(numAvailableAmount);
        numAssociated.setText(numAssociatedRecipes);

    }

    /*
     * Ingredient Unit to String converter
     */
    private String fromIntegerToUnitString(int unitValue){
        String unitString;

        if(unitValue == 1){unitString = "g";}
        else if(unitValue == 2){unitString = "ml";}
        else if(unitValue == 3){unitString = "pcs";}
        else{unitString = "units";}

        return unitString;
    }

    /*
    * Ingredient Type to String converter
    * */
    private String fromIntegerToTypeString(int typeValue){
        String typeString;

        if(typeValue == 1){typeString = "Dairy";}
        else if(typeValue == 2){typeString = "Vegetables";}
        else if(typeValue == 3){typeString = "Fruits";}
        else if(typeValue == 4){typeString = "Grains";}
        else if(typeValue == 5){typeString = "Spices";}
        else if(typeValue == 6){typeString = "Meats";}
        else if(typeValue == 7){typeString = "Fish";}
        else if(typeValue == 8){typeString = "Seafood";}
        else if(typeValue == 9){typeString = "Condiments";}
        else if(typeValue == 10){typeString = "Dairy Alternates";}
        else if(typeValue == 11){typeString = "Sweets";}
        else if(typeValue == 12){typeString = "Beverages";}
        else if(typeValue == 13){typeString = "Baking";}
        else{typeString = "Other";}

        return typeString;
    }

    /*
    * Button methods
    * */
    public void addToShoppingList(View view){

    }

    public void addAsOwned(View view){

    }
}