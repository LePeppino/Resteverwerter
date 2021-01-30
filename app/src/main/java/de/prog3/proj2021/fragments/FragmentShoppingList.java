package de.prog3.proj2021.fragments;

/*
 * UI ShoppingList Fragment of MainActivity
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

import de.prog3.proj2021.R;

public class FragmentShoppingList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shopping_list, container, false);

        return root;
    }
}
