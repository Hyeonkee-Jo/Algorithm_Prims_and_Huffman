package prims;

import java.util.Scanner;

/**
 * Created by jo930_000 on 2016-11-15.
 */
public class PrimTest {
    public static void main(String[] args) {
        Prim test = new Prim();
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of nodes : ");
        int node_number = scan.nextInt();    // 그래프를 형성할 행,열 입력(행과 열의 수는 일치) -> 예제에서처럼 9 입력

        System.out.println("\nEnter the cost matrix : ");  // 각 행을 스트링 형식으로 입력 ex) 1 2 3 4 5
        /* 0 4 0 0 0 0 0 8 0                            -> 이 행렬을 바로 입력
           4 0 8 0 0 0 0 11 0
           0 8 0 7 0 4 0 0 2
           0 0 7 0 9 14 0 0 0
           0 0 0 9 0 10 0 0 0
           0 0 4 14 10 0 0 0 0
           0 0 0 0 0 2 0 1 6
           8 11 0 0 0 0 1 0 7
           0 0 2 0 0 0 6 7 0
          */
        test.make_weight_matrix(node_number);

        System.out.print("Enter the source matrix : ");  // 시작 vertex가 될 source를 입력 1 : a, 2 : b ..... -> 1 입력
        int source_number = scan.nextInt();
        test.make_mst(source_number);
    }
}
