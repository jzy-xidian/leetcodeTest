package 尽可能使字符串相等1208;

public class SameString {
}
class Solution {

    //第一种是前缀和+二分查找
    //创建一个长度为 n 的数组 diff，计算两个字符串该位置的开销
    //当 diff 的子数组以下标 j 结尾时，需要找到最小的下标k（k≤j），使得diff 从下标 k 到 j 的元素和不超过 maxCost，此时子数组的长度是 j−k+1
    //

    public int equalSubstring(String s, String t, int maxCost) {

        int n = s.length();
        int[] accDiff = new int[n + 1];
        for (int i = 0; i < n; i++) {

            //计算变更的开销，状态统计
            accDiff[i + 1] = accDiff[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        for (int i = 1; i <= n; i++) {
            //返回满足条件的起始坐标
            //找到最小的起始坐标
            int start = binarySearch(accDiff, i, accDiff[i] - maxCost);
            maxLength = Math.max(maxLength, i - start);
        }
        return maxLength;



    }

    public int binarySearch(int[] accDiff, int endIndex, int target) {
        int low = 0, high = endIndex;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (accDiff[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


//    //第二种是滑动窗口

  //  滑动窗口的思想是，维护两个指针start 和end 表示数组diff 的子数组的开始下标和结束下标，
    //  满足子数组的元素和不超过 maxCost，子数组的长度是 end−start+1。初始时，start 和end 的值都是 0

    //另外还要维护子数组的元素和 sum，初始值为 0。在移动两个指针的过程中，更新 sum 的值，判断子数组的元素和是否大于 maxCost，并决定应该如何移动指针
    //当start 的值固定时，end 的值应尽可能大；
    //
    //当 end 的值固定时，start 的值应尽可能小。
    //


    public int equalSubstring2(String s, String t, int maxCost) {

        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {

            //计算每个字符对应位置的消耗
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += diff[end];
            while (sum > maxCost) {
                sum -= diff[start];
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }
        return maxLength;



    }
}