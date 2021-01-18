package 单调递增的数字738;

public class AddNumber {
}
class Solution {
    //个人思路：要保证从高位到低位，数字是依次递增的
    //如果是完全递减的，必须从首位就开始降低，应该是从递增的一项开始计算
    public int monotoneIncreasingDigits(int N) {

        //这个题标准的贪心算法
        //长度为n的情况下，最大单调递增一定是每位都9
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i-1] <= strN[i]){

            i +=1;

        }

        if (i < strN.length){

            while (i > 0 && strN[i-1] > strN[i]){
                strN[i-1] -= 1;
                i -= 1;
            }

            for (i +=1; i < strN.length; i++){
                strN[i] = '9';
            }
        }

        return Integer.parseInt(new String(strN));

    }
}