package com.example.listapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;


/**
 * Luokka "päävalikon" listafragmentille. Fragmentin tarkoitus on sisällyttää ja esittää lista
 * kaikista luoduista listoista.
 */
public class MainListFragment extends Fragment {

    public MainListFragment() {super(R.layout.fragment_list_main);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rvMainList = view.findViewById(R.id.rvMainList);

        ArrayList<UserList> lists = MainActivity.lists.fetchLists();
        rvMainList.setAdapter(new MainListAdapter(lists));
        rvMainList.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }

    public UserList[] getLists(){
        UserList test1 = new UserList(new ArrayList<>());
        test1.setName("TODO");
        test1.addToList("pyykkää");
        test1.addToList("tiskaa");
        test1.addToList("soita äidille");

        UserList test2 = new UserList(new ArrayList<>());
        test2.setName("Kauppalista");
        test2.addToList("maitoa");
        test2.addToList("munia");
        test2.addToList("leipää");

        UserList[] list = new UserList[]{test1, test2};

        return list;
    }
}