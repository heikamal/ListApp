package com.example.listapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;


/**
 * Luokka "päävalikon" listafragmentille. Fragmentin tarkoitus on sisällyttää ja esittää lista
 * kaikista luoduista listoista.
 */
public class ItemListFragment extends Fragment {

    public ItemListFragment() {super(R.layout.fragment_item_list);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}