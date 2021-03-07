package 替换后最长重复字符424;

public class ReplaceLargest {
}
class Solution {

    //同类问题：

    /***
     * 「力扣」第 1004 题：最大连续 1 的个数 III；
     * 「力扣」第 1208 题：尽可能使字符串相等；
     * 「力扣」第 1493 题：删掉一个元素以后全为 1 的最长子数组。
     *
     *
     * 「力扣」第 3 题：无重复字符的最长子串；
     * 「力扣」第 209 题：长度最小的子数组；
     * 「力扣」第 76 题：最小覆盖子串；
     * 「力扣」第 438 题：找到字符串中所有字母异位词；
     * 「力扣」第 567 题：字符串的排列。
     *


     * @param s
     * @param k
     * @return
     */

    //根据输入的k，决定要替换的字母个数，为了达到最长的重复字符串
    public int characterReplacement(String s, int k) {

        int len = s.length();
        if (len < 2){
            return len;
        }

        //先把字符串变为字符数组
        char[] charArray = s.toCharArray();

        //双指针的位置可以决定滑动窗口的大小
        int left = 0;
        int right = 0;

        //用来统计最终的长度
        int res = 0;

        //用来统计滑动窗口内字符出现最多次数的个数
        int maxCount = 0;
        int[] freq = new int[26];

        // [left, right) 内最多替换 k 个字符可以得到只有一种字符的子串
        while (right < len){

            //对字符出现的次数进行统计，主要是计算目前出现在滑动串口内的字符数
            freq[charArray[right] - 'A']++;

            //在这里维护 maxCount，因为每一次右边界读入一个字符，字符频数增加，才会使得 maxCount 增加,计算出目前出现次数最多的字符
            //看看进入的这个元素和最大的字母统计哪个更大，是否更新maxCount
            maxCount = Math.max(maxCount,freq[charArray[right] - 'A']);

            //更新右指针，同时方便计算滑动窗口的长度是否满足当前最大频度字母数量+能替换的数量
            right++;

            if (right - left > maxCount + k){

                //说明此时K不够用
                // 把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
                // 移出滑动窗口的时候，频数数组须要相应地做减法

                freq[charArray[left] - 'A']--;
                //左指针进行右移动
                left++;

            }

            res = Math.max(res,right - left);




        }

        return res;




    }
}