package huffman;

/**
 * Created by jo930_000 on 2016-11-16.
 */
public class trecord {
    char alphabet;
    int freq;
    trecord lchild;
    trecord rchild;
    StringBuffer code = new StringBuffer();
    StringBuffer[] alphatable = new StringBuffer[27];

    trecord() {
        this.alphabet = 0;
        this.freq = 0;
        this.lchild = null;
        this.rchild = null;
    }

    trecord(char alphabet, int freq, trecord lchild, trecord rchild) {
        this.alphabet = alphabet;
        this.freq = freq;
        this.lchild = lchild;
        this.rchild = rchild;
    }

    trecord(char alphabet, int freq) {
        this.alphabet = alphabet;
        this.freq = freq;
        this.lchild = null;
        this.rchild = null;
    }

    public void Order() {
        Order(this);
    }

    private StringBuffer[] Order(trecord x) {
        if( x!=null) {
            if(x.lchild != null) {
                x.lchild.code.append(x.code +"0");
            }
            if(x.rchild != null) {
                x.rchild.code.append(x.code +"1");
            }

            if(x.lchild == null && x.rchild == null) {
                if(x.alphabet == 32) alphatable[0] = x.code;
                else alphatable[(int)(x.alphabet)-96] = x.code;
            }

            Order(x.lchild);
            Order(x.rchild);
        }

        return alphatable;
    }
}
