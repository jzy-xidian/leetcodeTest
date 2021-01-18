package 最大矩阵85;

public class BiggestJuzhen {
}
class Solution {

    //个人思路：第一种就是柱状图的优化暴力方法
    public int maximalRectangle(char[][] matrix) {

        //这个题的思想按照第一种就是按行找最大，按列继续找持续的1，在持续的列中，找到最小的行

        //二维矩阵的长
        int m = matrix.length;
        if (m == 0){
            return 0;
        }

        //矩阵的宽
        int n = matrix[0].length;

        //复刻原本的二维矩阵，统计连续1的个数
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1'){
                    left[i][j] = (j == 0 ? 0 : left[i][j-1]) + 1;
                }
            }
        }

        int ret = 0;

        //这次是考虑纵列的情况
        for (int i = 0; i < m; i++){
            for (int j = 0;j < n; j++){

                //按行开始，先找到第一个不是1的元素,然后开始向下统计
                if (matrix[i][j] == '0'){
                    continue;
                }

                //找到一个1的时候,先统计他横向已经有的1的个数
                int width = left[i][j];
                //没有向下找的时候，面积就是width，因为宽是1
                //保存该点的横向变量
                int area = width;

                //开始向下找
                for (int k = i - 1; k >= 0; k--){
                    //自底向上寻找
                    //宽，也就是横向累计的1是会变的
                    width = Math.min(width,left[k][j]);
                    //开始找最大值
                    area = Math.max(area, (i-k+1)*width);
                }

                //最后一步就是遍历完每个点后，找到最大的面挤
                ret = Math.max(ret,area);


            }
        }


        return ret;

    }
}