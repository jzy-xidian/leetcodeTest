package 可获得的最大点数1423;

import java.util.Arrays;

public class GetMaxPoint {
}
class Solution {

    //采用双指针试试
    //采用滑动窗口，因为只能拿K个点，那么剩下的就算n-k个点，值最小的时候就是手中点最大的情况
    public int maxScore(int[] cardPoints, int k) {

        int n = cardPoints.length;

        int window = n - k;

        //选前n-k个作为初始值；
        int sum = 0;

        for (int i = 0 ; i < window; i++){
            sum += cardPoints[i];
        }

        int minSum = sum;

        for (int i = window; i < n; i++){

            //右边进来一个，左边出去一个
            sum += cardPoints[i] - cardPoints[i - window];

            minSum = Math.min(minSum,sum);
        }

        return Arrays.stream(cardPoints).sum() - minSum;

    }
}