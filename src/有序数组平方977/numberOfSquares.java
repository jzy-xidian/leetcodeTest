package 有序数组平方977;

import javafx.geometry.Pos;

import java.util.Arrays;

public class numberOfSquares {
}
class Solution {
    //自己的思路：两个for循环
    //效率太低，看看别人的解法
    public int[] sortedSquares(int[] A) {
        for(int i = 0; i < A.length; i++){
            A[i] = A[i]*A[i];
        }

        for(int i = 0; i < A.length; i++){
            for(int j = 1; j < A.length - i; j++){
                int temp;
                if(A[j-1] > A[j]){
                    temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
                }

            }
        }

        //或者直接排序
//        int[] ans = new int[A.length];
//        for (int i = 0; i < A.length; ++i) {
//            ans[i] = A[i] * A[i];
//        }
//        Arrays.sort(ans);
//        return ans;



        return A;


    }

    //另一种解法就是，已经有序，用双指针，新建数组进行排序
    public int[] sortedSquares2(int[] A) {
        int length = A.length;
        int[] arr = new int[length];
        int index = length - 1;
        for (int i = 0,j = length -1; i < j;){

            if(A[i] * A[i] > A[j] * A[j]){
                arr[index] = A[i] * A[i];
                ++i;

            }else {
                arr[index] = A[j] * A[j];
                --j;
            }

            --index;
        }

        return arr;


    }
}