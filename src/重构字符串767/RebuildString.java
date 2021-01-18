package 重构字符串767;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RebuildString {
}

class Solution {

    //个人思路：采用桶排序，对每个字符的个数进行统计，然后按照字符的个数排序，
    //只要剩下的字符个数大于最大的，就可以重新排列组合
    public String reorganizeString(String S) {

        int[] letter = new int[26];

        for (int i = 0; i < S.length(); i++){

            char arr = S.charAt(i);
            letter[arr - 'a'] ++;

        }



        return "";
    }

    //这个题没啥思路，看看题解
    //确实是典型的贪心算法。但是对贪心的理解好像还是不够
    /**
     * 假设字符串的长度为 nn，如果可以重新排布成相邻的字母都不相同的字符串，每个字母最多出现多少次？
     * n为偶数的时候，有一半的偶数下标和一半的奇数下标，如果超过一半，必然出现相邻
     */

    //第一种就是基于最大堆的贪心算法
//    护最大堆存储字母，堆顶元素为出现次数最多的字母。首先统计每个字母的出现次数，然后将出现次数大于 0 的字母加入最大堆。
//
//    当最大堆的元素个数大于 1 时，每次从最大堆取出两个字母，拼接到重构的字符串，然后将两个字母的出现次数分别减 1，并将剩余出现次数大于 0 的字母重新加入最大堆。由于最大堆中的元素都是不同的，因此取出的两个字母一定也是不同的，将两个不同的字母拼接到重构的字符串，可以确保相邻的字母都不相同。
//
//    如果最大堆变成空，则已经完成字符串的重构。如果最大堆剩下 1 个元素，则取出最后一个字母，拼接到重构的字符串。

    public String reorganizeString2(String S){
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }

        //判断长度是否大于一半，大于一半必定会有同元素相邻
        if (maxCount > (length + 1) / 2) {
            return "";
        }

        //采用优先队列，倒序实现每个字母数量的排序

        PriorityQueue<Character> queue= new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return counts[o2 - 'a'] - counts[o1 - 'a'];
            }
        });

        //只要数量大于1，按照每个字母出现的次数的排序规则将每个字母入队，注意，这里是入队字母，不是入队数量
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }

        StringBuffer sb = new StringBuffer();

        while (queue.size() > 1){
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;

            //还要重新将两个元素入队一次
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }

        }

        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();



    }

    //第二种是基于计数的贪心算法，可以看成插空法
    public String reorganizeString3(String S){

        if (S.length() < 2){
            return S;
        }

        int[] counts = new int[26];

        int maxCount = 0;

        int length = S.length();


        for (int i = 0; i < length; i++){
            char c = S.charAt(i);

            counts[c - 'a']++;

            maxCount = Math.max(maxCount, counts[c - 'a']);
        }

        if (maxCount > (length + 1) / 2){
            return  "";
        }

        char[] reorganizeArray = new char[length];

        int evenIndex = 0,oddIndex = 1;
        int halfLength = length / 2;

        for (int i = 0; i < 26 ;i++){

            char c = (char) ('a' + i);
            //这一步就是采用插空法
            while (counts[i] > 0 && counts[i] <= halfLength && oddIndex < length) {
                reorganizeArray[oddIndex] = c;
                counts[i]--;
                oddIndex += 2;
            }

            while (counts[i] > 0) {
                reorganizeArray[evenIndex] = c;
                counts[i]--;
                evenIndex += 2;
            }


        }

        return new String(reorganizeArray);


    }

}