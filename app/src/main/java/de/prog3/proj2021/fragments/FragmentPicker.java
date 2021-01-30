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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.prog3.proj2021.R;

public class FragmentPicker extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_picker, container, false);

        return root;
    }
}
