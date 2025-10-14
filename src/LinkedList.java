public class LinkedList<T> implements List<T>{

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

    public LinkedList() {
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
            this.head = node;
        } else if (index == this.size) {
            this.tail.next = node;
            this.tail = node;
        } else {
            Node prev = this.head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
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
            this.tail.next = myNode;
            this.tail = myNode;
        }
        this.size++;
        return true;
    }

    //get code taken from slides
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

    //remove code taken from slides and modified
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
            this.head = head.next;
        } else {
            Node prev = this.head;
            for (int i=0; i < index- 1; i++) {
                prev = prev.next;
            }
            retNode = prev.next;
            if (index == this.size - 1) {
                prev.next = null;
                this.tail = prev;
            } else {
                prev.next = retNode.next;
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
