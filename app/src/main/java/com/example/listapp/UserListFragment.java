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
 * Luokka fragmentille, jonka on tarkoitus esittää käyttäjän luoma lista. Perii Fragment-luokan.
 */
public class UserListFragment extends Fragment {

    /**
     * Käsiteltävä käyttäjälista.
     */
    static UserList userList;

    /**
     * RecyclerView-olion käyttämä adapteri.
     */
    static UserListAdapter adapter;

    /**
     * Fragmentti-olio joka sisällyttää viittauksen tähän fragmenttiin.
     */
    private static UserListFragment instance;

    /**
     * Parametritön alustaja.
     */
    public UserListFragment() {
        super(R.layout.fragment_list_user);
    }

    /**
     * onViewCreated-metodi, joka ajetaan aina Viewiä luodessa. Määrittää käytettävät
     * komponentit, fragmentin instanssin ja adapterin.
     *
     * @param view Parametreinä saatu View-luokan olio.
     * @param savedInstanceState Bundle instanssin tilalle.
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
     * Metodi käyttäjälistaan kohdan lisäämiselle. Lisää annetun merkkijonon listaan uudeksi kohdaksi
     * UserList-luokan metodeita käyttäen, päivittää nykyisen näkymän ja päivittää käyttäjälistan
     * päälistaan.
     *
     * @param text Listaan lisättävä merkkijono.
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
     * Palauttaa tallennetun fragment-olion.
     * @return UserListFragment-olio, joka sisällyttää viittauksen nykyiseen fragmenttiin.
     */
    public static UserListFragment getInstance()
    {
        return instance;
    }
}