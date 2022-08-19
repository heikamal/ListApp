package com.example.listapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Adapter;

import java.util.ArrayList;


/**
 * Luokka "päävalikon" listafragmentille. Fragmentin tarkoitus on sisällyttää ja esittää lista
 * kaikista luoduista listoista.
 */
public class MainListFragment extends Fragment {

    ArrayList<UserList> lists;
    MainListAdapter adapter;

    private static MainListFragment instance;

    public MainListFragment() {super(R.layout.fragment_list_main);}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        instance = this;

        RecyclerView rvMainList = view.findViewById(R.id.rvMainList);

        lists = MainActivity.lists.fetchLists();
        adapter = new MainListAdapter(lists);
        rvMainList.setAdapter(adapter);
        rvMainList.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }

    public void updateList(UserList newList){
        lists.add(newList);
        int pos = lists.indexOf(newList);
        adapter.notifyItemInserted(pos);
    }

    public static MainListFragment getInstance()
    {
        return instance;
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