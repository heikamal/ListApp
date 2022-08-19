package com.example.listapp;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import java.io.File;

/**
 * Ohjelman pääaktiviteetti, sisältää ensimmäisen näkymän minkä käyttäjä näkee.
 */
public class MainActivity extends AppCompatActivity {

    static ListsOfLists lists;

    static MainListFragment frag;

    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        File file = new File(getFilesDir() + "/lists.bin");
        frag = MainListFragment.getInstance();

        lists = new ListsOfLists(file);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newList(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Listan nimi");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                UserList newList = new UserList(m_Text);
                lists.addList(newList);
                frag.updateList(newList);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void newItem(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Uusi merkintä");

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                UserListFragment frag = UserListFragment.getInstance();
                frag.updateList(m_Text);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}