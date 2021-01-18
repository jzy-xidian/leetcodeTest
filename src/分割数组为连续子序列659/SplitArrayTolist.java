package 分割数组为连续子序列659;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class SplitArrayTolist {
}

class Solution {
    //个人思路：排序，重复的取出来，然后新开数组，如果长度不到3，返回false
    public boolean isPossible(int[] nums) {

        if (nums.length < 3){
            return false;
        }

        //已经升序排序，放入子序列，重复的往下一个序列放

        //用来统计出现次数最多数字，决定开辟几个数组
        int MaxCount = 1;

        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(nums[i])){
                int num = map.get(nums[i]);
                num++;
                map.put(nums[i],num);

                MaxCount = MaxCount >= num ? MaxCount : num;
            }
            else {

                map.put(nums[i],1);
            }
        }

        //如果一个数出现了三次以上怎么办，继续开辟list似乎并不合理
        return true;

    }

    //看看题解吧
    //第一种：哈希表+最小堆
    //由于需要将数组分割成一个或多个由连续整数组成的子序列，
    // 因此只要知道子序列的最后一个数字和子序列的长度，就能确定子序列。
    public boolean isPossible2(int[] nums){

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int x : nums){
            if (!map.containsKey(x)){
                map.put(x, new PriorityQueue<Integer>());
            }

            //计算x-1的队列长度
            //这里采用小顶堆，就是为了把连续元素加到长度较短的一个队列中
            if (map.containsKey(x-1)){

                //poll()方法用于返回第一个元素，并从此PriorityQueue中删除一个元素。
                int prevLength = map.get(x-1).poll();
                if (map.get(x-1).isEmpty()){
                    map.remove(x-1);
                }

                map.get(x).offer(prevLength + 1);

            }
            else {
                map.get(x).offer(1);
            }
        }

        Set<Map.Entry<Integer,PriorityQueue<Integer>>> entrySet = map.entrySet();
        for (Map.Entry<Integer,PriorityQueue<Integer>> entry : entrySet){
            PriorityQueue<Integer> queue = entry.getValue();
            if (queue.peek() < 3){
                return false;
            }
        }

        return true;

    }

    //第二种就算贪心算法
    //对于数组中的元素 xx，如果存在一个子序列以 x-1结尾，则可以将 x 加入该子序列中。
    // 将 x 加入已有的子序列总是比新建一个只包含 x的子序列更优，因为前者可以将一个已有的子序列的长度增加 1，
    // 而后者新建一个长度为 1 的子序列，而题目要求分割成的子序列的长度都不小于 3，因此应该尽量避免新建短的子序列。


    //使用两个哈希表，
    // 第一个哈希表存储数组中的每个数字的剩余次数，第二个哈希表存储数组中的每个数字作为结尾的子序列的数量。

    public boolean isPossible3(int[] nums){
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();

        //第一个哈希表存储数组出现的元素和每个元素对应的次数
        for (int x : nums){
            int count = countMap.getOrDefault(x,0) + 1;
            countMap.put(x,count);
        }

        for (int x : nums){
            int count = countMap.getOrDefault(x,0);
            if (count > 0){
                int prevEndCount = endMap.getOrDefault(x-1,0);
                if (prevEndCount > 0){
                    countMap.put(x, count-1);
                    endMap.put(x-1,prevEndCount - 1);
                    endMap.put(x,endMap.getOrDefault(x,0) + 1);

                }
                //就是为了判断是否后面两个数还有剩余量，没有的话直接返回false
                else {
                    int count1 = countMap.getOrDefault(x + 1, 0);
                    int count2 = countMap.getOrDefault(x + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(x, count - 1);
                        countMap.put(x + 1, count1 - 1);
                        countMap.put(x + 2, count2 - 1);
                        endMap.put(x + 2, endMap.getOrDefault(x + 2, 0) + 1);
                    } else {
                        return false;
                    }

                }
            }

        }
        return true;
    }
}