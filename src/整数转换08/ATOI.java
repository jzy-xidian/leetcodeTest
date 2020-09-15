package 整数转换08;

public class ATOI {

    public static void main(String[] args) {
        String s = "42";

        Solution solution = new Solution();

        System.out.println(solution.myAtoi(s));
    }

}

//自己的思路
class Solution {
    public int myAtoi(String str) {

        int i = 0;
        int sum = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (str.charAt(i) < '0' && str.charAt(i) > '9' && (str.charAt(i) != '-' || str.charAt(i) != '+')){
            return 0;
        }

        ++i;
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9' ){
            stringBuffer.append(str.charAt(i++));
        }

        //比较失误的一个地方：没有考虑到前导空格问题

        if (str.charAt(0) == '-'){

            i = 0;

            //这里有个很关键的点就是，sum是int型的，已经超过最大或最小值时，无法进行转换
            sum = - Integer.parseInt(stringBuffer.toString());

            if (sum < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }

            else {
                return sum;
            }

        }
        else {
            String s = str.charAt(0) + stringBuffer.toString();

            sum = Integer.parseInt(s);

            if (sum > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }

            else {
                return sum;
            }
        }
    }
}

//别人的解法
class Solution2 {
    public int myAtoi(String str) {

        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }

        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        }
        //Character.isDigit() 方法用于判断指定字符是否为数字。
        else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        //这种字符串问题最好先鉴别要判断的字符字符是否已经超过字符串长度，然后判断指定的字符是否为数字
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';

            //这里就是用来判断是否数组越界，以最后一位作为分界，最大或最小值对他减去然后除以十去判断即可
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                //这里很巧妙的一点就是
                //2147483647
                //-2147483648被默认为越界数组，大于等于这个数时，返回的就是这个，也不影响结果
                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative? -ans : ans;


    }
}