package 划分字母区间763;

import java.util.ArrayList;
import java.util.List;

public class letterRange {
}


class Solution {
    //这个题真的没有思路，看看题解，题目开始都没看懂。。。。。

    //官方题解是 贪心算法 + 双指针
    //由于同一个字母只能出现在同一个片段，显然一个字母第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段
    //需要遍历字符串，得到每个字符最后一次出现的下标位置

    //得到每个字符最后一次出现的下标位置后，可以使用贪心算法和双指针的方法把字符串划分为尽可能多的片段，
    //1.从左到右遍历字符串，遍历的同时维护当前片段的开始下标start和结束下标end，初始start = end = 0
    //2.对于每个能访问到的字母C，得到当前字母最后一次出现的下标位置endc，则当前片段结束下标一定不会小于endc
    //则当前片段的结束下标一定不会小于endc，因此令end=max（end，endc)
    //3.当访问到下标end时，当前片段访问结束，当前片段的下标范围是（start，end），长度为end - start + 1，
    //将当前片段的长度添加到返回值，然后令start = end + 1，继续寻找下一个片段。
    //重复以上过程，知道遍历完字符串

    //上述做法是使用贪心算法的思想寻找每个片段可能的最小结束下标，因此可以保证每个片段的长度一定是符合要求的最短长度，
    //如果取更短的片段，一定会出现同一个字母出现在多个片段的情况，由于每次取的片段都是符合要求的最短片段，因此得到的片段数也是最多的

    //由于每个片段访问结束的标志是访问到下标end，因此对于每个片段，可以保证当前片段每个字母都一定在当前片段中，不可能在其他片段
    public List<Integer> partitionLabels(String S) {
        //26个字母
        int[] last = new int[26];
        //计算传入串的长度
        int length = S.length();
        //遍历字符串，保存字母最后一次出现的下标
        for (int i = 0; i < length; i++){
            last[S.charAt(i) - 'a'] = i;
        }

        List<Integer> partition = new ArrayList<>();
        int start = 0, end = 0;

        for (int i = 0; i < length; i++){
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end){
                partition.add(end - start +1);
                start = end + 1;
            }
        }

        return partition;

    }
}