package 数组的相对顺序1122;

import java.util.*;


//提交不上去，自己跑一下测试一下
public class ArrsSort1122 {

    public static void main(String[] args) {

        int[] arr1 = {2,21,43,38,0,42,33,7,24,13,12,27,12,24,5,23,29,48,30,31};
        int[] arr2 = {2,42,38,0,43,21};

        Solution solution = new Solution();

        String result = Arrays.toString(solution.relativeSortArray(arr1,arr2));

        System.out.println(result);

    }



}

class Solution {

    //个人思路：采用暴力匹配并不合理，所以要么存储法，要么指针
    //这里采用map统计arr1中数字出现的次数，然后根据arr2出现的数字，剩下的采用排序的方法
    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        Map<Integer,Integer> map = new TreeMap<>();

        for (int i = 0; i < arr1.length; i++){
            if (!map.containsKey(arr1[i])){
                map.put(arr1[i],1);
            }else {

                int num = (int)map.get(arr1[i]);
                num++;
                map.put(arr1[i],num);

            }
        }

        int index = 0;

        for (int i = 0; i < arr2.length; i++){

            int num = map.get(arr2[i]);

            for (int j = 0; j < num; j++){
                arr1[index++] = arr2[i];
            }

            map.remove(arr2[i]);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            for (int i = 0; i < value; i++){
                arr1[index++] = key;
            }

        }

        return arr1;

    }

    //写的有点麻烦，看看题解
    //自定义排序
    public int[] relativeSortArray2(int[] arr1, int[] arr2){

        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : arr1){
            list.add(num);
        }

        //以坐标作为比较的大小
        for (int i = 0; i < arr2.length; i++){
            map.put(arr2[i],i);
        }

        Collections.sort(list,(x,y) -> {
            //意思就是当Map集合中有这个key时，就使用这个key对应的value值，如果没有就使用默认值defaultValue
            if (map.containsKey(x) || map.containsKey(y)) {

                return map.getOrDefault(x,1001) - map.getOrDefault(y,1001);
            }else {
                return x - y;
            }

        });

        for(int i = 0; i < arr1.length; i++) arr1[i] = list.get(i);
        return arr1;
    }
}