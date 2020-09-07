package 两数之和01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfTwo {
    public static void main(String[] args) {

        int[] array = new int[]{2,7,15,6};
        int num = 9;
        Solution2 solution2 = new Solution2();
        System.out.println(Arrays.toString(solution2.twoSum(array,num)));

    }

}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        int k=0;

        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i] + nums[j]==target){
                    a[k++]=i;
                    a[k++]=j;
                }
            }
        }
        return a;
    }
}

//采用hashmap的解法
//这里使用new int[2]会出现数组越界，因为map会从头开始查，会导致查两遍，应该直接返回，跳出循环
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        //错误的写法如下：
//        int[] arr = new int[100];
//        int k =0;
//
//        Map<Integer,Integer> map = new HashMap<>();
//        for(int i=0;i<nums.length;i++){
//
//                map.put(nums[i],i);
//
//        }
//
//        for(int j=0;j<nums.length;j++){
//            int result = target - nums[j];
//            if(map.containsKey(result)&&map.get(result)!=j){
//
//                arr[k++] = j;
//                arr[k++] = map.get(result);
//            }
//        }
//
//        return arr;

        //正确写法如下：
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");

    }
}
