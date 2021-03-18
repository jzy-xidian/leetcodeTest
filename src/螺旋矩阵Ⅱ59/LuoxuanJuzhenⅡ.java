package 螺旋矩阵Ⅱ59;

public class LuoxuanJuzhenⅡ {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateMatrix(3));
    }
}
class Solution {
    public int[][] generateMatrix(int n) {

        int[][] arr = new int[n][n];

        boolean[][] visited = new boolean[n][n];


        //方向矩阵
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int directionIndex = 0;



        int row = 0;
        int column = 0;


        //为了访问所有的点，必须是n*n个点
        for (int i = 1; i <= n * n; i++){



            arr[row][column] = i;

            visited[row][column] = true;

            int nextRow = row + direction[directionIndex][0], nextColumn = column + direction[directionIndex][1];

            if (nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || visited[nextRow][nextColumn] == true){

                directionIndex = (directionIndex + 1) % 4;

            }


            row += direction[directionIndex][0];
            column += direction[directionIndex][1];



        }

        return arr;

    }
}