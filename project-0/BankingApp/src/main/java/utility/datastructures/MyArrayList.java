package utility.datastructures;


import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements MyArrayInterface <E>, Iterable<E>{
  int size;
  int capacity;
  Object [] array;
  E[] theItem; //generic data structure


/** array list takes in an initial capacity,the default capacity of 10 is granted after the first add to the list(Googled)
 * Line 19 Objects and the data are not compatible,so you have to cast it to a generic type
 * **/
@SuppressWarnings("unchecked")
    public MyArrayList(){
      size = 0;
      capacity = 10;
      theItem = (E[]) new Object[capacity]; // initialize the array data to a new object array to the capacity


    }

    @Override
    public int size() {
        return size; //this refers to current size
    }

/** Appends the specified element to the end of this list.
 * Will always return true because you are always able to add a new item
 * Check if size is less than the capacity, if so set theItem to index size and increment.
 * Else gorw the size of the array first then add the item
**/
    @Override
    public boolean add(E e) {
        if(size < capacity){
            theItem[size] = e;
            size++;
            return true;
        } else{
            growArray();
            theItem[size] = e;
            return true;
        }
    }

    /**will need a growth method or can use reallocate instead of growArray(does exact same thing)
     * initialize a new array > than old
     * copy the old array
     * set the array reference to the new array-discarding the old one for garbage collection
     * update capacity with the new array size*/
    private void growArray(){
            capacity *=2;
            theItem = Arrays.copyOf(theItem, capacity);

    }

    /** Inserts the specified element at the specified position in this list.
     * Check index first before adding new item and if there is no space, call growArray() method
     * Then shift elements down using a for loop
     * Lines 73- 74 add item in the index & whenever add a new item into the array have to increment the size
     *
     * @return*/
    @Override
    public boolean add(int index, E e) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: " + index + "is out of bounds");
        }
        if(size >= capacity) {
                growArray();
            }
        for(int i = size; i > index; i--){
            theItem[i] = theItem[i-1];
        }
        theItem[index] = e;
        size++;
        return false;
    }

    /** Returns the element at the specified position in this list.
     * check size of index first, if valid return the item at the positioned index
     * */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + "is out of bounds");
        }
        return theItem[index];
    }


/** Returns the index of the first occurrence of the specified element in this list,
 * or -1 if this list does not contain the element.
 * check if item(aka e = generic type) is not null, then loop through array for an item
 * if array contains an item return the item at the specific index, else return -1.
 * Can not return 0 because index starts at 0. * **/
    @Override
    public int indexOf(E e) {
        if (e != null) {
            for (int i = 0; i < size; i++) {
                if (theItem[i].equals(e))
                    return i;
            }
        } else {
            return -1;
        }
        return -1;
    }


/** Removes the element at the specified position in this list.
 * Line 119 Create a return item and set it equal to the data at indexed item
 *Loop through the position where item is being removed and increment.
 * Line 122 copying items from a lower index to a higher index, essentiaally shifting items
 * decrease size removing items and then return new array
 * **/
    @Override
    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + "is out of bounds");
        }
        E returnedItem = theItem[index];
        for(int i = index + 1; i < size; i++){
            theItem[i-1] = theItem[i];
        }
        size--;
        return returnedItem;
    }

    /**Replaces the element at the specified position in this list with the specified element.
     * First check if index is valid
     * set old entry to the item of the positioned index, then replace it with new item
     *  */
    @Override
    public E set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + "is out of bounds");
        }
        E oldEntry = theItem[index];
        theItem[index] = e;
        return oldEntry;
    }


    /**array returns to original size**/
    @Override
    public void clear() {
    initialSize();
    }

    @SuppressWarnings("unchecked")
    private void initialSize() {
        size = 0;
        capacity = 10;
        theItem = (E[]) new Object[capacity];
    }


    /** check if item(aka e = generic type) is not null, then loop through array for an item
     * if array contains an item return the item at the specific index, else return -1.
     * Can not return 0 because index starts at 0. * **/
    @Override
    public int contains(E e) {
   return 0;
    }


        /**
         * This is a manuel copy by looping and copying each element.
         * We can also use Arrays.copyOf(), which does the same thing
        Object[] temp = new Object[maxSize];

        for(int i = 0; i < size++; i++) {
            temp[i] = array[i];
        }
         ***/

/** Override default toString method inorder to view items in arraylist
 * Concatenating our results and attaching more items to our results (line 171)**/
@Override
    public String toString() {
    String result = "[" + theItem[0];
    for (int i = 1; i < size; i++) {
        result += ", " + theItem[i];
    }
    result += ']';
    return result;
    }
    /**
     *returns an iterator object to traverse(walk through) through the list
     */

    public Iterator<E> iterator(){
        Iterator<E> iE = new Iterator<E>() {

            private int currentIndex = 0;

            /**
             *Checking array to find next available item(index). True if index is available false if not
             */
            @Override
            public boolean hasNext() {
                return currentIndex < size && theItem[currentIndex] !=null;
            }

            /**
             *Returns the first index it finds and then advances to the next one
             * return the item(object) at the current index
             */
            @Override
            public E next() {
                return (E) theItem[currentIndex++];
            }
        };
        return iE;
    }

}




////////////////////////////////// THIS STUFF IS HINTS ABOUT IMPLEMENTING ARRAYLIST ////////////////////////////////////
//      At the core of your arraylist implementation there WILL BE A primitive array.
//    this works - our hack we avoid generics, instead using an array of Objects,
//     because all objects inherit eventually from Object class.
//    Object[] o = new Object[2];
//
//    this doesn't work, can't directly build an array of generics
//    E[] w = new E[2];
//
//    When we want to return our array, we would need to "cast" it like this "(Type) thing" we turn the thing into type.
//    public E[] getArray() {
//        return (E[]) o;
//    }
////////////////////////////////// THIS STUFF IS HINTS ABOUT IMPLEMENTING ARRAYLIST ////////////////////////////////////