package de.prog3.proj2021.fragments;

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
import de.prog3.proj2021.viewmodels.MainActivityViewModel;

public class FragmentHome extends Fragment {

    private MainActivityViewModel mMainActivityViewModel;
    RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    RecyclerView recipeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Passes LiveData from MainActivityViewModel to RecipeRecyclerViewAdapter
        initRecyclerView(root);

        //instantiate ViewModel
        mMainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        //Observe ViewModel for changes
        mMainActivityViewModel.getmRecipes().observe(getViewLifecycleOwner(), recipes -> { //Observable lambda expression
            recipeRecyclerViewAdapter.setmRecipes(recipes);
            recipeRecyclerViewAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "observed onChanged", Toast.LENGTH_SHORT).show();
        });

        return root;
    }

    /*
     * initialise RecipeRecyclerView with Adapter
     * Queries recipes from RecipeRepository and passes data to RecipeRecyclerViewAdapter
     * */
    private void initRecyclerView(View root){
        recipeRecyclerView = root.findViewById(R.id.recipeRecyclerView);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeRecyclerView.setHasFixedSize(true);

        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext());
        recipeRecyclerView.setAdapter(recipeRecyclerViewAdapter);
    }
}
