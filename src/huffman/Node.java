package huffman;

/**
 * Created by jo930_000 on 2016-11-16.
 */
public class Node {
    public trecord data;
    public int priority;
    public Node next;

    Node(trecord data, int priority) {
        this.data = data;
        this.priority = priority;
    }

    Node(trecord data, int priority, Node next) {
        this.data = data;
        this.priority = priority;
        this.next = next;
    }
}
