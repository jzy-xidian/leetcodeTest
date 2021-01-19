package 同构字符串205;

import java.util.HashMap;
import java.util.Map;

public class SameString {
}
class Solution {
    //思路：用map存储，然后一一对比
    public boolean isIsomorphic(String s, String t) {

        //开始没有问题，但是忽略了这种情况：
        //babc baba,这样采用存储会报错
        Map<Character,Character> map = new HashMap<>();
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        if(s.length() != t.length()){
            return false;
        }
        for(int i = 0; i < str1.length; i++){
            if(!map.containsKey(str1[i])){
                map.put(str1[i],str2[i]);
            }
            else{
                if(str2[i] != map.get(str1[i])){
                    return false;
                }
            }
        }

        return true;


    }
    //题解采用双哈希表的方法
    //这个题的关键在于双射问题
    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }


}