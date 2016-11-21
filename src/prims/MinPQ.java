package prims;

/**
 * Created by jo930_000 on 2016-11-14.
 */
public class MinPQ {
    Vertex[] heap = new Vertex[65];
    int init_size = 0;
    int heap_size;

    public void create_heap() {
        for(int i = 0; i < 65; i++) {
            heap[i] = new Vertex();
        }
    }

    // implement MAX-HEAPIFY(A, i)
    public void min_heapify(int index) {
        int min;
        int left_child = 2*index;
        int right_child = 2*index+1;

        if (left_child <= heap_size && heap[left_child].weight < heap[index].weight){
            min = left_child;
        }
        else {
            min = index;
        }

        if (right_child <= heap_size && heap[right_child].weight < heap[min].weight) {
            min = right_child;
        }

        if (min != index) {
            Vertex temp;
            temp = heap[index];
            heap[index] = heap[min];
            heap[min] = temp;
            this.min_heapify(min);
        }
    }

    // implement BUILD-MIN-HEAP(A)
    public void build_min_heap() {
        heap_size = init_size;

        for(int i = heap_size/2; i >= 1; i--) {
            this.min_heapify(i);
        }
    }

    // a. implement insert(S, x)
    public void insert(Vertex x) {
        init_size++;
        heap[init_size] = x;
        this.build_min_heap();
    }

    // b. implement min(S)
    public Vertex min() {
        return heap[1];
    }

    // c. implement extract_min(S)
    public void extract_min() {
        heap[1].setWeight(heap[init_size].getWeight());
        heap[1].setSrc(heap[init_size].getSrc());
        heap[1].setParent(heap[init_size].getParent());
        heap[init_size].setWeight(Integer.MAX_VALUE);
        heap[init_size].setSrc(-1);
        heap[init_size].setParent(-1);
        init_size--;
        this.build_min_heap();
    }

    public boolean isEmpty() {
        if(this.heap[1].src != -1) return false;
        return true;
    }

    public int search(int src) {
        for(int i = 1; i <= heap_size; i++) {
            if(heap[i].src == src) return i;
        }
        return 0;
    }

    public boolean contains(int src) {
        for(int i = 1; i <= heap_size; i++) {
            if(heap[i].src == src) return true;
        }
        return false;
    }
}