package de.prog3.proj2021.fragments;

/**
 * UI Favourites Fragment of MainActivity.
 * Instantiates a ViewModel to retrieve data from repository.
 * Initiates a RecyclerView to display queried recipe data.
 *
 * @author Eric Walter
 */

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.FavouriteRecyclerViewAdapter;
import de.prog3.proj2021.db.FavouritesWithRecipes;
import de.prog3.proj2021.db.RecipeWithIngredients;
import de.prog3.proj2021.models.Recipe;
import de.prog3.proj2021.viewmodels.FavouritesViewModel;
import de.prog3.proj2021.viewmodels.RecipeViewModel;

public class FragmentFavorites extends Fragment {

    FavouriteRecyclerViewAdapter favouriteRecyclerViewAdapter;
    RecyclerView favouriteRecyclerView;
    FavouritesViewModel mFavouritesViewModel;

    AutoCompleteTextView autoCompleteTextView;
    private final List<String> recipeNameList = new ArrayList<>();

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
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        //instantiate RecipeRecyclerView and Adapter
        initRecyclerView(root);

        //instantiate ViewModel and Observer
        initViewModel();

        //instantiate editText search bar
        initSearchBar(root);

        return root;
    }

    /**
     * initialise RecipeRecyclerView with Adapter
     * */
    private void initRecyclerView(View root){
        favouriteRecyclerView = root.findViewById(R.id.favouriteRecyclerView);
        favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteRecyclerView.setHasFixedSize(true);

        favouriteRecyclerViewAdapter = new FavouriteRecyclerViewAdapter(getContext());
        favouriteRecyclerView.setAdapter(favouriteRecyclerViewAdapter);
    }

    /**
     * Initiate ViewModel, query favourite recipes from
     * FavouriteRecipeRepository and pass data to FavouriteRecyclerViewAdapter
     */
    private void initViewModel() {
        mFavouritesViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);

        FavouritesWithRecipes favouritesWithRecipes = mFavouritesViewModel.getMFavouriteList();
        favouriteRecyclerViewAdapter.setMFavourites(favouritesWithRecipes);
        favouriteRecyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * initialise the AutoCompleteTextView as search bar
     * and update recipe list according to query
     * */
    private void initSearchBar(View root){
        initNameList();

        autoCompleteTextView = root.findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> recipeAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, recipeNameList);
        autoCompleteTextView.setAdapter(recipeAdapter);

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

    /**
     * display recipe in Recycler if query matches with recipe name.
     * if query is empty, show all favourite recipes
     * */
    private void updateRecipeList(String query){
        if(!query.equals("")){
            mFavouritesViewModel.getFavouriteRecipesByQuery(query).observe(getViewLifecycleOwner(), filteredRecipeList -> {
                favouriteRecyclerViewAdapter.setMFavouritesByQuery(filteredRecipeList);
                favouriteRecyclerViewAdapter.notifyDataSetChanged();
            });
        }else{
            FavouritesWithRecipes recipeList = mFavouritesViewModel.getMFavouriteList();
            favouriteRecyclerViewAdapter.setMFavourites(recipeList);
            favouriteRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    /**
     * fill the name lists with recipe titles
     * */
    private void initNameList(){
        recipeNameList.clear();
        FavouritesWithRecipes favouritesWithRecipes = mFavouritesViewModel.getMFavouriteList();
            for(Recipe favouriteRecipe : favouritesWithRecipes.recipes){
                recipeNameList.add(favouriteRecipe.getName());
            }
    }

}
