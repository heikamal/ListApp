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

    /**
     *
     */
    static ArrayList<UserList> lists;

    /**
     *
     */
    static MainListAdapter adapter;

    /**
     *
     */
    private static MainListFragment instance;

    /**
     *
     */
    public MainListFragment() {super(R.layout.fragment_list_main);}

    /**
     *
     * @param view
     * @param savedInstanceState
     */
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

    /**
     *
     * @param newList
     */
    public static void updateList(UserList newList){
        lists.add(newList);
        int pos = lists.indexOf(newList);
        adapter.notifyItemInserted(pos);
        MainActivity.lists.updateList(lists);
        MainActivity.lists.saveLists();
    }

    /**
     *
     * @return
     */
    public static MainListFragment getInstance()
    {
        return instance;
    }

}