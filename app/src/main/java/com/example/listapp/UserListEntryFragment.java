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

    /**
     * Parametritön alustaja. Määrittää käytettänä layoutin.
     */
    public UserListEntryFragment() {super(R.layout.user_list_entry);}

    /**
     * onViewCreated-metodi, joka ajetaan kun view luodaan.
     *
     * @param view Parametreinä saatu view.
     * @param savedInstanceState Bundle instancen tilalle.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}