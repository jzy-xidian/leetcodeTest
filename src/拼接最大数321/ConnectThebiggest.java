package 拼接最大数321;

public class ConnectThebiggest {
}
class Solution {

    //个人思路，肯要开辟新的空间，对数据进行排序，但是要按照数组的顺序来统计
    //还有个要注意的关键点，就算要统计长度

    //采用单调栈，找到长度为K的最大数，从两个数组中选出最大子序列。两个子序列长度之和为k
    //将两个子序列合并得到最大数，两个子序列长度最小为0，最大不能超过给K，且不能超过对应数组长度

    //第一步可以通过单调栈实现，单调栈满足从占地到栈顶元素递减，从左到右遍历数组，遍历过程中维护单调栈内的元素
    //需要博正白能力结束之后单调栈内的元素个数等于指定的最大子序列的长度，遍历结束后，从栈底到栈顶的元素依次拼接

    //第二步需要自定义比较方法，比较两个子序列的当前元素，如果当前元素不同，选择其中比较大的元素作为下一个合并元素，否则需要比较后面所有元素才能决定选哪个作为合并元素

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];

        //需要遍历所有可能的 xx 和 yy 的值，对于每一组 xx 和 yy 的值，得到最大数。在整个过程中维护可以通过拼接得到的最大数。

        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;



    }


    //提取字串的情况
    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    //对两个字串进行合并
    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }


    //自定义比较元素
    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2){
        int x= subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y){
            //对比两个队列中的元素大小
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0){
                return difference;
            }

            index1++;
            index2++;
        }

        return (x - index1) - (y - index2);
    }
}