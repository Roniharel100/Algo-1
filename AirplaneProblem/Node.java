package AirplaneProblem;

import java.util.LinkedList;

class Node {
    private int right, down, value, countOfPath, valueA, valueB;
    private LinkedList<String> paths;

    public Node(int r, int d) {
        this.right = r;
        this.down = d;
        this.value = 0;
        this.countOfPath = 0;
        this.paths = new LinkedList<>();
        this.valueA = 0;
        this.valueB=0;
    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
    }

    public int getValue() {
        return value;
    }

    public int getValueA() {
        return valueA;
    }

    public int getValueB() {
        return valueB;
    }

    public int getCountOfPath() {
        return countOfPath;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValueA(int valueA) {
        this.valueA = valueA;
    }

    public void setValueB(int valueB) {
        this.valueB = valueB;
    }

    public void setCountOfPath(int countOfPath) {
        this.countOfPath = countOfPath;
    }

    public LinkedList<String> getPaths() {
        return paths;
    }

    @Override
    public String toString() {
        return "Node{" +
                "right=" + right +
                ", down=" + down +
                ", value=" + value +
                ", countOfPath=" + countOfPath +
                '}';
    }
}
