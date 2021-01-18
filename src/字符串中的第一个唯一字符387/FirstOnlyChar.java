package 字符串中的第一个唯一字符387;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstOnlyChar {

    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();

        List<Integer> list = new ArrayList<>();

        //测试下索引的变化
        map.put("aaa",2);
        map.put("bbb",3);
        map.put("ccc",4);
        map.put("ddd",5
        );

        for (String key : map.keySet()){
            System.out.println("key: " + key + "..... valiue:"  + map.get(key) );

        }

        System.out.println("-----------------------------------");

        map.put("aaa",5);

        for (Map.Entry<String ,Integer> entry : map.entrySet()){
            System.out.println("key: " + entry.getKey() + ".........value:" + entry.getValue());
        }

        System.out.println("-----------------------------------");

        map.remove("aaa",5);

        for (Map.Entry<String ,Integer> entry : map.entrySet()){
            System.out.println("key: " + entry.getKey() + ".........value:" + entry.getValue());
        }


    }
}
class Solution {
    public int firstUniqChar(String s) {

        if(s.length() == 0){
            return -1;
        }

        //需要统计字符个数，然后进行返回
        //采用map，但是这里不能用treeMap排序
        //所以还要借用一次ArrayList
        //第一遍真的是蠢到家了，不能把list也移掉
        Map<Character,Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        char[] charArr = s.toCharArray();

        for(int i = 0; i < charArr.length; i++){
            if(!map.containsKey(charArr[i])){
                map.put(charArr[i],i);
                list.add(charArr[i]);
            }
            else{

                //list可以按照索引删除，也可以按照类型删除
                //千万不能移除map的key，否则来回插入一定会有问题
                list.remove((Character)charArr[i]);
            }
        }

        if(list.size() == 0){
            return -1;
        }


        return map.get(list.get(0));


    }
}