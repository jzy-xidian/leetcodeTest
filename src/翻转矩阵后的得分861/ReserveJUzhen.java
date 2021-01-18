package 翻转矩阵后的得分861;

public class ReserveJUzhen {
}
class Solution {

    //移动是指选择任一行或列，并转换该行或列中的每一个值：
    // 将所有 0 都更改为 1，将所有 1 都更改为 0。

    //为了得到最高分数，每一行的最左边必须是1，所以为了做到这一点，可以翻转那些最左边不为1的行，其他保持不变
    //计算每一列堆总分数的贡献，从而直接计算最高分数
    //假设矩阵共有 mm 行 nn 列，计算方法如下
    //对于最左边的列而言，最优情况下，取值都为1，贡献为2^n-1 * m，这是左边的总贡献

    public int matrixScore(int[][] A) {

        int m = A.length, n = A[0].length;

        //左边为1时的总贡献，进行移位运算
        int ret = m * (1 << (n-1));

        for (int j = 1; j < n; j++){
            int nOnes = 0;
            for (int i = 0; i < m; i++){
                if (A[i][0] == 1){
                    nOnes += A[i][j];
                }else {
                    //// 如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                    nOnes += (1 - A[i][j]);
                }
            }

            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }

        return ret;

    }

    //感觉别人的解法更好一点，比较好理解：
//    分三步走
//
//    第一步：将首列全部置位1，保证最高位全部取到，首列不为1的行全部翻转
//
//    第二步：从第二列开始，将所有列中1的数量小于0的数量的行翻转，保证取1的数量尽可能多
//
//    第三步：计算结果返回
    public int matrixScore2(int[][] A){
        //step1:翻转所有行，保证第一列全为1
        int m = A.length, n = A[0].length;

        for (int i = 0; i < m; i++){

            if (A[i][0] != 1){

                //翻转该行
                for (int j = 0; j < n; j++){

                    A[i][j] = 1 - A[i][j];

                }
            }
        }

        //step2:从第二列开始，检查该列的1的数量是否大于等于0，如果不是，则翻转该列
        for (int j = 1; j < n; j++){
            int count = 0;
            for (int i = 0; i < m; i++){
                if (A[i][j] == 1){
                    count++;
                }
            }
            if (count < (m + 1) / 2){
                for (int i = 0; i < m; i++){
                    A[i][j] = 1 - A[i][j];
                }
            }

        }

        //step3:计算结果并返回
        int num = 0;
        for (int j = 0; j < n; j++){
            int temp = (int) Math.pow(2,n-j-1);
            for (int i=0;i<m;i++){
                num += A[i][j] * temp;
            }
        }

        return num;
    }

}