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

    ArrayList<String> list;
    UserListAdapter adapter;

    private static UserListFragment instance;

    public UserListFragment() {
        super(R.layout.fragment_list_user);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        instance = this;

        RecyclerView rvUserList = view.findViewById(R.id.rvUserList);
        list = getArguments().getStringArrayList("userList");

        adapter = new UserListAdapter(list);
        rvUserList.setAdapter(adapter);
        rvUserList.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }

    public void updateList(String text){
        list.add(text);
        int pos = list.indexOf(text);
        adapter.notifyItemInserted(pos);
    }

    public static UserListFragment getInstance()
    {
        return instance;
    }
}