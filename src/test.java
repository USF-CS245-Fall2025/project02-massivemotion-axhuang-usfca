public class test {
    public static void main(String[] args) {
        DoublyLinkedList myDLL = new DoublyLinkedList<Integer>();
        myDLL.add(1);
        myDLL.add(2);
        myDLL.add(3);
        myDLL.add(4);
        myDLL.add(5);
        myDLL.printList();
        try {
        myDLL.remove(4);
        myDLL.remove(3);
        } catch (Exception e) {
            System.err.println("yh");
            System.exit(0);
        }
        myDLL.printList();
        myDLL.add(6);
        myDLL.add(7);
        myDLL.printList();
    }
}
