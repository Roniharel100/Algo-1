package ParkingProblem;

public class node_implement {

    private char data;
    private node_implement prev, next;

    public node_implement(char data, node_implement prev, node_implement next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public String toString() {
        return "" + this.data;
    }

    public void setData(char c) {
        this.data = c;
    }

    public char getData() {
        return this.data;
    }

    public node_implement getNext() {
        return this.next;
    }

    public node_implement getPrev() {
        return this.prev;
    }

    public void setPrev(node_implement t) {
        this.prev=t;
    }

    public void setNext(node_implement t) {
        this.next=t;
    }
}

