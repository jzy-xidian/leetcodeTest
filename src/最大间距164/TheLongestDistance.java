package 最大间距164;

import java.util.*;

public class TheLongestDistance {
}


class Solution {

    //个人思路：对传入的数组升序排序，然后用TreeSet进行统计，直接返回最后一个Set
    public int maximumGap(int[] nums) {

        if (nums.length < 2 || nums == null){
            return 0;
        }

        Arrays.sort(nums);


        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length-1; i++){

            int distance = nums[i+1] - nums[i];

            set.add(distance);

        }

        set = ((TreeSet<Integer>) set).descendingSet();

        Iterator iterator = set.iterator();

        int Largest = 0;
        int count = 0;

        while (iterator.hasNext()){

            if (count == 1){
                break;
            }

            Largest = (int) iterator.next();

            count++;

        }


        return Largest;


    }
}
