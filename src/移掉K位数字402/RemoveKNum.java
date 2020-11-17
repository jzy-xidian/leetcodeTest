package 移掉K位数字402;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class RemoveKNum {
}

class Solution {

    //个人思路：感觉应该就是动态规划或者贪心算法

    //想不出来，看看题解吧
    //采用的是贪心+单调栈的方法
    //两个相同长度的序列，最左边不同的数字决定两个数字的大小，还要保证靠前的数字尽可能小
    //由于是从左到右，所以让每一个元素和左邻居进行比较，采用双端队列，避免反转Deque 是 Double ended queue (双端队列) 的缩写,读音和 deck 一样，蛋壳。

    //这里采用双端的好处是，在比较前一个和后一个元素大小的时候，可以看作是出栈操作，最后输出结果直接从队头输出，避免反转。
    public String removeKdigits(String num, int k) {

        Deque<Character> deque = new LinkedList<>();

        int len = num.length();

        for (int i = 0; i < len; i++){

            char digit = num.charAt(i);

            //把当前元素和队尾元素进行比较，如果队尾元素大，队尾出栈，当前元素出栈，直到不降的情况出现
            while (!deque.isEmpty() && k > 0 && deque.peekLast() >digit){

                //队尾元素出队
                deque.pollLast();
                //说明已经移除了一个数字
                k--;

            }

            //当前元素进行入队操作
            deque.offerLast(digit);
        }

        //如果一直是不降的，就把最后的K个元素移除就行了
        for (int i = 0; i < k; i++){
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();

        boolean leadingZero = true;

        while (!deque.isEmpty()){

            char digit = deque.pollFirst();

            if (leadingZero && digit == '0'){
                continue;
            }

            leadingZero = false;
            ret.append(digit);
        }

        return ret.length() == 0 ? "0" : ret.toString();




    }
}
