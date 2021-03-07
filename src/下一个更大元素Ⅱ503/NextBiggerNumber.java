package 下一个更大元素Ⅱ503;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class NextBiggerNumber {
}
class Solution {

    //采用单调栈和循环数组
    //单调栈中保存的是下标，从栈底到栈顶的下标在数组 nums 中对应的值是单调不升的。
    //每次移动到数组的一个新的位置i，就将当前单调栈中所有对应值小于nums[i]的下标弹出单调栈，这些值的下一个更大元素就是nums[i],
    //但是这里是循环遍历，例如序列 [2,3,1]，最后单调栈中将剩余 [3,1]，其中元素 [1] 的下一个更大元素还是不知道的。
    //一个朴素的思想是，我们可以把这个循环数组「拉直」，即复制该序列的前 n−1 个元素拼接在原序列的后面。这样我们就可以将这个新序列当作普通序列，用上文的方法来处
    //而在本题中，我们不需要显性地将该循环数组「拉直」，而只需要在处理时对下标取模即可。

    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] ret = new int[n];

        Arrays.fill(ret,-1);

        //双端队列
        Deque<Integer> stack = new LinkedList<>();
        //n*2-1作为拼接，可以把还是未知下一个元素的值通过一次拉伸进行查找
        for (int i = 0; i < n*2-1; i++){

            //根据栈
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]){

                //如果队列内的元素都比目前要入队的元素小，给元素赋值并且出队，通过索引对应的值来判断
                ret[stack.pop()] = nums[i % n];
            }

            //双端队列中存的是下标
            stack.push(i % n);

        }

        return ret;


    }
}