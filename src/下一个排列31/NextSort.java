package 下一个排列31;

public class NextSort {
}

class Solution {

    //自己的思路：第一种情况就是如果长度为0或者1，直接返回，如果完全递减，直接反转数组
    //感觉有点类似山峰问题，不管前面几位怎么变，只要最后是上升趋势，交换最后两位几个
    //如果是含有山尖的那种，交换比山峰第一个小的左边的数和右边的最小的数就是重拍结果
    //如果是类似123897这种，必定交换的是峰值
    public void nextPermutation(int[] nums) {

        int length = nums.length;

        //用来记录右边的最小值
        int right = 0;
        //记录峰值
        int peek = 0;
        //记录左边的次大值
        int left = 0;

        //用来判断是否达到最大值的计数器
        int count = 1;

        //先判断是否达到了最大排列组合
        for (int i = 0; i < length-1; i++){
            if (nums[i] > nums[i+1]){
                count++;

            }
            else{
                break;
            }

        }

        //达到最大排列，数组反转
        if (count == length){

            for (int i = 0,j = length - 1; ; i++,j--){
               if (i >= j){
                   break;
               }
               else {
                   int temp = nums[i];
                   nums[i] = nums[j];
                   nums[j] = temp;
               }
            }

        }
        //否则采用山峰的那种方式
        else {

            //最后一位是判定关键
            right = nums[length - 1];

            for (int i = length - 2; i >0; i--){

                //第一种情况就是如果最后两个值直接递增，直接交换最后两位，然后结束
                if (i == length - 2){
                    if( nums[i] < right){
                        int temp = nums[i];
                        nums[i] = right;
                        nums[length-1] = temp;
                        break;
                    }
                    else {
                        continue;
                    }
                }
                //用右边的最小值去换左边递减的右边最小值第一个小的数
                else if (nums[i] > nums[i+1]){

                }

            }

        }

    }

    //思路大体正确，但是还是没想出来，卡在核心算法
    //还有个很重要的点就是交换过后，山峰往右的部分也要进行最小的排列组合

    public void nextPermutation2(int[] nums) {
        int i = nums.length - 2;
        //找山峰的峰顶
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            //要开始从右边的峰，找到第一个比nums[i]大的数才能交换
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        //最后一步就是进行数组反转
        reverse(nums, i + 1);
    }

    //封装的交换函数
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        //在左小于右的情况下才可以交换
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}