package 使用最小的花费爬楼梯746;

public class LessCostUpstore {
}

//先看看大佬的解法，再看看动态规划
class Solution {
    public int minCostClimbingStairs(int[] cost) {

        int len = cost.length;
        for (int i = 2; i < len; i++){
            cost[i] += Math.min(cost[i-1],cost[i-2]);
        }
        return Math.min(cost[len-1],cost[len - 2]);
    }

    //但是这个题实际考察的是动态规划
    public int minCostClimbingStairs2(int[] cost) {

//       int n = cost.length;
//       int[] dp = new int[n+1];
//       dp[0] = dp[1] = 0;
//       for (int i = 2; i <= ; i++){
//           dp[i] = Math.min(dp[i - 1] + cost[i - 1],dp[i - 2] + cost[i-2]);
//       }
//
//       return dp[n];

        //优化版
        int n = cost.length;
        int pre = 0, curr = 0;
        for (int i = 2; i <= n; i++){
            int next = Math.min(curr + cost[i-1],pre + cost[i-2]);
            pre = curr;
            curr = next;
        }

        return curr;
    }
}