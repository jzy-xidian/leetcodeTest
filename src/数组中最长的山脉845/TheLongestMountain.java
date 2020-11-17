package 数组中最长的山脉845;

public class TheLongestMountain {
}


class Solution {
    //自己的思路：定义变量，计算先增长后减少的情况的长度，取最长的值，当再次增长的时候，断掉循环，从新的坐标开始
    public int longestMountain(int[] A) {

        if (A.length < 3){
            return 0;
        }

        int max = 0;

        int count = 0;
        for (int i = 0; i< A.length; i++){
            count ++;
            if (i == A.length - 1){
                return max;
            }
            if (A[i+1] <= A[i]){
                max = Math.max(count,max);
                count = 0;
                continue;
            }

            if (A[i] < A[i+1]){
                count ++;
                boolean flag = true;
                for (int j = i; j < A.length; j++){

                    if (flag) {
                        if (A[j] < A[i]){
                            count++;
                        }
                    }
                }
            }
        }
        return 0;
    }

    // 思路有问题，看看题解

    //第一种是枚举法
    // 这个题需要用到动态规划。对于一座「山脉」，我们称首元素 B[0] 和尾元素 B[len(B)−1] 为「山脚」，
    // 满足题目要求 B[i-1] < B[i] > B[i+1] 的元素 B[i] 为「山顶」。为了找出数组 A 中最长的山脉，
    // 我们可以考虑枚举山顶，再从山顶向左右两侧扩展找到山脚。
    //
    // 由于从左侧山脚到山顶的序列是严格单调递增的，而从山顶到右侧山脚的序列是严格单调递减的，因此我们可以使用动态规划（也可以理解为递推）的方法，
    // 计算出从任意一个元素开始，向左右两侧最多可以扩展的元素数目。
    //
    //我们用 left[i] 表示 A[i]向左侧最多可以扩展的元素数目。如果A[i−1]<A[i]，
    // 那么 A[i] 可以向左扩展到A[i−1]，再扩展 left[i] 个元素，因此有
    //left[i]=left[i−1]+1
    //
    //如果 A[i−1]≥A[i]，那么 A[i]无法向左扩展，因此有
    //left[i]=0
    //特别地，当 i=0时，A[i] 为首元素，无法向左扩展，因此同样有
    //left[0]=0
    //同理，我们用 right[i] 表示 A[i] 向右侧最多可以扩展的元素数目，

    public int longestMountain2(int[] A){
        int n = A.length;
        if (n == 0){
            return 0;
        }
        int[] left = new int[n];
        //这一步计算的是每个i左边可扩展的长度
        for (int i=1; i<n; i++){
            left[i]=A[i-1] < A[i] ? left[i-1] + 1 : 0;
        }
        int[] right = new int[n];
        for (int i = n-2; i >= 0; i--){
            right[i] = A[i+1] < A[i] ? right[i+1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i){
            if(left[i] > 0 && right[i] > 0){
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }

        return ans;
    }



}