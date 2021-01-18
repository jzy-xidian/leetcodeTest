package 不同路径62;

public class DifferentPath {
}
class Solution {
    //个人思路：其实这个有点走迷宫问题，判断右边和下边就行
    public int uniquePaths(int m, int n) {

        //需要加一圈边界，边界都为1，内部矩阵为0
        int[][] arr = new int[m+2][n+2];

        for (int i = 0; i < m+2; i++){
            for (int j = 0; j < n + 2;j++){
                if (i == 0 || i == m + 1 || j == 0 || j == n+1){
                    arr[i][j] = 1;
                }
            }
        }

        int pathSize = 0;
        //初始化结束后，开始计算路径，我觉得要用递归
        return  search(arr,1,1,pathSize);



    }

    public int  search(int[][] arr,int heng,int shu, int pathSize){

        if (heng == arr.length - 2 && shu == arr[0].length - 2){
            pathSize++;

            return pathSize;
        }

        if (shu + 1 != arr[0].length - 1){
            pathSize = search(arr,heng,++shu,pathSize);
        }else {
            pathSize = search(arr,++heng,shu,pathSize);
        }

        if (heng + 1 != arr.length - 1){
            pathSize = search(arr,++heng,shu,pathSize);
        }else {
            pathSize = search(arr,heng,++shu,pathSize);
        }

        return pathSize;
    }

    public int uniquePaths2(int m, int n){
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                //每次到该点的路径只有上边和左边
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];

    }
}