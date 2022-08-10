package com.example.listapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;

/**
 * Luokka yksittäisen käyttäjän luoman listan osaa varten.
 */
public class UserListEntryFragment extends Fragment {

    public UserListEntryFragment() {super(R.layout.user_list_entry);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}