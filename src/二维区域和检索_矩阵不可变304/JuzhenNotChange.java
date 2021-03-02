package 二维区域和检索_矩阵不可变304;

public class JuzhenNotChange {
}
class NumMatrix {

    //直接累加和不现实，应该有别的技巧，参考昨天的方法
    //这个不太好用初始化然后相减的策略

    //先试下简单的暴力求解

    int[][] matrix;

    public NumMatrix(int[][] matrix) {

        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum = 0;

        for (int i = row1; i <= row2; i++){
            for (int j = col1; j <= col2; j++){
                sum += matrix[i][j];
            }
        }

        return sum;
    }



}

//官方有一维前缀和与二维前缀和两种方式
class NumMatrix2 {

    //一维的方法就是把矩阵每一行计算前缀和，然后检索的时候对二维区域中的每一行计算子数组和，最后计算每一行子数组计算总和
    //注意，前缀和的实现思想是sum（i）加的是前i-1个数的和，再加上自己变成sum（i+1）才能计算到自己

    int[][] sums;

    public NumMatrix2(int[][] matrix) {

        //行
        int m = matrix.length;

        if (m > 0){

            int n = matrix[0].length;
            //列是n+1，为了能够计算出最后一列的总和，以及不需要对第一轮的情况做特殊处理
            sums = new int[m][n+1];

            for (int i = 0; i < m; i++){
                for (int j = 0; j < n; j++){
                    sums[i][j+1] = sums[i][j] + matrix[i][j];
                }
            }
        }



    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        int sum = 0;
        for (int i = row1; i <= row2; i++){
            sum += sums[i][col2+1] - sums[i][col1];
        }

        return sum;
    }



}