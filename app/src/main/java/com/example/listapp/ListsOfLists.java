package com.example.listapp;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListsOfLists {

    ArrayList<UserList> lists;

    public ListsOfLists(){
        lists = fetchLists();
    }

    public void saveLists(ArrayList<UserList> list){
        try{
            OutputStream file = new FileOutputStream("lists.bin");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
            try{
                output.writeObject(list);
            }
            finally{
                output.close();
            }
        } catch(IOException e){}
    }

    public ArrayList<UserList> fetchLists(){
        UserList test1 = new UserList(new ArrayList<>());
        test1.setName("TODO");
        test1.addToList("pyykkää");
        test1.addToList("tiskaa");
        test1.addToList("soita äidille");

        UserList test2 = new UserList(new ArrayList<>());
        test2.setName("Kauppalista");
        test2.addToList("maitoa");
        test2.addToList("munia");
        test2.addToList("leipää");

        ArrayList<UserList> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);

        return list;
    }

    public void addList(UserList addition){
        lists.add(addition);
    }
}
