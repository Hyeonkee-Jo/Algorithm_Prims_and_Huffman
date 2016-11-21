package huffman;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by jo930_000 on 2016-11-16.
 */
public class Huffman {

    int[] freqlist = new int[27];
    StringBuffer[] alphabetTable;
    Queue<trecord> queue = new Queue<>();
    int lineNumber = 0;
    int size = 0;
    String data;
    trecord peektree;

    public void file_read() {
        try{
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\data10.txt"));
            String line = in.readLine();
            while(line != null){
                ++lineNumber;
                StringTokenizer parser = new StringTokenizer(line, ",:;-.?!\"");
                while(parser.hasMoreTokens()){
                    int j;
                    String word = parser.nextToken();
                    for(int i=0; i<word.length(); i++) {
                        if(word.charAt(i) == 32) {   // ASCII code로 공백은 32
                            freqlist[0]++;
                        } else {
                            j = word.charAt(i) - 96;
                            freqlist[j]++;
                        }
                    }
                }
                line = in.readLine();
            }
            in.close();
        }catch(IOException e){System.out.println(e);}

        if(freqlist[0] != 0) {
            queue.enqueue(new trecord((char) (32), freqlist[0]), freqlist[0]);
            size++;
        }
        for(int i = 1; i<27; i++) {
            if(freqlist[i] != 0) {
                queue.enqueue(new trecord((char)(i+96), freqlist[i]), freqlist[i]);
                size++;
            }
        }

        // 과제에서의 수도코드
        for(int i=0; i<size-1; i++) {
            trecord tnode = new trecord();
            tnode.lchild = queue.remove();
            tnode.rchild = queue.remove();
            tnode.freq = tnode.lchild.freq + tnode.rchild.freq;
            queue.enqueue(tnode, tnode.freq);
        }

        peektree = queue.first();
        peektree.Order();

        alphabetTable = peektree.alphatable;
    }

    public void encode() throws IOException{
        lineNumber = 0;

        try{
            FileOutputStream output_encode = new FileOutputStream("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\hw08_00_201202143_encoded.txt");
            FileOutputStream output_table = new FileOutputStream("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\hw08_00_201202143_table.txt");
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\data10.txt"));
            String line = in.readLine();

            while(line != null){
                StringBuffer encode = new StringBuffer();

                ++lineNumber;
                StringTokenizer parser = new StringTokenizer(line, ",:;-.?!\"");
                while(parser.hasMoreTokens()){
                    String word = parser.nextToken();

                    for(int i=0; i<word.length(); i++) {
                        if(word.charAt(i)==32) encode.append(alphabetTable[0]);
                        else encode.append(alphabetTable[(int)(word.charAt(i)-96)]);

                    }
                }
                data = encode.toString();
                output_encode.write(data.getBytes());
                data = null;
                int i;
                if(alphabetTable[0] != null){
                    data = (char)32 + ","+ alphabetTable[0].toString()+"\n";
                    output_table.write(data.getBytes());
                }
                for(i =1; i < 27; i++) {
                    data = (char)(i+96) + "," + alphabetTable[i].toString() +"\n";
                    output_table.write(data.getBytes());
                    if(alphabetTable[i+2] == null) {
                        i++;
                        break;
                    }
                }
                data = (char)(i+96) + "," + alphabetTable[i].toString();
                output_table.write(data.getBytes());

                output_encode.close();
                output_table.close();
                line = in.readLine();
            }
            in.close();
        } catch(IOException e){System.out.println(e);}
    }

    public void decode() throws IOException {
        Tree tree = new Tree();
        lineNumber = 0;
        FileOutputStream output_decode = new FileOutputStream("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\hw08_00_201202143_decoded.txt");
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\data10_table.txt"));
        String line = in.readLine();

        while (line != null) {
            ++lineNumber;
            StringTokenizer parser = new StringTokenizer(line, ",:;-.?!\"");
            while (parser.hasMoreTokens()) {
                char alpha = parser.nextToken().charAt(0);
                String word = parser.nextToken();
                Tree temp = tree;

                for(int i=0; i<word.length(); i++) {
                    if(temp.lchild == null) temp.lchild = new Tree();
                    if(temp.rchild == null) temp.rchild = new Tree();

                    if(word.charAt(i) == 48) {
                        if(i == word.length() - 1) {
                            temp.lchild.isLeaf = true;
                            temp.lchild.alphabet = alpha;
                        }
                        temp = temp.lchild;
                    }
                    else if(word.charAt(i) == 49) {
                        if(i == word.length() - 1) {
                            temp.rchild.isLeaf = true;
                            temp.rchild.alphabet = alpha;
                        }
                        temp = temp.rchild;
                    }
                }
            }
            line = in.readLine();
        }
        in.close();

        String data = null;
        lineNumber = 0;
        BufferedReader in_1 = new BufferedReader(new FileReader("C:\\Users\\jo930_000\\IdeaProjects\\Algorithm_hw08\\src\\huffman\\data10_encoded.txt"));
        String line_1 = in_1.readLine();

        while (line_1 != null) {
            StringBuffer decode = new StringBuffer();
            ++lineNumber;
            StringTokenizer parser = new StringTokenizer(line_1, ",:;-.?!\"");
            Tree temp = tree;
            while (parser.hasMoreTokens()) {
                String word = parser.nextToken();
                for(int i=0; i<word.length(); i++) {
                    if(word.charAt(i) == 48) {
                        temp = temp.lchild;
                        if(temp.isLeaf == true) {
                            decode.append(temp.alphabet);
                            temp = tree;
                        }
                    }
                    else if(word.charAt(i) == 49) {
                        temp = temp.rchild;
                        if(temp.isLeaf == true) {
                            decode.append(temp.alphabet);
                            temp = tree;
                        }
                    }
                }
            }
            data = decode.toString();
            line_1 = in_1.readLine();
        }
        output_decode.write(data.getBytes());
        output_decode.close();
        in_1.close();
    }
}
