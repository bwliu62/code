package BFS;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix {
    static class Cell{
        int row;
        int column;
        int value;

        Cell(int row, int column, int value){
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int colums = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.value == c2.value) {
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });

        boolean[][] visited = new boolean[rows][colums];
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;

        for (int i = 0; i < k -1 ; i++){
            Cell cur = minHeap.poll();

            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.column]){
                minHeap.offer(new Cell(cur.row + 1, cur.column, matrix[cur.row + 1][cur.column]));
                visited[cur.row + 1][cur.column] = true;
            }
            if (cur.column + 1 < colums && !visited[cur.row][cur.column + 1]){
                minHeap.offer(new Cell(cur.row, cur.column + 1, matrix[cur.row][cur.column + 1]));
                visited[cur.row][cur.column + 1] = true;
            }
        }
        return minHeap.peek().value;

    }

    public static void main(String args[]){
        int[][] matrix ={{1,2,3,4},{11,12,13,14},{15,16,17,18},{19,20,21,22}};
        int k = 7;
        KthSmallestInSortedMatrix s = new KthSmallestInSortedMatrix();
        int res = s.kthSmallest(matrix,k);
        System.out.println(res);
    }
}
