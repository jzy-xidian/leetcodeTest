package 将数组拆分成斐波那契序列842;

import java.util.ArrayList;
import java.util.List;

public class ArraySplitedToFibonacci {
}

class Solution {
    //按我个人的理解，应该就算把字符串拆成三段，前两个数的和要等于第三个数
    public List<Integer> splitIntoFibonacci(String S) {

        //第一个数从一位开始，后面的数要预留足够的位数，不然没法相加

        //这个题是一个经典的回溯问题
        //一层一层向下去比对，满足条件的时候打破，否则一直遍历

        //用来存储满足条件的三个数
        List<Integer> res = new ArrayList<>();

        backtrack(S.toCharArray(), res, 0);
        return res;


    }
    //一般要定义回溯的函数
    public boolean backtrack(char[] digit, List<Integer> res, int index) {

        //边界条件判断，如果截取结束，并且res长度大于等于3，表示找到了一个组合
        if (index == digit.length && res.size() >= 3){
            return true;
        }

        for (int i = index; i < digit.length; i++){
            //两位以上的数字不能以0开头
            if (digit[index] == '0' && i > index){
                break;
            }
            //截取字符串转换为数字
            long num = subDigit(digit,index, i + 1);
            //如果截取的数字大于int最大值，终止截取
            if (num > Integer.MAX_VALUE){
                break;
            }
            int size = res.size();
            //如果截取的数字大于res中前两个数字的和，说明这次截取的太大，直接终止，因为后面越截取越大
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)){
                break;
            }
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)){
                //把数字num添加到集合res中
                res.add((int) num);
                //如果找到了就直接返回
                if (backtrack(digit, res, i + 1))
                    return true;
                //如果没找到，就会走回溯这一步，然后把上一步添加到集合res中的数字给移除掉
                res.remove(res.size() - 1);

            }
        }

        return false;

    }
    //相当于截取字符串S中的子串然后转换为十进制数字
    private long subDigit(char[] digit, int start, int end) {
        long res = 0;
        for (int i = start; i < end; i++) {
            res = res * 10 + digit[i] - '0';
        }
        return res;
    }


}