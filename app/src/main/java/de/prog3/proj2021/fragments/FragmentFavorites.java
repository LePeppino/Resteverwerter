package de.prog3.proj2021.fragments;

/*
 * UI Favourites Fragment of MainActivity.
 * Instantiates a ViewModel to retrieve data from repository.
 * Initiates a RecyclerView to display queried recipe data.
 *
 * File authors: Eric Walter, Giuseppe Buccellato
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.FavouriteRecyclerViewAdapter;
import de.prog3.proj2021.db.FavouritesWithRecipes;
import de.prog3.proj2021.viewmodels.FavouritesViewModel;

public class FragmentFavorites extends Fragment {

    FavouriteRecyclerViewAdapter favouriteRecyclerViewAdapter;
    RecyclerView favouriteRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        //instantiate RecipeRecyclerView and Adapter
        initRecyclerView(root);

        //instantiate ViewModel and Observer
        initViewModel();

        return root;
    }

    /*
     * initialise RecipeRecyclerView with Adapter
     * */
    private void initRecyclerView(View root){
        favouriteRecyclerView = root.findViewById(R.id.favouriteRecyclerView);
        favouriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favouriteRecyclerView.setHasFixedSize(true);

        favouriteRecyclerViewAdapter = new FavouriteRecyclerViewAdapter(getContext());
        favouriteRecyclerView.setAdapter(favouriteRecyclerViewAdapter);
    }

    /*
     * Initiate ViewModel, query favourite recipes from
     * FavouriteRecipeRepository and pass data to FavouriteRecyclerViewAdapter
     */
    private void initViewModel() {
        FavouritesViewModel mFavouritesViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
        FavouritesWithRecipes favouriteRecipes = mFavouritesViewModel.getMFavouriteList();

        favouriteRecyclerViewAdapter.setMFavourites(favouriteRecipes);
    }
}
