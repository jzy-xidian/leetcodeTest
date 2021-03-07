package 斐波那契数509;

public class Fibnaci {
}

class Solution {
    public int fib(int n) {

        if (n == 0){
            return 0;
        }

        if (n == 1){
            return 1;
        }

        return fib(n-1) + fib(n-2);

    }

    //用递归时间复杂度直接炸掉，这里参考一下题解的动态规划
    //采用状态转移方程，边界条件就是F(0),F(1)
    public int fib2(int n){

        if (n < 2){
            return n;
        }

        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++){
            p = q; //用来转移状态的
            q = r;
            r = p + q;
        }

        return r;

    }

}
