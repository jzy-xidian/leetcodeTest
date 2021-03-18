package 螺旋矩阵54;

import java.util.ArrayList;
import java.util.List;

public class SpiralJuzhen {
}
class Solution {

    //找到索引变化规律，然后存入list
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> order = new ArrayList<>();

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return order;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        boolean[][] visited = new boolean[rows][columns];

        int total = rows * columns;

        int row = 0;
        int column = 0;

        //方向数组
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;

        //为了访问到所有的点，所以必须是total次访问
        for (int i = 0; i < total; i++){

            order.add(matrix[row][column]);
            visited[row][column] = true;

            //要判断下一个方向是否合理
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]){
                directionIndex = (directionIndex + 1) % 4;
            }

            row += directions[directionIndex][0];
            column += directions[directionIndex][1];



        }


        return order;

    }
}