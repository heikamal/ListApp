package com.example.listapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Luokka yksittäisen käyttäjän luoman listan osaa varten.
 */
public class ListEntryFragment extends Fragment {

    public ListEntryFragment() {super(R.layout.fragment_list_entry);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}