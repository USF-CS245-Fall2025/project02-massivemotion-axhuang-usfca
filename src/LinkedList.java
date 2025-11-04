public class LinkedList<T> implements List<T>{

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T value) {
            data = value;
            next = null;
        }
    }

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    //add code taken from slides and modified
    @Override
    /** add method inserts a node with the specified element as its data at a specified
    * index in the list. Utilizes multiple edge cases to handle situations like adding to
    * an empty list and adding to the head/tail of a list.
    * @param int for the index to insert the new node at
    * @param T for the data of the node being inserted
    * @return none
    * @exception Exception if the inputted index is out of bounds of the size
    */
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Invalid add location!");
        }
        Node node = new Node(element);
        if (head == null) {
            head = node;
            tail = node;
        } else if (index == 0) {
            node.next = head;
            head = node;
        } else if (index == size) {
            tail.next = node;
            tail = node;
        } else {
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
        }
        size++;
    }

    @Override
    /** add method inserts a node with the inputted element as its data at 
    * the end (tail) of the list. The only edge case is if the list is empty.
    * @param T for the data of the node being inserted
    * @return none
    */
    public boolean add(T element) {
        Node myNode = new Node<T>(element);
        if (head == null) {
            head = myNode;
            tail = myNode;
        } else {
            tail.next = myNode;
            tail = myNode;
        }
        size++;
        return true;
    }

    //get code taken from slides
    @Override
    /** get method returns the data of the node at a specified index in the list.
    * @param int for the index of the node whose data is being retrieved
    * @return The data of the node at the inputted index
    * @exception Exception if the inputted index is out of bounds of the size
    */
    public T get(int index) throws Exception {
        if(index >= size || index < 0) {
            throw new Exception("Index out of bounds!");
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return (T) curr.data;
    }

    //remove code taken from slides and modified
    @Override
    /** remove method returns the data of the node at a specified index in the list after having
    * removed it from the list (and organizing its neighboring nodes properly). Utilizes multiple
    * edge cases to handle situations like removing from a list with only 1 value and removing the 
    * head/tail of a list.
    * @param int for the index of the node that is being removed
    * @return The data of the removed node
    * @exception Exception if the inputted index is out of bounds of the size
    */
    public T remove(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new Exception("Index out of bounds!");
        }
        Node retNode;
        if (size == 1) {
            retNode = head;
            head = null;
            tail = null;
        } else if (index == 0) {
            retNode = head;
            head = head.next;
        } else {
            Node prev = head;
            for (int i=0; i < index- 1; i++) {
                prev = prev.next;
            }
            retNode = prev.next;
            if (index == size - 1) {
                prev.next = null;
                tail = prev;
            } else {
                prev.next = retNode.next;
            }
        }

        size--;
        return (T) retNode.data;
    }

    @Override
    /** size method returns the amount of nodes in the list.
    * @return The amount of nodes stored in the list
    */
    public int size() {
        return size;
    }

}
