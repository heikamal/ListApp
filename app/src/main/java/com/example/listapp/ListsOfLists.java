package com.example.listapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Luokka listojen muokkaamiseen, lisäämiseen, lukemiseen ja tallentamiseen tarkoitettuihin metodeihin.
 */
public class ListsOfLists {

    /**
     * Arraylist-lista käyttäjän tekemistä listoista.
     */
    ArrayList<UserList> lists;

    /**
     * Tiedosto johon listat tallennetaan.
     */
    static File listFile;

    /**
     * Parametrillinen alustaja. Asettaa tiedoston johon listat tallennetaan ja luetaan.
     * @param file File-olio, mihin tehdyt listat tallennetaan.
     */
    public ListsOfLists(File file){
        listFile = file;
        this.lists = fetchLists();
    }

    /**
     * Avaa OutputStreamin, joka kirjoittaa kaikki listat määriteltyyn tiedostoon.
     */
    public void saveLists(){
        try{
            OutputStream file = new FileOutputStream(listFile);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try{
                output.writeObject(lists);
            }
            finally{
                output.close();
            }
        } catch(IOException e){
            System.out.println(e);
        }
    }

    /**
     * Metodi tallennettujen listojen hakemiselle. Avaa määritellyn tiedoston ja lukee sieltä
     * kaikki tallennetut listan ArrayList-listaan. Jos tiedostoa ei löydy, metodi luo valmiin
     * oletuslistan.
     *
     * @return ArrayList-lista listoista jotka metodi luki tiedostosta.
     */
    public ArrayList<UserList> fetchLists(){
        ArrayList<UserList> list = new ArrayList<>();

        if (listFile.exists()){
            try{
                InputStream file = new FileInputStream(listFile);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
                try{
                    list = (ArrayList<UserList>) input.readObject();
                    for(UserList userList: list){
                        System.out.println(userList);
                    }
                }
                finally{
                    input.close();
                }
            } catch (IOException | ClassNotFoundException e){
                System.out.println(e);
            }
        } else {

            //oletuslista
            UserList test1 = new UserList(new ArrayList<>());
            test1.setName("Welcome! Click me!");
            test1.setCheckedItems(new ArrayList<>());
            test1.addToList("Press 'New' to create new items");
            test1.addToList("Long press an item to remove it");

            list.add(test1);
        }


        return list;
    }

    /**
     * Korvaa olemassa olevan listan listoista saamallaan uudella listalla.
     *
     * @param updated päivitetty ArrayList-lista käyttäjälistoista
     */
    public void updateList(ArrayList<UserList> updated){
        lists = updated;
    }

    /**
     * Lisää uuden listan päälistaan.
     *
     * @param addition uusi käyttäjän luoma lista
     */
    public void addList(UserList addition){
        lists.add(addition);
    }

    /**
     * Metodi poistaa käyttäjälistan päälistasta.
     *
     * @param pos Poistettavan listan indeksiluku päälistassa kokonaislukuna.
     */
    public void removeList(int pos){
        lists.remove(pos);
    }

    /**
     * Etsii käyttäjälistan päälistasta listan nimen perusteella.
     *
     * @param find Käyttäjälista jonka paikkaa haetaan.
     * @return Parametreina annetun listan indeksiluku päälistassa tai -1 jos käyttäjälistaa ei löydy.
     */
    public int findList(UserList find){

        for (UserList i : lists){
            if (i.getName().equals(find.getName())){
                return lists.indexOf(i);
            }
        }

        return -1;
    }
}
