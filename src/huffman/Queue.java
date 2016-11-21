package huffman;

/**
 * Created by jo930_000 on 2016-11-16.
 */
public class Queue<Type> {
    private Node front = null;
    private int size = 0;


    public void enqueue(trecord data, int priority) {
	/*
	Key) Priority enqueue
	*/
        Node temp = front;

        if(front == null) {
            front = new Node(data, priority);
            size++;
        }
        else if(priority<front.priority) {
            front = new Node(data, priority, front);
            size++;
        }
        else {
            if(temp.next != null) {
                while (priority > temp.next.priority && temp.next != null) {
                    temp = temp.next;
                }
            }
            temp.next = new Node(data, priority, temp.next);
            size++;
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        Node temp = front;
        buf.append("@@@@ Queue @@@@\n");
        while( temp != null ) {
            buf.append(temp.data + "(" + temp.priority + ")\n");
            temp = temp.next;
        }
        return buf.toString();
    }

    public trecord first() {
        if(size == 0) throw new IllegalStateException("the queue is empty");
        return front.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public trecord remove() {
        if(size == 0) throw new IllegalStateException("the queue is empty");
        trecord object = front.data;
        front = front.next;
        --size;
        return object;
    }

    public int size() {
        return size;
    }

}
