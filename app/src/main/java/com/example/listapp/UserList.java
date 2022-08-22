package com.example.listapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokka, jonka oliot pitävät sisällään jokainen yhden käyttäjän luoman listan.
 */
public class UserList implements Serializable {

    private String name;
    private ArrayList<Boolean> checkedItems;

    /**
     * Olion säilömä lista merkkijonoja.
     */
    private ArrayList<String> list;

    private int length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get-metodi olion tallentamalle listalle.
     * @return list
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     * Set-metodi olion tallentamalle listalle.
     * @param list
     */
    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public int getLength(){
        this.length = this.list.size();
        return length;
    }

    public ArrayList<Boolean> getCheckedItems() {
        return checkedItems;
    }

    public void setCheckedItems(ArrayList<Boolean> checkedItems) {
        this.checkedItems = checkedItems;
    }


    /**
     * Parametrillinen alustaja.
     * @param list
     */
    public UserList(ArrayList<String> list){
        this.list = list;
        this.length = list.size();
    }

    public UserList(String name){
        this.name = name;
        this.list = new ArrayList<>();
        this.checkedItems = new ArrayList<>();
    }

    /**
     * Metodi olion listaan lisäämiselle.
     * @param addition
     */
    public void addToList(String addition){
        list.add(addition);
        checkedItems.add(false);
    }

    public void getItem(int position){
        list.get(position);
    }
}
