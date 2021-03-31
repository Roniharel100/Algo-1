package ParkingProblem;

public class linked_list_implement {

    private node_implement head, tail;
    private int size;

    public linked_list_implement() {
        head = tail = null;
        size = 0;
    }

    // Appends the specified element to the end of this list.
    public void add(char obj) {
        if (head == null) {
            head = new node_implement(obj, null, null);
            tail = head;
            head.setNext(tail);
            head.setPrev(tail);
        }
        else {
            node_implement n = new node_implement(obj, tail, head);
            tail.setNext(n);
            tail = n;
            head.setPrev(tail);
        }
        size++;
    }

    public node_implement getHead() {
        return head;
    }

    public node_implement getNext(node_implement n) {
        return n.getNext();
    }

    public String toString() {
        String s = "[";
        if (head != null) {
            s = s + head.toString() + ", ";
            for (node_implement n = head.getNext(); n != head; n = n.getNext()) {
                s = s + n.toString() + ", ";
            }
            s = s.substring(0, s.length() - 2);
        }
        return s + "]";
    }

    public int getSize() {
        return size;
    }


}
