package 最长回文子串05;

public class longestReturnString {
    public static void main(String[] args) {

    }
}

//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
/*
* 输入: "babad"
  输出: "bab"
  注意: "aba" 也是一个有效答案。
* */

//动态规划

//动态规划
/*
* 也就是说，只有 s[i+1:j−1] 是回文串，并且 ss 的第 i 和 j 个字母相同时，s[i:j] 才会是回文串。

* */
//讨论是建立在子串长度大于 2 的前提之上的，我们还需要考虑动态规划中的边界条件，
// 即子串的长度为 1 或 2。对于长度为 1 的子串，它显然是个回文串；对于长度为 2 的子串，只要它的两个字母相同，它就是一个回文

class Solution {
    public String longestPalindrome(String s) {

        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        String ans = "";

        //这里的l代表的是长度，看作要对比的字串长度
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                //可以理解为由第一个坐标加上l长度确认第二个要对比的字符的坐标
                int j = i + l;
                //第一种边界条件是只有一个元素的时候，必定是回文串
                if (l == 0) {
                    dp[i][j] = true;
                }
                //第二种边界条件是，加上1，对比相邻两个字符是否一样的情况
                else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                }
                //第三种就是用来对比长度为2以上的字符串
                else {
                    //判断上一种情况是否为回文
                    //比如aba，b是回文，abba，bb是回文
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    //注意，substring取的实际值为i到i+l
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;


    }
}


