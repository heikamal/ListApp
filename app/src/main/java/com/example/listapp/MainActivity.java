package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Ohjelman pääaktiviteetti, sisältää ensimmäisen näkymän minkä käyttäjä näkee.
 */
public class MainActivity extends AppCompatActivity {

    static ListsOfLists lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        lists = new ListsOfLists();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newList(){

    }
}