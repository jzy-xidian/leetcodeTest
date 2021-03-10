package 基本计算器224;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.util.*;

public class BasicCaculator {

    public static void main(String[] args) {

        Deque<Integer> ops = new LinkedList<>();

        ops.push(1);
        ops.push(2);
        ops.push(3);
        System.out.println(ops);
        System.out.println(ops.peek());
        System.out.println(ops.poll());
        System.out.println(ops.pop());
        System.out.println(ops.remove());

        ops.addFirst(1);
        ops.addFirst(2);
        ops.addFirst(3);

        System.out.println(ops.peekFirst());




    }
}

//要考虑运算符的优先级，还要考虑括号匹配，典型的数据结构计算器压栈弹栈问题
class Solution {
    public int calculate(String s) {

        //采用双端队列
        Deque<Integer> ops = new LinkedList<>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;

        while (i < n){
            if (s.charAt(i) == ' '){
                i++;
            }
            else if (s.charAt(i) == '+'){
                sign = ops.peek();
                i++;
            }else if (s.charAt(i) == '-'){
                sign = -ops.peek();
                i++;
            }else if (s.charAt(i) == '('){
                ops.push(sign);
                i++;
            }else if (s.charAt(i) == ')'){
                ops.pop();
                i++;
            }else {
                long num = 0;
                //考虑多位数的情况
                //Character.isDigit() 方法用于判断指定字符是否为数字
                while (i < n && Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret  += sign * num;
            }
        }

        return ret;


    }

    //还是根据括号匹配来确定吧
    public int calculate2(String s){
        //记录式子
        List<String> sums = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();//记录左括号开始位置

        int num = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == ' '){
                continue;
            }

            if (Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }
            else if(c == '+' || c == '-'){

                sums.add(String.valueOf(num));
                sums.add(String.valueOf(c));

                num = 0;
            }
            else if (c == '('){

                //左括号开始的位置，为了计算形成的式子中要消费的起始位置
                stack.push(sums.size());
            }else if(c == ')'){
                //遇到右括号就把这段结果算出来，把这段值清掉，换成括号计算的结果
                sums.add(String.valueOf(num));
                num = 0;

                //最后一个左括号的位置，要和第一个右括号进行匹配，消费掉整个括号内的计算结果
                int pos = stack.pop();

                int val = getVal(pos,sums.size() - 1, sums);

                //清除sums中的式子
                for (int j = sums.size() - 1; j >= pos; j--){
                    sums.remove(j);
                }

                sums.add(String.valueOf(val));

            }

        }

        sums.add(String.valueOf(num));
        //最后没括号了，从左到右算出来
        return getVal(0,sums.size()-1,sums);
    }

    private int getVal(int start, int end, List<String> sums){
        //一个数就不用算了
        if(sums.size() == 1){
            return Integer.valueOf(sums.get(0));
        }

        int val = 0;
        int sign = 1;//默认正，无符号就是正

        for(int j = start; j <=end; j++){
            if("+".equals(sums.get(j))){
                sign = 1;
            }else if("-".equals(sums.get(j))){
                sign = -1;
            }else{
                //a-b=a+(-b)
                int temp = Integer.valueOf(sums.get(j));

                //数值计算
                val += (sign*temp);
            }
        }
        return val;
    }

    //双栈 java版
    public int calculate3(String s){
        Deque<Integer> num_stack=new ArrayDeque<>();
        Deque<Character> op_stack=new ArrayDeque<>();
        s='('+s+')';
        int num=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                op_stack.push(s.charAt(i));
            }else if(s.charAt(i)=='+'||s.charAt(i)=='-'){
                op_stack.push(s.charAt(i));
                num_stack.push(num);
                num=0;
            }else if(Character.isDigit(s.charAt(i))){
                num=num*10+s.charAt(i)-'0';
            }else if(s.charAt(i)==')'){
                int res=0;
                num_stack.push(num);
                while(op_stack.peekFirst()!='('){
                    char op=op_stack.pop();
                    int n=num_stack.pop();
                    if(op=='+'){
                        res+=n;
                    }else if(op=='-'){
                        res-=n;
                    }
                }
                res+=num_stack.pop();
                num=res;
                op_stack.poll();
            }
        }
        return num;
    }
}