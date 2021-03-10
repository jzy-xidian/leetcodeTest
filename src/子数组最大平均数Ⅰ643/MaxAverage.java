package 子数组最大平均数Ⅰ643;

import java.util.List;

public class MaxAverage {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = new int[]{1,12,-5,-6,50,3};
        System.out.println(solution.findMaxAverage(arr,4));
    }
}
class Solution {
    public double findMaxAverage(int[] nums, int k) {

        if (nums.length == 1){
            return (double)nums[0];
        }

        int n = nums.length;
        double avg = 0;

        int sum = 0;

        double max = 0;

        for (int i = 0; i <= n - k; i++){
            for (int j = i ; j < i+k; j++){
                sum += nums[j];
            }

            avg = (double)sum / k;

            max = Math.max(avg,max);

            sum = 0;
        }

        return max;

    }

    //又是败在了大数情况
    //滑动窗口月，注意使用滑动窗口
    //如果直接计算每个子数组的元素和，则时间复杂度过高，无法通过全部测试用例，因此需要使用时间复杂度更低的方法计算每个子数组的元素和。
    //可以看成维护一个长度为 k 的滑动窗口。当滑动窗口从下标范围 [i−k,i−1] 移动到下标范围 [i−k+1,i] 时，nums[i−k] 从窗口中移出，nums[i] 进入到窗口内。
    //

    public double findMaxAverage2(int[] nums, int k){
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;


    }

}