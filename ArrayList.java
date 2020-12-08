// Class ArrayList<E> can be used to store a list of values of type E.
import java.util.*;
import java.io.Serializable;

/**
 * ArrayList class
 *
 * @author      Building Java Programs 4rd Edition
 * @version     2018-10-18
 */
public class ArrayList<E> implements Serializable, Iterable<E> {
    /** list of values*/
    private E[] elementData; // list of values
    /** current number of elements in the lists*/
    private int size;        // current number of elements in the list
    /** defult capcity*/
    public static final int DEFAULT_CAPACITY = 50;

    // post: constructs an empty list of default capacity
    /**
     * Constructor for the ArrayList
     *
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // pre : capacity >= 0 (throws IllegalArgumentException if not)
    // post: constructs an empty list with the given capacity

    /**
    * Constructor for the ArrayList
    * @param  capacity capacity
    */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (E[]) new Object[capacity];
        size = 0;
    }

    // post: returns the current number of elements in the list
    /**
     * get size of ArrayList
     * @return size of ArrayList
     */
    public int size() {
        return size;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: returns the value at the given index in the list
    /**
     * get element at certain index
     *
     * @param  index index
     * @return  element at the index
     */
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    // post: creates a comma-separated, bracketed version of the list
    /**
     * toString
     *
     * @return  string result
     */    
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    // post : returns the position of the first occurrence of the given
    //        value (-1 if not found)
    /**
     * returns the position of the first occurrence of the given value
     *
     * @param  value  given value
     * @return    returns the position of the first occurrence of the given value
     */
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // post: returns true if list is empty, false otherwise

    /**
     * returns returns true if list is empty, false otherwise
     *
     * @return    returns true if list is empty, false otherwise
     */    
    public boolean isEmpty() {
        return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    //       false otherwise
    /**
     * returns true if the given value is contained in the list, false otherwise
     *
     * @param  value  given value
     * @return   returns true if the given value is contained in the list, false otherwise
     */
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    // post: appends the given value to the end of the list
    /**
     * appends the given value to the end of the list
     *
     * @param  value  given value
     */    
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    // pre : 0 <= index <= size() (throws IndexOutOfBoundsException if not)
    // post: inserts the given value at the given index, shifting subsequent
    //       values right
    /**
     * add the given value to the index of the list
     * @param index index
     * @param  value  given value
     */        
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    /**
     * remove the  value in the list
     * @param value value
     */    
    public void remove(E value) {
        if (contains(value)){
            remove(indexOf(value));
        }
    }
    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: removes value at the given index, shifting subsequent values left
    /**
     * remove the  value at the index of the list
     * @param index index
     */    
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
    }

    // pre : 0 <= index < size() (throws IndexOutOfBoundsException if not)
    // post: replaces the value at the given index with the given value
    /**
     * set the  value at the index of the list
     * @param index index
     * @param value value
     */        
    public void set(int index, E value) {
        checkIndex(index);
        elementData[index] = value;
    }

    // post: list is empty
    /**
     * clear the list
     * 
     */         
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    // post: appends all values in the given list to the end of this list
    /**
     * appends all values in the given list to the end of this list
     * @param other other ArrayList
     */    
    public void addAll(ArrayList<E> other) {
        ensureCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    // post: returns an iterator for this list
    /**
     * returns an iterator for this list
     * @return iterator
     */        
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    // post: ensures that the underlying array has the given capacity; if not,
    //       the size is doubled (or more if given capacity is even larger)
    /**
     * ensures that the underlying array has the given capacity; if not,the size *1.5(or more if given capacity is even larger)
     * @param capacity the capcacity
     */        
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = (int)Math.round(elementData.length * 1.5+ 1);
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // post: throws an IndexOutOfBoundsException if the given index is
    //       not a legal index of the current list
    /**
     * ensures that if the index is not smaller than 0 and smaller than size
     * @param index index
     */           
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }
    
    /**
     * Iterator
     * 
     */  
    private class ArrayListIterator implements Iterator<E> {
        /** current position within the list*/
        private int position;           // current position within the list
        /** whether it's okay to remove now*/
        private boolean removeOK;       // whether it's okay to remove now

        // post: constructs an iterator for the given list
        /**
         * constructs an iterator for the given list
         * 
         */            
        public ArrayListIterator() {
            position = 0;
            removeOK = false;
        }

        // post: returns true if there are more elements left, false otherwise
        /**
         * check if has next element
         * 
         * @return true if there are more elements left, false otherwise
         */                    
        public boolean hasNext() {
            return position < size();
        }

        // pre : hasNext() (throws NoSuchElementException if not)
        // post: returns the next element in the iteration
        /**
         * retrun the the element in the iteration
         * 
         * @return returns the next element in the iteration
         */               
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData[position];
            position++;
            removeOK = true;
            return result;
        }

        // pre : next() has been called without a call on remove (throws
        //       IllegalStateException if not)
        // post: removes the last element returned by the iterator
        /**
         * removes the last element returned by the iterator
         * 
         */                    
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(position - 1);
            position--;
            removeOK = false;
        }
    }
}
