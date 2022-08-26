package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

    /**
     *
     */
    static ListsOfLists lists;

    /**
     *
     */
    static MainListFragment frag;

    /**
     *
     */
    private String inputText = "";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        File file = new File(getFilesDir() + "/lists.bin");
        frag = MainListFragment.getInstance();

        lists = new ListsOfLists(file);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     * @param view
     */
    public void newList(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_list_title);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputText = input.getText().toString();
                UserList newList = new UserList(inputText);
                lists.addList(newList);
                MainListFragment.updateList(newList);
            }
        });
        builder.setNegativeButton(R.string.nay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     *
     * @param view
     */
    public void newItem(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_list_item);

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputText = input.getText().toString();
                UserListFragment.updateList(inputText);
            }
        });
        builder.setNegativeButton(R.string.nay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}