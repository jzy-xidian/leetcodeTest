package 整数转罗马数字12;

import java.util.ArrayList;

public class IntToRoma {

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();

        int num = 3265;

        System.out.println(solution2.intToRoman(num));
    }


}


//自己的思路：要先找出罗马数字的规律
//个人感觉应当先对给定范围的数字进行拆分，千位，百位，十位，个位，然后进行拼接，模式匹配
class Solution {
    public String intToRoman(int num) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();

        while (num > 0){

            arrayList.add(num % 10);

            num /= 10;
        }

        String one = null;
        String five = null;
        //用于对1和5的代表字母变换
        int sum = 0;

        //这里采用此swich case 匹配
        for(int i = arrayList.size() - 1; i >=0; i--){

            //题目中都是按1和五来取，可以按照次数来进行匹配
            switch (sum){
                case 0:
                    one = "M";
                    break;
                case 1:
                    one = "C";
                    five = "D";
                    break;
                case 2:
                    one = "X";
                    five = "L";
                    break;
                case 3:
                    one = "I";
                    five = "V";
                    break;
            }

            //思路到这一步截止。。。。。。。。。。。。


        }

        return null;

    }
}

//看答案了。。。。、
//我们用来决定的规则是从左到右选择尽可能大的符号表示
//自己对题目的理解没有问题，按从高到低的位数进行拆分，取较大的表示字符
//使用贪心算法是有意义的。贪心算法是一种在当前时间做出最佳可能决策的算法；在这种情况下，它会取出最大可能的符号


//例如，假设我们需要将数字设为 671。
//
//671 中最大的符号是 D（值 500）。
//
//
//Roman Numeral so far: D
//Integer remainder: 671 - 500 = 171
//我们在 171 的基础重复以上步骤，最大的符号是 C（值 100）。
//
//
//Roman Numeral so far: DC
//Integer remainder: 171 - 100 = 71
//在 71 的基础重复以上步骤，最大的符号是 L （值 50）。
//
//
//Roman Numeral so far: DCL
//Integer remainder: 71 - 50 = 21
//在 21 的基础重复以上步骤，最大的符号是 X （值 10）。
//
//
//Roman Numeral so far: DCLX
//Integer remainder: 21 - 10 = 11
//在 11 的基础重复以上步骤，最大的符号是 X （值 10）。
//
//
//Roman Numeral so far: DCLXX
//Integer remainder: 11 - 10 = 1
//最后，用 I 表示 1，完成转换。
//
//
//Roman Numeral so far: DCLXXI
//Integer remainder: 1 - 1 = 0
//
class Solution2 {
    public String intToRoman(int num) {

        //直接将特殊值取出，创建一个对应的符号数组，让数值与字母一一对应
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder stringBuilder = new StringBuilder();

        //一种从大到小取找的思想
        for (int i = 0; i < values.length && num >=0; i++){

            while (values[i] <= num){
                num -= values[i];
                stringBuilder.append(symbols[i]);
            }
        }

        return stringBuilder.toString();

    }
}
