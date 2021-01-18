package 在排序数组中查找元素的第一个和最后一个位置34;

public class SearchFirstAndLast {
}

class Solution {

    //个人思路：一个从前往后，一个从后往前
    public int[] searchRange(int[] nums, int target) {


        int[] result = new int[2];

        if (nums.length == 0){
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        int index = 0;

        while (index < nums.length){

            if (nums[index] == target){

                result[0] = index;
                break;

            }

            index++;

        }

        if (index == nums.length){
            result[0] = -1;
        }

        int tail = nums.length - 1;

        while (tail>=0){
            if (nums[tail] == target){
                result[1] = tail;
                break;
            }
            tail--;
        }

        if (tail < 0){
            result[1] = -1;
        }

        return result;

    }

    //这个题根据要求，明显就是二分查找
    public int[] searchRange2(int[] nums, int target){
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }


    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}