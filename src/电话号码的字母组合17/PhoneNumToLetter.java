package 电话号码的字母组合17;

import java.util.*;

public class PhoneNumToLetter {
}

class Solution {

    //自己的思路：先采用map进行存储，然后获取组合结果
    public List<String> letterCombinations(String digits) {

        HashMap<String, List<String>> map = new HashMap<>();

        //先对每个数字代表的字母进行枚举
        map.put("2", Arrays.asList("a","b","c"));
        map.put("3", Arrays.asList("d","e","f"));
        map.put("4", Arrays.asList("g","h","i"));
        map.put("5", Arrays.asList("j","k","l"));
        map.put("6", Arrays.asList("m","n","o"));
        map.put("7", Arrays.asList("p","q","r","s"));
        map.put("8", Arrays.asList("t","u","v"));
        map.put("9", Arrays.asList("w","x","y","z"));

        char[] arr = digits.toCharArray();
        int i = 0;
        //暴力枚举,如果用这种方法，会十分复杂
        while (i < arr.length){

        }

        return null;

    }

    //这个题用回溯法比较好
    public List<String> letterCombinations2(String digits) {

        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;




    }

    public void backtrack(List<String> combinations, Map<Character,String> phoneMap, String digits, int index, StringBuffer combination){
        if (index == digits.length()) {
            combinations.add(combination.toString());
        }else {
            //先找到index对应的数字
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            //获取到该数字能够代表的字符的个数
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++){
                combination.append(letters.charAt(i));
                //递归调用，进行下一个索引对应的数字的字母串，进行组合
                backtrack(combinations,phoneMap,digits,index + 1,combination);
                combination.deleteCharAt(index);
            }

        }


    }
}
