public class DummyHeadLinkedList<T> implements List<T>{

    //Code for DummyHeadLinkedList was taken from LinkedList code and modified

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

    public DummyHeadLinkedList() {
        head = new Node<T>(null);
        tail = head;
        size = 0;
    }

    @Override
    /** add method inserts a node with the specified element as its data at a specified
    * index in the list. Because of the dummyhead format, the only edge case is if tail
    * needs to be changed.
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
        Node prev = head.next;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        if (index == size) {
            tail = node;
        }
        size++;
    }

    @Override
    /** add method inserts a node with the inputted element as its data at 
    * the end (tail) of the list. Because of the dummyhead format, there are
    * no edge cases.
    * @param T for the data of the node being inserted
    * @return none
    */
    public boolean add(T element) {
        Node myNode = new Node<T>(element);
        tail.next = myNode;
        tail = myNode;
        size++;
        return true;
    }

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
        Node curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return (T) curr.data;
    }

    @Override
    /** remove method returns the data of the node at a specified index in the list after having
    * removed it from the list (and organizing its neighboring nodes properly). Because of the 
    * dummyhead format, the only edge case is if tail is being removed.
    * @param int for the index of the node that is being removed
    * @return The data of the removed node
    * @exception Exception if the inputted index is out of bounds of the size
    */
    public T remove(int index) throws Exception{
        if (index < 0 || index >= size) {
            throw new Exception("Index out of bounds!");
        }
        Node retNode;
        Node prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        retNode = prev.next;
        if (index == size - 1) {
            prev.next = null;
            tail = prev;
        } else {
            prev.next = retNode.next;
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
