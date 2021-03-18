package 不同的子序列115;

public class DiffDIstinct {
}
class Solution {

    //我觉得可以采用回溯，每个值只要和目标串一样的时候，进行统计，否则继续向下找
    //不过题解好像采用的是动态规划
    //假设字符串 s 和 t 的长度分别为 m 和 n。如果 t 是 s 的子序列，则 s 的长度一定大于或等于 t 的长度，
    // 即只有当 m≥n 时，t 才可能是 s 的子序列。如果 m<n，则 t 一定不是 s 的子序列，因此直接返回 0。
    //当 m≥n 时，可以通过动态规划的方法计算在 s 的子序列中 t 出现的个数。
    //
    //创建二维数组 dp，其中 dp[i][j] 表示在 s[i:] 的子序列中 t[j:] 出现的个数。
    //考虑动态规划的边界情况

    //


    public int numDistinct(String s, String t) {

        int m = s.length(), n = t.length();
        if (m < n){
            return 0;
        }

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++){
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--){
            char sChar = s.charAt(i);

            for (int j = n - 1; j >= 0; j--){
                char tChar = t.charAt(j);
                if (sChar == tChar){
                    dp[i][j] = dp[i+1][j+1] + dp[i+1][j];
                }else {
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        return dp[0][0];



    }
}