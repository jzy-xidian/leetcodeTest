package 罗马数字转整数13;

public class RomaNumToInt {

}

//给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
class Solution {

    //自己的思路
    //按照之前贪心算法，整数转罗马数字的情况是，将罗马数字代表的字母存在一个数组内，创建另一个数组表示对应的值，然后用对应的整数去减，然后进行字符串拼接
    //这次是罗马转整数，可以统计一个字母出现的次数，然后获取字母对应的值，但是这里要注意特殊值
    public int romanToInt(String s) {
        //直接将特殊值取出，创建一个对应的符号数组，让数值与字母一一对应
//        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
//        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

//        按照题目的描述，可以总结如下规则：
//
//        罗马数字由 I,V,X,L,C,D,M 构成；
//        当小值在大值的左边，则减小值，如 IV=5-1=4；
//        当小值在大值的右边，则加小值，如 VI=5+1=6；
//        由上可知，右值永远为正，因此最后一位必然为正。

        //思路的关键：一个小值放在大值的左边，那就是减法，如果放在右边就是加法，且最多出现三次，像4，9，40这种数值，不能把一个符号出现四次来表示

        //在代码实现上，可以往后看多一位，对比当前位与后一位的大小关系，从而确定当前位是加还是减法。当没有下一位时，做加法即可。
        //
        //也可保留当前位的值，当遍历到下一位的时，对比保留值与遍历位的大小关系，再确定保留值为加还是减。最后一位做加法即可。
        //这次转换用通过swich更好一些
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++){
            int num = getValue(s.charAt(i));
            if(preNum < num){
                sum -= preNum;
            }else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;




        return sum;
    }
    private int getValue(char ch){
        switch (ch){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;

        }
    }
}
