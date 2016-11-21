package prims;

import java.util.Scanner;

/**
 * Created by jo930_000 on 2016-11-14.
 */
public class Prim {
    int[][] weight;
    MinPQ priorityQueue = new MinPQ();
    int total_weight = 0;

    public void make_weight_matrix(int num) {
        weight = new int[num][num];
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < num; i++) {
            String input = scan.nextLine();
            String[] arr;
            arr = input.split(" ");
            for(int j = 0; j < num; j++) {
                weight[i][j] = Integer.parseInt(arr[j]);
                if(i != j && weight[i][j] == 0) weight[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public void make_mst(int vertex) {
        for(int i = 0; i < weight.length; i++) {
            if(i != vertex-1) priorityQueue.insert(new Vertex(i, Integer.MAX_VALUE, -1));
            else priorityQueue.insert(new Vertex(i, 0, -1));
        }

        while(!priorityQueue.isEmpty()) {
            Vertex u = new Vertex(priorityQueue.min().src, priorityQueue.min().weight, priorityQueue.min().parent);
            priorityQueue.extract_min();
            if(u.parent == -1)  System.out.println("w( " +","+ Character.toString((char)(u.src+97)) +") = " + u.weight);
            else System.out.println("w(" + Character.toString((char)(u.parent+97))+ "," + Character.toString((char)(u.src+97)) +") = " + u.weight);
            total_weight += u.weight;

            for(int i = 1; i <= weight.length; i++) {
                if(weight[u.src][i-1] != Integer.MAX_VALUE && weight[u.src][i-1] != 0) {
                    if(priorityQueue.contains(i-1) && weight[u.src][i-1] < priorityQueue.heap[priorityQueue.search(i-1)].weight) {
                        priorityQueue.heap[priorityQueue.search(i-1)].weight = weight[u.src][i-1];
                        priorityQueue.heap[priorityQueue.search(i-1)].parent = u.src;
                    }
                }
            }
            priorityQueue.build_min_heap();
        }

        System.out.println("\nw(MST) = " + total_weight);
    }
}
