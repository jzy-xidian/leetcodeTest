package 有效的字母异位词242;

import java.util.*;

public class DifferentPosition {
}
class Solution {

    //个人思路：直接采用map进行统计，看字母数量是否一致
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()){
            return false;
        }

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++){

            if (!map1.containsKey(s.charAt(i))){
                map1.put(s.charAt(i),1);
            }
            else {
                int num = map1.get(s.charAt(i));
                num++;
                map1.put(s.charAt(i),num);
            }

            if (!map2.containsKey(t.charAt(i))){
                map2.put(t.charAt(i),1);
            }
            else {
                int num = map2.get(t.charAt(i));
                num++;
                map2.put(t.charAt(i),num);
            }

        }



            Set entrySet = map1.entrySet();
            Iterator iterator = entrySet.iterator();
            while (iterator.hasNext()){

                Map.Entry<Character,Integer> entry = (Map.Entry<Character, Integer>) iterator.next();

                if (!map2.containsKey(entry.getKey())){
                    return false;
                }else {
                    int num = entry.getValue();
                    int num2 = map2.get(entry.getKey());

                    if (num != num2){
                        return false;
                    }
                }

            }

            return true;
        }

        //看看官方的两种写法，比较不错
        public boolean isAnagram2(String s, String t){
            if (s.length() != t.length()) {
                return false;
            }
            char[] str1 = s.toCharArray();
            char[] str2 = t.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            return Arrays.equals(str1, str2);


        }

    public boolean isAnagram3(String s, String t){
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;




    }


}