public class ArrayList<T> implements List<T> {

    private int size;
    private T[] arr;

    // Constructor taken from slides;
    public ArrayList() {
        arr = (T[]) new Object[10];
        size = 0;
    }

    // grow_array method taken from slides
    private void grow_array() {
        T [] new_arr = (T[]) new Object[arr.length * 3 / 2 + 1];
        for (int i = 0; i < arr.length; i++) {
            new_arr[i] = arr[i];
        }
        arr = new_arr;
    }

    /** add method inserts a specified element at a specified index in the array.
    * @param int for the index being inserted at
    * @param T for the element being inserted
    * @return none
    * @exception Exception if the inputted index is out of bounds of the size
    */
    @Override
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Invalid add location!");
        }
        if (size == arr.length) {
            grow_array();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = element;
        size++;
    }

    /** add method inserts a specified element at the first empty index in the array (becoming
    * the last element).
    * @param T for the element being inserted
    * @return none
    */
    @Override
    public boolean add(T element) {
        if (size == arr.length) {
            grow_array();
        }
        arr[size] = element;
        size++;
        return true;
    }

    /** get method returns the element at a specified index in the array.
    * @param int for the index of the value that wants to be retrieved
    * @return The element at the inputted index
    * @exception Exception if the inputted index is out of bounds of the size
    */
    @Override
    public T get(int index) throws Exception {
        if(index >= size || index < 0) {
            throw new Exception("Index out of bounds!");
        }
        return arr[index];
    }

    /** remove method returns the element at a specified index in the array after having
    * deleted it from the array (and shifting the array so there isn't an empty slot between values).
    * @param int for the index of the value that is being removed
    * @return The element at the inputted index
    * @exception Exception if the inputted index is out of bounds of the size
    */
    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new Exception("Index out of bounds!");
        }
        T retVal = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return retVal;
    }

    /** size method returns the amount of elements in the list.
    * @return The amount of values stored in the list
    */
    @Override
    public int size() {
        return size;
    }

}
