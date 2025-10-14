public class ArrayList<T> implements List<T> {

    private int size;
    private T[] arr;

    // Constructor taken from slides;
    public ArrayList() {
        this.arr = (T[]) new Object[10];
        this.size = 0;
    }

    // grow_array method taken from slides
    private void grow_array() {
        T [] new_arr = (T[]) new Object[this.arr.length * 3 / 2 + 1];
        for (int i = 0; i < this.arr.length; i++) {
            new_arr[i] = this.arr[i];
        }
        this.arr = new_arr;
    }

    @Override
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > this.size) {
            throw new Exception("Invalid add location!");
        }
        if (this.size == this.arr.length) {
            grow_array();
        }
        for (int i = this.size; i > index; i--) {
            this.arr[i] = this.arr[i-1];
        }
        this.arr[index] = element;
        this.size++;
    }

    @Override
    public boolean add(T element) {
        if (this.size == this.arr.length) {
            grow_array();
        }
        this.arr[this.size] = element;
        this.size++;
        return true;
    }

    @Override
    public T get(int index) throws Exception {
        if(index >= this.size || index < 0) {
            throw new Exception("Index out of bounds!");
        }
        return this.arr[index];
    }

    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= this.size) {
            throw new Exception("Index out of bounds!");
        }
        T retVal = this.arr[index];
        for (int i = index; i < this.size - 1; i++) {
            this.arr[i] = this.arr[i+1];
        }
        this.size--;
        return retVal;
    }

    @Override
    public int size() {
        return this.size;
    }

}
