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
     *
     */
    ArrayList<UserList> lists;

    /**
     *
     */
    static File listFile;

    /**
     *
     * @param file
     */
    public ListsOfLists(File file){
        listFile = file;
        this.lists = fetchLists();
    }

    /**
     *
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
     *
     * @return
     */
    public ArrayList<UserList> fetchLists(){
        ArrayList<UserList> list = new ArrayList<>();

        if (listFile.exists()){
            try{
                //use buffering
                InputStream file = new FileInputStream(listFile);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream(buffer);
                try{
                    //deserialize the List
                    list = (ArrayList<UserList>) input.readObject();
                    //display its data
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

            //testilistat
            // TODO: remove test
            UserList test1 = new UserList(new ArrayList<>());
            test1.setName("TODO");
            test1.setCheckedItems(new ArrayList<>());
            test1.addToList("pyykkää");
            test1.addToList("tiskaa");
            test1.addToList("soita äidille");

            UserList test2 = new UserList(new ArrayList<>());
            test2.setName("Kauppalista");
            test2.setCheckedItems(new ArrayList<>());
            test2.addToList("maitoa");
            test2.addToList("munia");
            test2.addToList("leipää");

            list.add(test1);
            list.add(test2);
        }


        return list;
    }

    /**
     *
     * @param updated
     */
    public void updateList(ArrayList<UserList> updated){
        lists = updated;
    }

    /**
     *
     * @param addition
     */
    public void addList(UserList addition){
        lists.add(addition);
    }

    /**
     *
     * @param pos
     */
    public void removeList(int pos){
        lists.remove(pos);
    }

    /**
     *
     * @param find
     * @return
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
