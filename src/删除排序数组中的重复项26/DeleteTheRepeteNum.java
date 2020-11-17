package 删除排序数组中的重复项26;

import java.util.Set;
import java.util.TreeSet;

public class DeleteTheRepeteNum {
}
class Solution {

    //个人思路：双指针，因为题目要求，必须是在原数组上操作
    public int removeDuplicates(int[] nums) {

        if (nums.length == 0){
            return 0;
        }

        int i = 0;
        for (int j = 1; j < nums.length; j++){
            if (nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;

    }
}