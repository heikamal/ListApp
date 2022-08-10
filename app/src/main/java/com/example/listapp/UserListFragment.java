package com.example.listapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Luokka fragmentille, jonka on tarkoitus esittää käyttäjän luoma lista.
 */
public class UserListFragment extends Fragment {

    public UserListFragment() {
        super(R.layout.fragment_list_user);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView rvUserList = view.findViewById(R.id.rvUserList);
        ArrayList<String> list = getArguments().getStringArrayList("userList");

        rvUserList.setAdapter(new UserListAdapter(list));
        rvUserList.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }
}