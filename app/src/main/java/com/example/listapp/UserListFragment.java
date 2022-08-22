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

    /**
     *
     */
    static UserList userList;

    /**
     *
     */
    static UserListAdapter adapter;

    /**
     *
     */
    private static UserListFragment instance;

    /**
     *
     */
    public UserListFragment() {
        super(R.layout.fragment_list_user);
    }

    /**
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        instance = this;

        RecyclerView rvUserList = view.findViewById(R.id.rvUserList);
        userList = (UserList) getArguments().getSerializable("userList");

        adapter = new UserListAdapter(userList);
        rvUserList.setAdapter(adapter);
        rvUserList.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }

    /**
     *
     * @param text
     */
    public static void updateList(String text){
        int userListPos = MainActivity.lists.findList(userList);

        userList.addToList(text);
        int pos = userList.getList().indexOf(text);

        MainActivity.lists.removeList(userListPos);
        MainActivity.lists.addList(userList);

        adapter.notifyItemInserted(pos);
        MainActivity.lists.updateList(MainActivity.lists.lists);
        MainActivity.lists.saveLists();
    }

    /**
     *
     * @return
     */
    public static UserListFragment getInstance()
    {
        return instance;
    }
}