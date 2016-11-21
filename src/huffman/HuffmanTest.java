package huffman;

import java.io.IOException;

/**
 * Created by jo930_000 on 2016-11-16.
 */
public class HuffmanTest {
    public static void main(String[] args) throws IOException {
        Huffman test = new Huffman();

        test.file_read();
        test.encode();
        test.decode();
    }
}
