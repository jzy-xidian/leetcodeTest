package 上升下降字符串1370;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortAnddisSort {
}
class Solution {

    //个人思路，肯定是先排序，然后用while循环，看是否还有数字
    public String sortString(String s) {

        char[] arr =  s.toCharArray();

        //先排序，再转成list
        Arrays.sort(arr);

        List charAsList = Arrays.asList(arr);



        StringBuilder stringBuilder = new StringBuilder(s.length());

        for (int i = 0 ; i < charAsList.size(); i++){

            if (i == 0){
                stringBuilder.append(charAsList.get(0));

            }

        }

        return null;

    }

    //这个体最好的方法就是用桶排序
    public String sortString2(String s){
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++){
            nums[s.charAt(i) - 'a']++;
        }

        StringBuffer stringBuffer = new StringBuffer();
        while (stringBuffer.length() < s.length()){

            for (int i = 0; i < s.length(); i++){
                if (nums[i] > 0){
                    stringBuffer.append((char) (i + 'a'));
                    nums[i]--;
                }
            }

            for (int i = 25; i >= 0; i--){
                if (nums[i] > 0){
                    stringBuffer.append((char)(i+'a'));
                    nums[i]--;
                }
            }
        }

        return stringBuffer.toString();

    }
}