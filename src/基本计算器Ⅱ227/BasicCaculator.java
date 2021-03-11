package 基本计算器Ⅱ227;

import java.util.Deque;
import java.util.LinkedList;

public class BasicCaculator {
}
class Solution {
    public int calculate(String s) {

        //如果是 * 和 / 优先级更高，先处理 * 和 /，再处理 + 和 -
        //还是用栈来做
        //加号：将数字压入栈；
        //减号：将数字的相反数压入栈；
        //乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果。
        //遍历字符串 s，并用变量 preSign 记录每个数字之前的运算符，对于第一个数字，
        // 其之前的运算符视为加号。每次遍历到数字末尾时，根据 preSign 来决定计算方式：

        Deque<Integer> stack = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();

        for (int i = 0; i < n; i++){
            if (Character.isDigit(s.charAt(i))){
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1){
                switch (preSign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }

                //更新preSign的状态
                preSign = s.charAt(i);
                num = 0;
            }
        }

        int ans = 0;
        while (!stack.isEmpty()){
            ans += stack.pop();
        }

        return ans;

    }
}