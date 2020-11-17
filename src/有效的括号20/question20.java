package 有效的括号20;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class question20 {
}

class Solution{

    //自己的思路：可以用栈的方式试一下
    //三种括号{ [ (，要么连续匹配，要么是回文的类型
    //采用map保存括号对应的关系，然后用栈的方法进行匹配
    public boolean isValid(String s){
        if(s == "" || s.length() == 0){
            return true;
        }

        if(s.length() %2 != 0){
            return false;
        }

        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            //这一步判断主要是针对左括号
            //只要是左括号就先入栈
            if (map.containsKey(c)){
                stack.push(c);
            }

            else {
                if(!stack.empty() && map.get(stack.peek()) == c){
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }

        return stack.isEmpty();


    }
}
