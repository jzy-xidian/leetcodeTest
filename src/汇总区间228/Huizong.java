package 汇总区间228;

import java.util.ArrayList;
import java.util.List;

public class Huizong {
}
class Solution {
    //题都看不懂，放弃了

    //我们从数组的位置 0 出发，向右遍历。
    // 每次遇到相邻元素之间的差值大于 1 时，我们就找到了一个区间。
    // 遍历完数组之后，就能得到一系列的区间的列表。
    public List<String> summaryRanges(int[] nums) {

        List<String> list = new ArrayList<>();

        int i = 0;
        int n = nums.length;
        while (i < n){

            //从第二个元素开始计算
            int low = i;
            i++;

            while (i < n && nums[i] == nums[i-1] + 1){
                i++;
            }
            int high = i-1;
            StringBuffer stringBuffer = new StringBuffer(Integer.toString(nums[low]));
            if (low < high) {
                stringBuffer.append("->");
                stringBuffer.append(Integer.toString(nums[high]));
            }
            list.add(stringBuffer.toString());


        }

        return list;



    }
}