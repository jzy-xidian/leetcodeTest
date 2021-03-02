package 区域和检索_数组不可变303;

public class ZoneAndSearch {

}

class NumArray {

    //题的意思都不太理解

    /**
     * 最朴素的想法是存储数组 nums 的值，每次调用 sumRange 时，通过循环的方法计算数组 nums 从下标 i 到下标 j 范围内的元素和，需要计算 j-i+1 个元素的和。由于每次检索的时间和检索的下标范围有关，因此检索的时间复杂度较高，如果检索次数较多，则会超出时间限制。
     *
     * 由于会进行多次检索，即多次调用 sumRange，因此为了降低检索的总时间，应该降低 sumRange 的时间复杂度，最理想的情况是时间复杂度 O(1)O(1)。为了将检索的时间复杂度降到 O(1)，需要在初始化的时候进行预处理。
     *
     * @param nums
     */

    //其实也就是在算i--j下标数据和的时候，计算0-j下标和与0-(i-1)下标和的差值
    //最核心的计算也就是在初始化的时候，计算好每个下标的前缀和，就可以满足每次调用sumRange时间复杂度都是1

    /**
     * 具体实现方面，假设数组 nums 的长度为 n，创建长度为 n+1 的前缀和数组 sums，对于 0≤i<n 都有 sums[i+1]=sums[i]+nums[i]，则当 0<i≤n 时，sums[i] 表示数组 nums 从下标 0 到下标 i−1 的前缀和。
     *
     * 将前缀和数组 sums 的长度设为 n+1 的目的是为了方便计算 sumRange(i,j)，不需要对 i=0 的情况特殊处理。此时有：
     *
     *
     * sumRange(i,j)=sums[j+1]−sums[i]
     *
     *
     */

    //在初始化的时候就要计算好前缀和

    int[] sums;

    public NumArray(int[] nums) {
        int n = nums.length;
        sums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
