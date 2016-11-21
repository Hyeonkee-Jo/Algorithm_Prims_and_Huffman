package prims;

/**
 * Created by jo930_000 on 2016-11-14.
 */
public class Vertex {
    int src;
    int weight;
    int parent;

    public Vertex() {
        this.src = -1;
        this.weight = Integer.MAX_VALUE;
        this.parent = -1;
    }

    public Vertex(int src, int weight, int dest) {
        this.src = src;
        this.weight = weight;
        this.parent = dest;
    }

    public int getSrc() {
        return this.src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getParent() { return this.parent; }

    public void setParent(int parent) { this.parent = parent; }

}
