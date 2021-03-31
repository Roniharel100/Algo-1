package TortoiseAndHare;

public class THList {
    private THNode head;
    private THNode tail;
    private boolean isCi;

    public THList(boolean b){
        this.head = new THNode();
        this.tail = this.head;
        this.isCi = b;
    }

    public void add(){
        this.tail.setNext(new THNode());
        this.tail = this.tail.next;
        if (this.isCi){
            this.tail.next = this.head;
        }
    }

    public void add(int data){
        this.tail.setNext(new THNode(data));
        this.tail = this.tail.next;
        if (this.isCi){
            this.tail.next = this.head;
        }
    }

    public THNode getHead() {
        return head;
    }

    public THNode getTail() {
        return tail;
    }

    public void setTail(THNode tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        String s="";
        THNode temp = head;
        s += temp.data+"->";
        while(temp != tail){
            temp = temp.next;
            s += temp.data+"->";
        }
        return s;
    }

    public class THNode {
        private THNode next;
        private int data;

        public THNode() {
            this.next = null;
            this.data = 0;
        }

        public THNode(int data) {
            this.next = null;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public THNode getNext() {
            return next;
        }

        public void setNext(THNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return ""+data;
        }
    }
}





