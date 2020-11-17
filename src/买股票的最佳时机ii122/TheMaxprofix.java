package 买股票的最佳时机ii122;

public class TheMaxprofix {
}

class Solution {

    //自己的思路：因为输入数组是股票动态变化的，如果比前一天卖出的价格低就买入，然后只要后一天比前一天价格低就卖出
    //如果持续走高或者持续走低，就不要卖，这个牵扯到明天的价格变化
    public int maxProfit(int[] prices) {

        //先对比前两个数，出现第二个比第一种小的情况下才买入，如果直接是最后一个数就返回0
        //因为买入必定会有卖出，所以必须是成对出现

        int buy = 0;
        int sell = 0;
        int sellNext = 0;

        if (prices.length < 2){
            return 0;
        }

        if (prices.length == 2){
            if (prices[0] < prices[1]){
                return prices[1] - prices[0];
            }
            else {
                return 0;
            }
        }

        //统计利润的变量
        int profit = 0;

        for (int  i = 0; i < prices.length - 2; i++){

            for (int j = i + 1; j < prices.length-1; j++){
                buy = prices[i];
                sell = prices[j];
                sellNext = prices[j+1];
                if (buy >= sell){
                    break;
                }

                if (buy < sell && sell > sellNext){

                    profit  += (sell - buy);
                    i = j;
                    break;

                }else if (buy < sell && sell <= sellNext){

                    if (j+1 == prices.length - 1){
                        profit += sellNext - buy;

                        return profit;
                    }

                    continue;
                }



            }

        }

        return profit;

    }


    //看下别人的解法把，整理一下

    //第一种就是动态规划
    //不能同时参与多笔交易
    public int maxProfit2(int[] prices){
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // 0：持有现金
        // 1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++){

            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];


    }

    //另一种写法：
    public int maxProfit5(int[] prices) {
        int n = prices.length;
        int dp0 = 0, dp1 = -prices[0];
        for (int i = 1; i < n; ++i) {
            int newDp0 = Math.max(dp0, dp1 + prices[i]);
            int newDp1 = Math.max(dp1, dp0 - prices[i]);
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0;

    }



    //第二种是贪心算法，更好理解一些
    //要考虑到一个点就是如果达到利润的峰值，然后卖出的话，下次计算应该从卖出的后一天买入，然后后统计看是否有利可图
    public int maxProfit3(int[] prices){
        int ans = 0;
        int n = prices.length;
        //这个算法最巧妙的点就是，卖出那天的一定是某一区间的峰值，然后进行递增区间的累加，递减区间的不会被统计，是按0计算的
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;

    }






}