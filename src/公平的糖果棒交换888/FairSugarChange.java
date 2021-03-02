package 公平的糖果棒交换888;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FairSugarChange {
}
class Solution {

    //算差的题，计算出差多少，然后用相差的数进行交换补齐

    public int[] fairCandySwap(int[] A, int[] B) {

        //采用java8的流的特性，直接计算两个数组的和
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();

        //计算差值再除以2，就是大的要给小的补的值
        int delta = (sumA - sumB) / 2;
        Set<Integer> set = new HashSet<>();

        //先把A的元素存入哈希表
        for(int num : A){
            set.add(num);
        }

        int[] ans = new int[2];
        for (int y : B){
            int x = y + delta;

            //只要有一对数字满足条件，即可存入数组
            if (set.contains(x)){
                ans[0] = x;
                ans[1] = y;

                break;
            }
        }

        return ans;


    }
}