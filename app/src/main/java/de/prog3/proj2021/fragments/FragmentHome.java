package de.prog3.proj2021.fragments;

/*
 * UI Home Fragment of MainActivity.
 * Instantiates a ViewModel to retrieve data from repository.
 * Initiates a RecyclerView to display queried recipe data.
 *
 * File authors: Eric Walter, Giuseppe Buccellato
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.prog3.proj2021.R;
import de.prog3.proj2021.adapters.RecipeRecyclerViewAdapter;
import de.prog3.proj2021.repositories.RecipeRepository;
import de.prog3.proj2021.viewmodels.MainActivityViewModel;

public class FragmentHome extends Fragment {

    private MainActivityViewModel mMainActivityViewModel;
    RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    RecyclerView recipeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

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
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.getmRecipes().observe(getViewLifecycleOwner(), recipes -> { //Observable lambda expression
            recipeRecyclerViewAdapter.setmRecipes(recipes);
            recipeRecyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "observed onChanged RecyclerView", Toast.LENGTH_SHORT).show();
        });
    }
}
