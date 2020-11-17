package 独一无二的出现次数1207;

import java.util.*;

public class UniqueNums {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {-3,0,1,-3,1,1,1,-3,10,0};
        System.out.println(solution.uniqueOccurrences(arr));
    }
}

class Solution {
    //自己的思路：用map统计每个数的出现次数，然后再对比
    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer,Integer> map = new HashMap<>();

        List<Integer> list = new ArrayList<>();

        int length = arr.length;

        for (int i = 0; i < length; i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);

            }
            else {
                int num = map.get(arr[i]);
                map.put(arr[i],++num);
            }
        }

        //采用map的遍历value集的方法
        //遍历所有的value集：values()
        //打印出的结果和原本键值对的顺序是一样的
        Collection<Integer> values = map.values();
        for(Integer obj : values){
            list.add(obj);
        }



        //用循环去对比，看是否有相同的出现次数
        for (int i = 0; i < list.size() - 1 ; i++){
            for (int j = i + 1; j < list.size(); j++){
                if (list.get(i).equals(list.get(j))){
                    return false;
                }
            }
        }

        return true;

    }

    //这特么就很奇怪，collection方法编译过不去，**leetcode编译器！！！！！

    //看看官方题解，自己的解法好像又是挺蠢的、

    public boolean uniqueOccurrences2(int[] arr){
        Map<Integer, Integer> occur = new HashMap<Integer, Integer>();
        for (int x : arr) {
            occur.put(x, occur.getOrDefault(x, 0) + 1);
        }

        //下一步就是用Set获取到值，使用Map.Entry
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer,Integer> x : occur.entrySet()){
            //如果有相同的出现次数，set不会对相同的值进行添加
            set.add(x.getValue());
        }

        return set.size() == occur.size();
    }
}