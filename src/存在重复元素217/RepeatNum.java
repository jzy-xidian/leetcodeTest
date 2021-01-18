package 存在重复元素217;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RepeatNum {
}
class Solution {

    //个人思路：直接采用map，然后把
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> set = new HashSet<>();

        boolean flag = false;

        for (int i = 0; i < nums.length; i++){
            if (set.contains(nums[i])){
                flag = true;
            }
            else {
                set.add(nums[i]);
            }
        }

        return flag;

    }

    //还可以采用排序的方法，在对数字从小到大排序之后，
    // 数组的重复元素一定出现在相邻位置中。因此，我们可以扫描已排序的数组，
    // 每次判断相邻的两个元素是否相等，如果相等则说明存在重复的元素。
    public boolean containsDuplicate2(int[] nums){
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;

    }


}