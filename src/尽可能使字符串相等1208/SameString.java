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
//    public int equalSubstring2(String s, String t, int maxCost) {
//
//    }
}