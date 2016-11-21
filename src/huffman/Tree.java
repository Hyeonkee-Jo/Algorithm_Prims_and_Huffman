package huffman;

/**
 * Created by jo930_000 on 2016-11-16.
 */
public class Tree {

    char alphabet;
    Tree lchild;
    Tree rchild;
    boolean isLeaf;

    public Tree() {
        this.alphabet = '\0';
        this.lchild = null;
        this.rchild = null;
        this.isLeaf = false;
    }

}
