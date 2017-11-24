package com.example.omarfedah.tasker;

/**
 * Created by Robert on 2017-11-22.
 */

public abstract class List {

    int size ;

    public List(){
        super()  ;
        size = 0 ;
    }

    //EDIT TO UML, Corrected attribute to Collectable instead of List
    public Collectable[] getList(){
        return null ;
        //PLEASE IMPLEMENT
    }

    public void addToList(Collectable collectable){

    }


    public void removeFromList(Collectable collectable){


    }
}
