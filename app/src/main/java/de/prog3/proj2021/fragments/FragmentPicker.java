package de.prog3.proj2021.fragments;

/*
 * UI Ingredient Picker Fragment of MainActivity
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
import de.prog3.proj2021.adapters.PickerRecyclerViewAdapter;
import de.prog3.proj2021.viewmodels.IngredientViewModel;

public class FragmentPicker extends Fragment {

    PickerRecyclerViewAdapter pickerAdapter;
    RecyclerView pickerRecyclerView;
    IngredientViewModel mIngredientViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_picker, container, false);

        //instantiate PickerRecyclerView and Adapter
        initRecyclerView(root);

        //initiate ViewModel and Observer
        initViewModel();

        return root;
    }

    private void initRecyclerView(View root){
        pickerRecyclerView = root.findViewById(R.id.ingredientRecyclerView);
        pickerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pickerRecyclerView.setHasFixedSize(true);

        pickerAdapter = new PickerRecyclerViewAdapter(getContext());
        pickerRecyclerView.setAdapter(pickerAdapter);
    }

    private void initViewModel(){
        mIngredientViewModel = new ViewModelProvider(this).get(IngredientViewModel.class);

        mIngredientViewModel.getMIngredients().observe(getViewLifecycleOwner(), ingredientList -> {
            pickerAdapter.setIngredients(ingredientList);
            pickerAdapter.notifyDataSetChanged();
            Toast.makeText(getContext(), "observed onChanged RecyclerView", Toast.LENGTH_SHORT).show();
        });
    }

}
