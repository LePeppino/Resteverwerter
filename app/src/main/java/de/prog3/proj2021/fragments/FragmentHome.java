package de.prog3.proj2021.fragments;

/*
 * UI Home Fragment of MainActivity.
 * Instantiates a ViewModel to retrieve data from repository.
 * Initiates a RecyclerView to display queried recipe data.
 *
 * File authors: Eric Walter, Giuseppe Buccellato
 */

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.viewmodels.RecipeViewModel;

public class FragmentHome extends Fragment {

    RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    RecyclerView recipeRecyclerView;
    RecipeViewModel mRecipeViewModel;

    AutoCompleteTextView autoCompleteTextView;
    private final List<String> recipeNameList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //instantiate RecipeRecyclerView and Adapter
        initRecyclerView(root);

        //instantiate ViewModel and Observer
        initViewModel();

        //instantiate editText search bar
        initSearchBar(root);

        return root;
    }

    /*
     * initialise recipeRecyclerView with Adapter
     * */
    private void initRecyclerView(View root){
        recipeRecyclerView = root.findViewById(R.id.recipeRecyclerView);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeRecyclerView.setHasFixedSize(true);

        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext());
        recipeRecyclerView.setAdapter(recipeRecyclerViewAdapter);
    }

    /*
     * Observe ViewModel for changes, query recipes from
     * RecipeRepository and pass data to RecipeRecyclerViewAdapter
     */
    private void initViewModel(){
        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        mRecipeViewModel.getMRecipes().observe(getViewLifecycleOwner(), recipeList -> { //Observable lambda expression
            recipeRecyclerViewAdapter.setMRecipes(recipeList);
            recipeRecyclerViewAdapter.notifyDataSetChanged();
        });
    }

    /*
    * initialise the AutoCompleteTextView as search bar
    * and update recipe list according to query
    * */
    private void initSearchBar(View root){
        //fill recipe name list
        initNameList();

        autoCompleteTextView = root.findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> recipeAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, recipeNameList);
        autoCompleteTextView.setAdapter(recipeAdapter);

        //observe text in search bar
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateRecipeList(s.toString());
                System.out.println("onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateRecipeList(s.toString());
                System.out.println("afterTextChanged");
            }
        });
    }

    /*
     * display recipe in Recycler if query matches with recipe name.
     * if query is empty, show all recipes
     * */
    public void updateRecipeList(String query){
        if(!query.equals("")){
            mRecipeViewModel.getMRecipesByQuery(query).observe(getViewLifecycleOwner(), filteredRecipeList -> {
                recipeRecyclerViewAdapter.setMRecipes(filteredRecipeList);
                recipeRecyclerViewAdapter.notifyDataSetChanged();
            });
        }else{
            mRecipeViewModel.getMRecipes().observe(getViewLifecycleOwner(), recipeList -> {
                recipeRecyclerViewAdapter.setMRecipes(recipeList);
                recipeRecyclerViewAdapter.notifyDataSetChanged();
            });
        }
    }

    /*
    * fill the name lists with recipe titles
    * */
    private void initNameList(){
        recipeNameList.clear();
        List<RecipeWithIngredients> tmp = mRecipeViewModel.getMRecipesWithIngredients();
        for(RecipeWithIngredients recipeWithIngredients : tmp){
            recipeNameList.add(recipeWithIngredients.recipe.getName());
        }
    }

}
