package 三角形最大周长976;

import java.util.Arrays;
import java.util.Comparator;

public class TheLongestTranangle {
}

//个人思路：这个应该就是用贪心算法，先倒序排序，然后找三个最大的边
class Solution {
    public int largestPerimeter(int[] A) {


        if (A.length < 3){
            return 0;
        }

        //先正序排序，一会再降序
        Arrays.sort(A);

        //这个题有点类似于三数之和,如果最大的那个数比次大的两个之和还大，就没有必要再去比较了
        int index = A.length-1;



        while (index > 1){

            if (index == 2){

                return A[index] >= A[index - 1] + A[index - 2]  ? 0 : A[index] + A[index - 1] + A[index - 2];
            }


            if (A[index] >= A[index - 1] + A[index - 2] && index > 2){
                index --;
                continue;
            }else {
                return A[index] + A[index - 1] + A[index - 2];
            }

        }

        return 0;

    }

    //每次自己的判断条件老写的不好
    public int largestPerimeter2(int[] A){
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; --i) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;

    }
}