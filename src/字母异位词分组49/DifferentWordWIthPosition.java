package 字母异位词分组49;

import java.util.*;

public class DifferentWordWIthPosition {
    public static void main(String[] args) {
        System.out.println("hello word");
    }
}
class Solution {

    //个人思路：把字符串数组中单词先统计，然后按照字母数量和内容进行分类

    //两种方法，第一种就是把每个单词进行字母排序，然后存入list并返回
    public List<List<String>> groupAnagrams(String[] strs) {

        //map的作用就是为了统计排序后相同字母的单词，按照字典序排序

        Map<String,List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++){

            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            List<String> list = map.getOrDefault(key,new ArrayList<>());
            list.add(strs[i]);
            map.put(key,list);
        }

        List<List<String>> lists = new ArrayList<>();
        //常规写法
        for (Map.Entry<String,List<String>> entry : map.entrySet()){
            lists.add(entry.getValue());
        }

        return lists;

        //题解写法
        //return new ArrayList<List<String>>(map.values());

    }

    //第二种方法是采用计数
    //没有第一种方法好
    public List<List<String>> groupAnagrams2(String[] strs){
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());


    }
}