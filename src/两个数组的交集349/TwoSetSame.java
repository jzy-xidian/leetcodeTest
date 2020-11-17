package 两个数组的交集349;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TwoSetSame {
}

class Solution {

    //自己的思路：不能重复，所以直接用set存就可以了，反正没有顺序问题
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums1.length; i++){
            for (int j = 0; j < nums2.length; j++){
                if (nums1[i] == nums2[j]){
                    set.add(nums1[i]);
                }
            }
        }

        int[] arr = new int[set.size()];
        Iterator<Integer> iterator = set.iterator();

        int i = 0;
        while (iterator.hasNext()){
            arr[i] = iterator.next();
            ++i;
        }
       return arr;
    }

    //看看官方的题解

}