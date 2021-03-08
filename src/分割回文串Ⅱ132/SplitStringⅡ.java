package 分割回文串Ⅱ132;

import java.util.Arrays;

public class SplitStringⅡ {
}
class Solution {

    //在昨天题目要求的基础上，要找到最小的分割次数
    //感觉还是需要状态转移方程，f[i]表示字符串前缀s[0,i]的最小分割次数，考虑枚举出每个s[0,i]分割出的子厚一个回文串
    //即我们枚举最后一个回文串的起始位置 j+1，保证 s[j+1..i] 是一个回文串，那么 f[i] 就可以从 f[j] 转移而来，附加 1 次额外的分割次数。

    //注意到上面的状态转移方程中，我们还少考虑了一种情况，即 s[0..i] 本身就是一个回文串。此时其不需要进行任何分割，也就是f[i]=0

    //那么我们如何知道 s[j+1..i] 或者 s[0..i] 是否为回文串呢，
    // 我们可以使用与「131. 分割回文串的官方题解」中相同的预处理方法，将字符串 s 的每个子串是否为回文串预先计算出来

   // 过动态规划计算出所有的 f 值之后，最终的答案即为 f[n-1]，其中 n 是字符串 s 的长度。


    public int minCut(String s) {

        //先列举二维数组，计算是否为回文串

        int n = s.length();

        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }


        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }


        //创造状态转移方程
        int[] f = new int[n];

        //先假定每个位置都是最大分割次数
        Arrays.fill(f,Integer.MAX_VALUE);

        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }



        return f[n-1];

    }
}