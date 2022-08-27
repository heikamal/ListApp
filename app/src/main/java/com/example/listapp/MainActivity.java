package com.example.listapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

/**
 * Ohjelman pääaktiviteetti, sisältää ensimmäisen näkymän minkä käyttäjä näkee.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Päälista. Lista, joka sisältää kaikki käyttäjän listat.
     */
    static ListsOfLists lists;

    /**
     * Viittaus päälistan fragmenttiin.
     */
    static MainListFragment frag;

    /**
     * Merkkijono säilyttämään käyttäjän sytteet.
     */
    private String inputText = "";

    /**
     * Vakio merkkijono tallennustiedoston nimelle
     */
    private final String FILENAME = "lists.bat";

    /**
     * onCreate-metodi, joka ajetaan kun ohjelma käynnistetään. Asettaa ohjelman käyttämän
     * tiedostopolun, päälistan fragmentin ja päälistan itsessään.
     *
     * @param savedInstanceState Oletusparametrina saatava käynnistyksen bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        File file = new File(getFilesDir() + "/" + FILENAME);
        frag = MainListFragment.getInstance();

        lists = new ListsOfLists(file);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Metodi uuden listan luontiin. Avaa alertdialogin joka kysyy käyttäjältä uuden listan
     * nimeä ja lisää uuden listan päälistaan.
     *
     * @param view Metodia kutsunut View-olio.
     */
    public void newList(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_list_title);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            inputText = input.getText().toString();
            UserList newList = new UserList(inputText);
            lists.addList(newList);
            MainListFragment.updateList(newList);
        });
        builder.setNegativeButton(R.string.nay, (dialog, which) -> dialog.cancel());

        builder.show();
    }

    /**
     * Metodi uuden listan kohdan luontiin. Avaa AlertDialogin, joka kysyy käyttäjältä listan kohdan
     * tekstiä
     *
     * @param view Metodia kutsunut View-olio.
     */
    public void newItem(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.new_list_item);

        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            inputText = input.getText().toString();
            UserListFragment.updateList(inputText);
        });
        builder.setNegativeButton(R.string.nay, (dialog, which) -> dialog.cancel());

        builder.show();
    }
}