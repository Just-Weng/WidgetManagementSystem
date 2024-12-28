public class node {
    int data;
    node next;

    public node() {
        this.data = 0;
        this.next = null;
    }

    public node(int data) {
        this.data = data;
        this.next = null;
    }

    public node(int data, node next) {
        this.data = data;
        this.next = next;
    }
}
