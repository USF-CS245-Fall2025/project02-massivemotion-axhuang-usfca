public class DummyHeadLinkedList<T> implements List<T>{

    //Code for DummyHeadLinkedList was taken from LinkedList code and modified

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T value) {
            this.data = value;
            this.next = null;
        }
    }

    public DummyHeadLinkedList() {
        this.head = new Node<T>(null);
        this.tail = this.head;
        this.size = 0;
    }

    @Override
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > this.size) {
            throw new Exception("Invalid add location!");
        }
        Node node = new Node(element);
        Node prev = this.head.next;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        if (index == this.size) {
            this.tail = node;
        }
        this.size++;
    }

    @Override
    public boolean add(T element) {
        Node myNode = new Node<T>(element);
        this.tail.next = myNode;
        this.tail = myNode;
        this.size++;
        return true;
    }

    @Override
    public T get(int index) throws Exception {
        if(index >= this.size || index < 0) {
            throw new Exception("Index out of bounds!");
        }
        Node curr = this.head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return (T) curr.data;
    }

    @Override
    public T remove(int index) throws Exception{
        if (index < 0 || index >= this.size) {
            throw new Exception("Index out of bounds!");
        }
        Node retNode;
        Node prev = this.head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        retNode = prev.next;
        if (index == this.size - 1) {
            prev.next = null;
            this.tail = prev;
        } else {
            prev.next = retNode.next;
        }

        this.size--;
        return (T) retNode.data;
    }

    @Override
    public int size() {
        return this.size;
    }

}
