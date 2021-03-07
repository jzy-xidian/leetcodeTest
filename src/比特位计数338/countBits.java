package 比特位计数338;

public class countBits {
}

class Solution {

    //需要把范围内的每个数转换成比特，然后存入数组
    public int[] countBits(int num) {

        //因为还有0的存在，所以要多加一位
        int[] result = new int[num+1];

        //对每个数进行二进制转换，统计得到1的个数，存入数组
        for (int i = 0; i <= num; i++){

            int caculate = i;

            int sum = 0;

            while (caculate != 0){

                int arr = caculate % 2;
                if (arr == 1){
                    sum++;
                }

                caculate /= 2;
            }

            result[i] = sum;
            sum = 0;

        }

        return result;

    }

    //时间复杂读过高。看下题解
    //动态规划---最高有效位
    public int[] countBits2(int num){
        int[] bits = new int[num + 1];
        int highBit = 0;
        for (int i = 1; i <= num; i++){
            //说明是偶数
            if ((i & (i-1)) == 0){
                //更新当前的最高有效位。
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }

        return bits;
    }

    //第三种方法是设置最低有效位
    //对于正整数 x，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，得到的数是原先的一半

    //如果是偶数，两倍关系下1的个数还是一样的。
    //如果是奇数。两倍关系下大的要比小的多1个
    public int[] countBits3(int num){
        int[] bits = new int[num + 1];
        for (int i = 1; i <= num; i++){

            //i/2可以由i >> 1得到
            bits[i] = bits[i >> 1] + (i % 2);
        }

        return bits;
    }


}