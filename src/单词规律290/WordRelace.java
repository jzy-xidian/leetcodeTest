package 单词规律290;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordRelace {

    public static void main(String[] args) {
        String s1 = "abba";
        String s2 = "dog cat cat dog";

        Solution solution = new Solution();
        System.out.println(solution.wordPattern(s1,s2));
    }


}
class Solution {

    //个人思路：其实就是一对应关系
    //先判断长度，如果长度都不一样，必然不同
    //再判断是否一一对应
    public boolean wordPattern(String pattern, String s) {

        //还要考虑一对一的问题

        String[] strArr = s.split(" ");
        char[] charArr = pattern.toCharArray();

        if(strArr.length != charArr.length){
            return false;
        }

        //加两个set作为不同数的对比
        Set<Character> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        for(int i = 0; i < pattern.length(); i++){
            set1.add(charArr[i]);
            set2.add(strArr[i]);
        }

        if(set1.size() != set2.size()){
            return false;
        }



        Map<Character,String> map = new HashMap<>();

        for(int i = 0; i < pattern.length(); i++){

            if(!map.containsKey(charArr[i])){
                map.put(charArr[i],strArr[i]);
            }else{

                String result = map.get(charArr[i]);
                if(!result.equals(strArr[i])){
                    return false;
                }
            }

        }

        return true;
    }
}