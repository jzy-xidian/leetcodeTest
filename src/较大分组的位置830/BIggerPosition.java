package 较大分组的位置830;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BIggerPosition {
}

class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {

        List<List<Integer>> listResult = new ArrayList<>();
        int start = 0;
        int stop = 0;

        if(s.length() < 3){
            return listResult;
        }

        char[] arr = s.toCharArray();

        int index = arr[0];
        int sum = 1;
        for(int i = 1; i < arr.length; i++){

            if(arr[i] == arr[i-1]){
                sum++;
            }
            else{
                if(sum >= 3){
                    stop = i-1;
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(stop);
                    listResult.add(list);
                }
                sum = 1;
                start = i;
            }

        }

        return listResult;


    }

    //自己写的过于麻烦，看看官方解法
    public List<List<Integer>> largeGroupPositions2(String s) {

        List<List<Integer>> listResult = new ArrayList<>();
        int n = s.length();
        int num = 1;
        for (int i = 0; i < n; i++){
            if (i == n-1 || s.charAt(i) != s.charAt(i+1)){
                if(num >= 3){
                    listResult.add(Arrays.asList(i-num +1, i));
                }
                num = 1;
            }
            else {
                num++;
            }
        }
        return listResult;


    }
}

