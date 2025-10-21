public class DoublyLinkedList<T> implements List<T>{

    //Code for DoublyLinkedList was taken from LinkedList code and modified

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.data = value;
            this.next = null;
            this.prev = null;
        }
    }

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //add code taken from slides and modified
    @Override
    public void add(int index, T element) throws Exception {
        if (index < 0 || index > this.size) {
            throw new Exception("Invalid add location!");
        }
        Node node = new Node(element);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else if (index == 0) {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        } else if (index == this.size) {
            node.prev = this.tail;
            this.tail.next = node;
            this.tail = node;
        } else {
            Node mover = this.head;
            for (int i = 0; i < index - 1; i++) {
                mover = mover.next;
            }
            node.next = mover.next;
            mover.next = node;
            node.prev = mover;
            node.next.prev = node;
        }
        this.size++;
    }

    @Override
    public boolean add(T element) {
        Node myNode = new Node<T>(element);
        if (this.head == null) {
            this.head = myNode;
            this.tail = myNode;
        } else {
            myNode.prev = this.tail;
            this.tail.next = myNode;
            this.tail = myNode;
        }
        this.size++;
        return true;
    }

    @Override
    public T get(int index) throws Exception {
        if(index >= this.size || index < 0) {
            throw new Exception("Index out of bounds!");
        }
        Node curr = this.head;
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
        if (this.size == 1) {
            retNode = this.head;
            this.head = null;
            this.tail = null;
        } else if (index == 0) {
            retNode = this.head;
            this.head = this.head.next;
            this.head.prev = null;
        } else {
            Node delNode = this.head;
            for (int i = 0; i < index; i++) {
                delNode = delNode.next;
            }
            retNode = delNode;
            if (index == this.size - 1) {
                this.tail = delNode.prev;
                this.tail.next = null;
            } else {
                delNode.prev.next = delNode.next;
                delNode.next.prev = delNode.prev;
            }
        }

        this.size--;
        return (T) retNode.data;
    }

    @Override
    public int size() {
        return this.size;
    }

}