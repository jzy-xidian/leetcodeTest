package 找不同389;

import java.util.HashMap;
import java.util.Map;

public class FindDifferent {

    public static void main(String[] args) {

        String s1 = "abcd";
        String s2 = "abcde";

        Solution solution = new Solution();

        System.out.println(solution.findTheDifference(s1,s2));
    }
}

class Solution {

    //个人思路：计算总量？对比元素？
    public char findTheDifference(String s, String t) {

        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for(int i = 0; i < sarr.length; i++){
            int num = map1.getOrDefault(sarr[i],0);
            map1.put(sarr[i],++num);
        }

        for(int i = 0; i < tarr.length; i++){
            int num = map2.getOrDefault(tarr[i],0);
            map2.put(tarr[i],++num);
        }

        for(Map.Entry<Character,Integer> entry : map2.entrySet()){
            System.out.println(entry.getKey() + "---2:" + entry.getValue());
            System.out.println(map1.get(entry.getKey()));
            if(entry.getValue() != map1.get(entry.getKey())){
                return entry.getKey();
            }
        }

        return '\0';
    }

    //写法过于麻烦，看看题解
    //计数法
    public char findTheDifference2(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            cnt[ch - 'a']++;
        }
        for (int i = 0; i < t.length(); ++i) {
            char ch = t.charAt(i);
            cnt[ch - 'a']--;
            if (cnt[ch - 'a'] < 0) {
                return ch;
            }
        }
        return ' ';

    }
}