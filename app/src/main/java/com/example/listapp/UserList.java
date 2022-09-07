package com.example.listapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Luokka, jonka oliot pitävät sisällään jokainen yhden käyttäjän luoman listan.
 */
public class UserList implements Serializable {

    /**
     * Listan nimi merkkijonona.
     */
    private String name;

    /**
     * ArrayList-lista, joka säilöö totuusarvomuuttujia onko listan kohdat jo merkattu
     * valintalaatikoilla vai ei.
     */
    private ArrayList<Boolean> checkedItems;

    /**
     * Olion säilömä lista merkkijonoja.
     */
    private ArrayList<String> list;

    /**
     * Listan pituus kokonaislukuna.
     */
    private int length;

    /**
     * Get-metodi Listan nimelle.
     *
     * @return Listan nimi merkkijonona.
     */
    public String getName() {
        return name;
    }

    /**
     * Set-metodi listan nimelle.
     *
     * @param name Merkkijono, listan nimi.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get-metodi olion tallentamalle listalle.
     *
     * @return list
     */
    public ArrayList<String> getList() {
        return list;
    }

    /**
     * Set-metodi olion tallentamalle listalle.
     *
     * @param list
     */
    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    /**
     * Get-metodi listan pituudelle.
     *
     * @return Listan pituus kokonaislukuna.
     */
    public int getLength(){
        this.length = this.list.size();
        return length;
    }

    /**
     * Get-metodi listan "merkatuille" kohdille.
     *
     * @return ArrayList, mikä sisältää totuusarvomuuttujat siitä mitkä kohdat listasta ollaan
     * merkattu valintabokseilla.
     */
    public ArrayList<Boolean> getCheckedItems() {
        return checkedItems;
    }

    /**
     * Set-metodi listan "merkatuille" kohdille.
     *
     * @param checkedItems Lista totuusavomuuttujille siitä mitkä kohdat ollaan merkattu valintalaatikoilla.
     */
    public void setCheckedItems(ArrayList<Boolean> checkedItems) {
        this.checkedItems = checkedItems;
    }


    /**
     * Parametrillinen alustaja.
     *
     * @param list
     */
    public UserList(ArrayList<String> list){
        this.list = list;
        this.length = list.size();
    }

    /**
     * Parametrillinen alustaja. Luo listan sille annetun nimen perusteella. Asettaa listat listan
     * kohdille ja totuusarvomuuttujille.
     *
     * @param name Listan nimi merkkijonona.
     */
    public UserList(String name){
        this.name = name;
        this.list = new ArrayList<>();
        this.checkedItems = new ArrayList<>();
    }

    /**
     * Metodi olion listaan lisäämiselle.
     *
     * @param addition
     */
    public void addToList(String addition){
        list.add(addition);
        checkedItems.add(false);
    }

    /**
     * Metodi käyttäjälistan alkion hakemiselle sen indeksiluvun perusteella.
     *
     * @param position Alkion järjestysluku listassa kokonaislukuna.
     */
    public void getItem(int position){
        list.get(position);
    }

    /**
     * Metodi kohdan poistamiselle käyttälistasta sen indeksiluvun perusteella.
     *
     * @param position Alkoin järjestysluku listassa kokonaislukuna.
     */
    public void removeItem(int position){
        list.remove(position);
    }
}
