package 整数反转07;

import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class IntegerReserve {

    public static void main(String[] args) {
        int x = 325954;

        System.out.println(Integer.MAX_VALUE);

        System.out.println(Integer.MIN_VALUE);

        Solution solution = new Solution();

        System.out.println(solution.reverse(x));
    }


}


//自己写的方法，因为没有限制数字范围，导致出错
//这个题一定要考虑越界问题
class Solution {
    public int reverse(int x) {
        int length = 0;

        if (x == 0){
            return 0;
        }
        int k = x;
        if(x < 0){
            x = -x;
        }
        int sum = 0;

        ArrayList<Integer> arrayList = new ArrayList<>();

        while (x!=0){
                arrayList.add(x % 10);
                x /= 10;
                length ++ ;
        }

        int len = 0;

        while(arrayList.get(len) == 0){


            len ++;
        }



        while (len < length){

            sum = sum * 10 + arrayList.get(len);
            len++;
        }


        if (k >= 0) {
            return sum;
        }

        else {
            return -sum;
        }


    }
}

//官方题解
//2147483647
//-2147483648
//最大的值与最小的值为：[−2^31, 2^31 − 1]， 即：[-2147483648, 2147483647]
class Solution2 {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            //最后一位用于判断是否超出范围
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}





