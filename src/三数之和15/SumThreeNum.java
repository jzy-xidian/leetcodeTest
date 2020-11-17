package 三数之和15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumThreeNum {
}

//「不重复」的本质是什么？我们保持三重循环的大框架不变，只需要保证：
//
//第二重循环枚举到的元素不小于当前第一重循环枚举到的元素；
//
//第三重循环枚举到的元素不小于当前第二重循环枚举到的元素。
//
//也就是说，我们枚举的三元组 (a, b, c)(a,b,c) 满足 a \leq b \leq ca≤b≤c，保证了只有 (a, b, c)(a,b,c) 这个顺序会被枚举到，
// 而 (b, a, c)(b,a,c)、(c, b, a)(c,b,a) 等等这些不会，这样就减少了重复。要实现这一点，我们可以将数组中的元素从小到大进行排序，随后使用普通的三重循环就可以满足上面的要求。
//

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(nums == null || len < 3)
            return ans;

        //先对数组进行排序
        Arrays.sort(nums);

        for (int i = 0; i < len; i++){
            //由于已经进行过排序，nums[i]>0时sum必定不可能为0
            if(nums[i] > 0)
                break;
            //如果 nums[i] == nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过(这里要仔细理解一下)
            //这里千万要注意，是i > 0,否则会出现数组越界问题，i-1会变成-1
            if(i > 0 && nums[i] == nums[i-1])
                continue;

            int L = i + 1;
            int R = len - 1;

            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    // sum == 0 时，nums[L] == nums[L+1] 则会导致结果重复，应该跳过，L++L++
                    while (L < R && nums[L] == nums[L+1])
                        L++;//去重
                    while (L <R && nums[R] == nums[R-1])
                        R--; // 去重
                    L++;
                    R--;

                }
                else if(sum < 0)
                    L++;
                else if(sum > 0)
                    R--;

            }

        }
        return ans;
    }
}
