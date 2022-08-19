package com.example.listapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokka, jonka oliot pitävät sisällään jokainen yhden käyttäjän luoman listan.
 */
public class UserList implements Serializable {

    private String name;

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
    }

    /**
     * Metodi olion listaan lisäämiselle.
     * @param addition
     */
    public void addToList(String addition){
        list.add(addition);
    }

    public void getItem(int position){
        list.get(position);
    }
}
