package 单词拆分139;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSplit {
    public static void main(String[] args) {

        //测试一下截取


        boolean[] result = new boolean[10];
        for (int i = 0; i < 10; i++){
            System.out.println(result[i]);
        }
    }
}

class Solution {

    //这个应该就是根据单词列表，然后从头开始遍历，如果能匹配上，去掉这一部分，后面的继续匹配，最后如果剩下0，则说明可以匹配

    public boolean wordBreak(String s, List<String> wordDict) {

        if (s.equals("") || wordDict.size() == 0){
            return false;
        }


        while (!s.equals("")){

            //每次都循环匹配，如果出现都不匹配的情况，返回false，否则返回true
            for (int i = 0; i < wordDict.size(); i++){

                //如果单词直接和传入的s相同，直接返回true
                if (wordDict.get(i).equals(s)){
                    return true;
                }


                int len = wordDict.get(i).length();



                if (wordDict.get(i).equals(s.substring(0,len))){

                    s=s.substring(len);

                }else {
                    continue;
                }

            }
        }

        return true;

    }

    //别想了，看动态规划吧
    public boolean wordBreak2(String s, List<String> wordDict){

        Set<String> set = new HashSet<>();
        boolean[] dp = new boolean[s.length() + 1];

        //先初始化数组的第一个值
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

}
