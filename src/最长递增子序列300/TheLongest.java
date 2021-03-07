package 最长递增子序列300;

public class TheLongest {
}
class Solution {

    //这种会出现多种情况的题目，肯定是要考虑动态规划与状态转移的
    //d[i]考虑前i个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。

    //我们从小到大计算 dp 数组的值，在计算 dp[i] 之前，我们已经计算出 dp[0…i−1] 的值，则状态转移方程为：
    //即考虑往 dp[0…i−1] 中最长的上升子序列后面再加一个 [i]nums[i]

    //由于 dp[j] 代表nums[0…j] 中以 nums[j] 结尾的最长上升子序列，所以如果能从 dp[j] 这个状态转移过来，
    // 那么 nums[i] 必然要大于 nums[j]，才能将 nums[i] 放在 nums[j] 后面以形成更长的上升子序列。
    //
    //最后，整个数组的最长上升子序列即所有 dp[i] 中的最大值。
    //

    public int lengthOfLIS(int[] nums) {

        if(nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;

        for (int i = 1; i < nums.length; i++){

            //对比开始前只有自己一个
            dp[i] = 1;
            //开始和前面的转移方程进行对比，如果比自己小，就+1
            for (int j = 0; j < i; j++){

                if (nums[i] > nums[j]){

                    dp[i] = Math.max(dp[i],dp[j] + 1);

                }

            }

            //比较完一轮后，取和上一轮的最大值进行对比
            maxans = Math.max(maxans,dp[i]);

        }

        return maxans;

    }
}