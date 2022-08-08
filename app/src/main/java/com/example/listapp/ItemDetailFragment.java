package com.example.listapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Luokka fragmentille, jonka on tarkoitus esittää käyttäjän luoma lista.
 */
public class ItemDetailFragment extends Fragment {

    public ItemDetailFragment() {
        super(R.layout.fragment_item_detail);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}