package 最接近的三数之和16;

import java.util.Arrays;

public class ClosestThreeNum {
    public static void main(String[] args) {
        int[] nums = {0,2,1,-3};
        int target = 1;
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(nums,target));
    }
}

class Solution {

    //自己的思路：数组长度未知，目标数值唯一，所以关键是从n个数中筛选三个距离目标最近的
    //如果采用排列组合，效率过低n^3的复杂度
    //按照三数之和的算法，没法取到等于
    //所以只能按照差值进行比较
    public int threeSumClosest(int[] nums, int target) {

        if (nums.length == 3){
            return nums[0] + nums[1] + nums[2];
        }

        int length = nums.length;
        Arrays.sort(nums);


        //设定双指针，一个在头，一个在尾，比较出最小的值
        int sumResult = 0;

        //保存与目标数值的差值
        int min = Math.abs(target - (nums[0] + nums[1] + nums[length-1]));

        int finalResult = nums[0] + nums[1] + nums[length-1];

        for (int i = 0; i < length; i++){
            int L = i+1;
            int R = length -1;

            while (L < R){
                sumResult = nums[i] + nums[L] + nums[R];

                if (sumResult > target){
                    if (min > sumResult - target){
                        finalResult = sumResult;
                    }
                    R--;
                }else if (sumResult < target){
                    if (min > target - sumResult){
                        finalResult = sumResult;
                    }
                    L++;
                }else {
                    finalResult = sumResult;
                    break;
                }

            }
        }

        return finalResult;


    }

    //最后还是没有解出来，还是边界数据的问题，思路大体方向正确，看下解法
    //还是采用双指针和排序的方式
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        for (int i = 0; i < n; i++){
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;

            while (j < k){
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }

                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }

                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                }
                else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }

            }
        }

        return best;
    }
}