package utility.datastructures;

/*******
 * Custom List interface used to implement own list data structure
********/

public interface MyArrayInterface<E> {

/**
 Abstract Methods(no concrete implementations) Check out docs.oracle.com for array methods
 **/

    //returning the size of the collection. We will need to maintain some int with the number of elements
    int size();

    //adding an item to the end of the collection
    boolean add(E e);

    //adding an item to some index, and shifting those items at/after the index to make room
    boolean add(int index, E e);

    //return the element at the specified index
    E get(int index);

    //remove element at specified index, and then shift the remaining elements to close the gap
    E remove(int index);

    //remove all elements from the collection
    void clear();

    //check if collection contains this item (returning the index of where it can be found)
   //if you really wanted to you could return a boolean, could also use IndexOf(Object o).
    int contains(E e);

//Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
    int indexOf(E e);

//Replaces the element at the specified position in this list with the specified element.
    E set(int index, E e);
}
