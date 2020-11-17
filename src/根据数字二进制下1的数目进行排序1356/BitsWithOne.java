package 根据数字二进制下1的数目进行排序1356;

import java.util.*;

public class BitsWithOne {
}


class Solution {

    //个人思路：先要把整数转换成二进制，然后分别统计1的个数，最后排序
    //忘了二级制了，直接看答案。。。

    //第一种就是暴力匹配，在十进制转二进制的时候统计一下1的个数就可以了
    public int[] sortByBits(int[] arr) {

        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int x : arr){
            list.add(x);
            bit[x] = get(x);
        }

        //对传入的数组里的数进行自定义规则的排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                //先根据十进制数对应的1的个数从小道道排序
                if (bit[o1] != bit[o2]){
                    return bit[o1] - bit[o2];
                }
                //如果两个数的二进制数含有1的个数一样，对比十进制数本身
                else {
                    return o1 -o2;
                }
            }
        });

        //重新赋值给arr
        for (int i = 0; i < arr.length; i++){
            arr[i] = list.get(i);
        }

        return arr;


    }

    //在转换的过程中统计一下1的个数，也就是对2求余过程中为1的情况
    public int get(int x){

        int res = 0;
        while (x != 0){
            res += x%2;
            x /= 2;
        }

        return res;

    }

    //再看一下大佬的解法
    public int[] sortByBits2(int[] arr){

        //直接采用Integer中的一个方法Integer.bitCount(arr[i])
        //bitCount实现的功能是计算一个（byte,short,char,int统一按照int方法计算）int,long类型的数值在二进制下“1”的数量。

        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            map[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }

        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 10000000;
        }
        return map;


    }


}