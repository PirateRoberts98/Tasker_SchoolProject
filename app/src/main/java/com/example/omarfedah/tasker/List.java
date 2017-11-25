package com.example.omarfedah.tasker;

import java.util.ArrayList;

/**
 * A class to store a list of elements.
 * @param <E> Type stored in List.
 */
public abstract class List<E> {

    /**
     * Instance of ArrayList used to store the elements of List.
     */
    private ArrayList<E> arrayList;

    /**
     * Size of the List.
     */
    private int size ;

    /**
     * Constructor to create a new instance of List.
     */
    List() {
        arrayList = new ArrayList();
        size = 0;
    }

    /**
     * Adds an element to the end of the List.
     * @param elem Element to be added.
     */
    public void add(E elem){
        arrayList.add(elem);
    }

    /**
     * Removes an element from the List.
     * @param elem Element to be removed.
     * @return True if the element is removed successfully.
     */
    public boolean remove(E elem){
        if (arrayList.remove(elem) == true) {
            size--;
            return true;
        }

        return false;
    }

    /**
     * Gets the instance of List.
     * @return The instance of List.
     */
    public ArrayList<E> getList() {
        return arrayList;
    }

    /**
     * Gets the size of the List.
     * @return The size of the List.
     */
    public int getSize() {
        return size;
    }
}
