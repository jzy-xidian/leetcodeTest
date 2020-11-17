package 有多少小于当前数字的数字1365;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class smallerNumberThanNow {
}

class Solution {
    //自己的思路：最简单的办法就是依次遍历，查找数组中其他比自己更小的数字
    //用一次双循环
    public int[] smallerNumbersThanCurrent(int[] nums) {

        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            int count = 0;
            for (int j = 0; j < nums.length; j++){
                if (nums[j] < nums[i]){
                    count++;
                }
            }

            arr[i] = count;
        }

        return arr;

    }

    //也可以排序后采用map统计比自己小的数量，用index对比就可以
    public int[] smallerNumbersThanCurrent2(int[] nums){
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        //先进行数组的拷贝
        int[] temp = Arrays.copyOf(nums,nums.length);

        Arrays.sort(temp);
        for (int i = 0; i < temp.length; i++){
            if (!map.containsKey(temp[i])){
                map.put(temp[i],i);
            }
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            result[i] = map.get(nums[i]);
        }

        return result;
    }
}
