package 括号生成22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheParenthesis {
}

class Solution {

    //个人思路：之前的括号匹配问题采用的是入栈出栈的方法，这次是要生成多种组合
    //先考虑左括号和右括号的对应关系

    //实在没看出来，看看解析吧

    //先看了看一个大佬的递归解法

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        if(n <= 0){
            return res;
        }
        //因为左右括号是对半出现的
        getParenthesis("",n,n);
        return res;
    }


    private void getParenthesis(String str,int left, int right) {
        if(left == 0 && right == 0 ){
            res.add(str);
            return;
        }
        if(left == right){
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str+"(",left-1,right);
        }else if(left < right){
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if(left > 0){
                getParenthesis(str+"(",left-1,right);
            }
            getParenthesis(str+")",left,right-1);
        }
    }


    //官方题解：先是采用暴力法，检查每个是否有效即可

    //为了检查序列是否有效，我们遍历这个序列，并使用一个变量 balance 表示左括号的数量减去右括号的数量。
    // 如果在遍历过程中 balance 的值小于零，或者结束时 balance 的值不为零，那么该序列就是无效的，
    // 否则它是有效的。
    //

    //说实话，感觉这里有点问题，就是左括号应该是先行出现的，但是这里的有效判断并不能满足这个条件
    public List<String> generateParenthesis2(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current,int pos,List<String> result){

        if(pos == current.length){
            //在平衡的情况下
            if (valid(current)) {
                result.add(new String(current));
            }

        }else {
            current[pos] = '(';
            generateAll(current,pos+1,result);
            current[pos] = ')';
            generateAll(current,pos+1,result);
        }

    }

    public boolean valid(char[] current){

        int balance = 0;

        for (char c : current){

            if (c == '('){
                ++balance;
            }else {
                --balance;
            }
            //当左括号的数量小于右括号的数量的时候，说明不符合标准
            if (balance < 0){
                return false;
            }
        }

        //计算左右括号数量是否达到平衡
        return balance ==0;
    }


}
