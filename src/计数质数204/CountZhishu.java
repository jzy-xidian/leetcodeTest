package 计数质数204;

import java.util.Arrays;

public class CountZhishu {
}


class Solution {

    //统计每个数里面含有的质数，然后进行累加就行
    public int countPrimes(int n) {

        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;





    }

    private boolean isPrime(int num) {
        int max = (int)Math.sqrt(num);
        for (int i = 2; i <= max; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        // 从 2 开始枚举到 sqrt(n)。
        for (int i = 2; i * i < n; i++) {
            // 如果当前是素数
            if (isPrim[i]) {
                // 就把从 i*i 开始，i 的所有倍数都设置为 false。
                for (int j = i * i; j < n; j+=i) {
                    isPrim[j] = false;
                }
            }
        }

        // 计数
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                cnt++;
            }
        }
        return cnt;
    }










}