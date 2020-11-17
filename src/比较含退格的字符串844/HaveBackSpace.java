package 比较含退格的字符串844;

import java.util.Arrays;
import java.util.List;

public class HaveBackSpace {

    public static void main(String[] args) {
        String s = "ab##";
        String t = "c#d#";
        Solution solution = new Solution();
        System.out.println(solution.backspaceCompare(s,t));
    }
}

class Solution {
    //自己的思路：遇到#号，消除符号和前一个字符，#是第一个下标直接删除
    //写的过于麻烦，而且中间关于else if的使用不够熟练，String的用法也不够熟悉
    //看一下别人的解法
    public boolean backspaceCompare(String S, String T) {

          int countS = 0;
          int countT = 0;

          while (countS < S.length()){
              if (countS == 0 && S.charAt(countS) == '#'){
                  S = S.substring(1);
                  countS--;
              }

              else if (S.charAt(countS) == '#' && countS < S.length()-1){
                  S = S.substring(0,countS-1) + S.substring(countS +1);
                  countS = countS - 2;
              }
              else if (S.charAt(countS) == '#' && countS == S.length()-1){
                  S = S.substring(0,countS-1);
                  break;
              }


              countS ++;
          }

        while (countT < T.length()){
            if (countT == 0 && T.charAt(countT) == '#'){
                T = T.substring(1);
                countT--;
            }

            else if (T.charAt(countT) == '#' && countT < T.length()-1){
                T = T.substring(0,countT-1) + T.substring(countT +1);
                countT = countT - 2;
            }

            else if (T.charAt(countT) == '#' && countT == T.length()-1){
                T = T.substring(0,countT-1);
                break;
            }

            countT ++;
        }


        return S.equals(T);

    }

    //官方解法，第一种就是采用入栈出栈的思想，不是#就入，是#要弹出之前入的一个
    public boolean backspaceCompare2(String S, String T) {



        return build(S).equals(build(T));

    }
    public String build(String str){
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        for (int i=0; i< length; i++){
            char ch = str.charAt(i);
            if(ch != '#'){
                stringBuffer.append(ch);
            }else {
                //这里一定要先判断缓冲区的大小
                if(stringBuffer.length() > 0){
                    stringBuffer.deleteCharAt(stringBuffer.length()-1);
                }
            }
        }

        return stringBuffer.toString();
    }

    //采用双指针法
    //一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
    //这样，我们定义两个指针，分别指向两字符串的末尾。每次我们让两指针逆序地遍历两字符串，直到两字符串能够各自确定一个字符，
    // 然后将这两个字符进行比较。重复这一过程直到找到的两个字符不相等，或遍历完字符串为止。
    //这个方法空间复杂度较低，只需要一个指针和计数器


    //这个方法不太好
    public boolean backspaceCompare3(String S, String T) {

        int i = S.length() -1, j = T.length() - 1;
        //用来计算当前字符是否需要删除，如果不是0，就要删除
        //在不为0的情况下，遇到字符就删除，直到删为0，最后进行对比
        int skipS = 0, skipT = 0;


        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;



    }


}