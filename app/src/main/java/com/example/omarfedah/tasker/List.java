package com.example.omarfedah.tasker;



abstract class List {

    private int size ;

    /**
     *
     */
     List(){
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
