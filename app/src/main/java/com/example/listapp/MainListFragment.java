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
 * Luokka "päävalikon" listafragmentille. Fragmentin tarkoitus on sisällyttää ja esittää lista
 * kaikista luoduista listoista.
 */
public class MainListFragment extends Fragment {

    /**
     * Lista käyttäjän listoista.
     */
    static ArrayList<UserList> lists;

    /**
     * Fragmentin RecyclerView-komponentin käyttämä adapteri.
     */
    static MainListAdapter adapter;

    /**
     * Viittaus tähän fragmentin olioon.
     */
    private static MainListFragment instance;

    /**
     * Parametritön alustaja, joka määrittää layoutin.
     */
    public MainListFragment() {super(R.layout.fragment_list_main);}

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

        RecyclerView rvMainList = view.findViewById(R.id.rvMainList);

        lists = MainActivity.lists.fetchLists();
        adapter = new MainListAdapter(lists);
        rvMainList.setAdapter(adapter);
        rvMainList.setLayoutManager(new LinearLayoutManager(getContext()));

        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Metodi päävalikon listan päivittämiselle. Saa parametreinaan listan, jonka se lisää
     * omaan listaansa listoista ja tiedottaa muutoksesta adapterille joka osaa päivittää näkymän.
     *
     * @param newList Uusi, lisättävä lista.
     */
    public static void updateList(UserList newList){
        lists.add(newList);
        int pos = lists.indexOf(newList);
        adapter.notifyItemInserted(pos);
        MainActivity.lists.updateList(lists);
        MainActivity.lists.saveLists();
    }

    /**
     * Metodi palauttamaan viittaus tähän olioon.
     *
     * @return Viittaus fragmentin olioon.
     */
    public static MainListFragment getInstance()
    {
        return instance;
    }

}