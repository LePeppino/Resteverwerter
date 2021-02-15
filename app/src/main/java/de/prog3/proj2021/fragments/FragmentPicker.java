package de.prog3.proj2021.fragments;

/**
 * UI Ingredient Picker Fragment of MainActivity
 * Instantiates a ViewModel to retrieve data from repository.
 * Initiates a RecyclerView to display queried recipe data.
 *
 * @author Eric Walter
 * @author Giuseppe Buccellato
 */

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import de.prog3.proj2021.adapters.PickerRecyclerViewAdapter;
import de.prog3.proj2021.db.IngredientWithRecipes;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.viewmodels.IngredientViewModel;

public class FragmentPicker extends Fragment {

    PickerRecyclerViewAdapter pickerAdapter;
    RecyclerView pickerRecyclerView;
    IngredientViewModel mIngredientViewModel;

    AutoCompleteTextView autoCompleteTextView;
    private final List<String> ingredientNameList = new ArrayList<>();

    /**
     * onCreateView method displays the fragment
     * @param inflater layout infalter
     * @param container container that will display the fragment
     * @param savedInstanceState Bundle state
     * @return View of the fragment
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_picker, container, false);

        //instantiate PickerRecyclerView and Adapter
        initRecyclerView(root);

        //initiate ViewModel and Observer
        initViewModel();

        //instantiate editText search bar
        initSearchBar(root);

        return root;
    }

    /**
     * initialise RecipeRecyclerView with Adapter
     * */
    private void initRecyclerView(View root){
        pickerRecyclerView = root.findViewById(R.id.ingredientRecyclerView);
        pickerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pickerRecyclerView.setHasFixedSize(true);

        pickerAdapter = new PickerRecyclerViewAdapter(getContext());
        pickerRecyclerView.setAdapter(pickerAdapter);
    }

    /**
     * Observe ViewModel for changes, query ingredients from
     * IngredientRepository and pass data to PickerRecyclerViewAdapter
     */
    private void initViewModel(){
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        mIngredientViewModel.getMIngredients().observe(getViewLifecycleOwner(), ingredientList -> {
            pickerAdapter.setIngredients(ingredientList);
            pickerAdapter.notifyDataSetChanged();
        });
    }

    /**
     * initialise the AutoCompleteTextView as search bar
     * and update ingredient list according to query
     * */
    private void initSearchBar(View root){
        initNameList();

        autoCompleteTextView = root.findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> ingredientAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, ingredientNameList);
        autoCompleteTextView.setAdapter(ingredientAdapter);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateIngredientList(s.toString());
                System.out.println("onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateIngredientList(s.toString());
                System.out.println("afterTextChanged");
            }
        });
    }

    /**
    * display ingredient in Recycler if query matches with ingredient name.
    * if query is empty, show all ingredients
    * */
    private void updateIngredientList(String query){
        if(!query.equals("")){
            mIngredientViewModel.getMIngredientsByQuery(query).observe(getViewLifecycleOwner(), filteredIngredientList -> {
                pickerAdapter.setIngredients(filteredIngredientList);
                pickerAdapter.notifyDataSetChanged();
            });
        }else{
            mIngredientViewModel.getMIngredients().observe(getViewLifecycleOwner(), ingredientList -> {
                pickerAdapter.setIngredients(ingredientList);
                pickerAdapter.notifyDataSetChanged();
            });
        }
    }

    /**
     * fill the name lists with ingredient titles
     * */
    private void initNameList(){
        ingredientNameList.clear();
        List<IngredientWithRecipes> tmp = mIngredientViewModel.getMIngredientWithRecipes();
        for(IngredientWithRecipes ingredientWithRecipes : tmp){
            ingredientNameList.add(ingredientWithRecipes.ingredient.getName());
        }
    }

}
