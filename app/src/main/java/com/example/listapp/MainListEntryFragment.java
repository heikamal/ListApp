package com.example.listapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Fragmentti päälistan alkioiden tyyliä varten. Perii Fragment-luokan.
 */
public class MainListEntryFragment extends Fragment {

    /**
     * Parametritön muuttuja. Määrittää käytettävän Layoutin.
     */
    public MainListEntryFragment() {
        super(R.layout.main_list_entry);
    }

    /**
     * onViewCreated-metodi, mikä ajetaan fragmenttia luodessa.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}