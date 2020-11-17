package 按奇偶排序数组922;

import java.util.ArrayList;
import java.util.List;

public class SortwithJiO {
}
class Solution {

    //自己的思路：直接打乱重排，交替存入数组即可
    //这方法可真是太爽了
    public int[] sortArrayByParityII(int[] A) {

        //存偶数
        List<Integer> list1 = new ArrayList<>();
        //存奇数
        List<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < A.length; i++){

            if (A[i] %2 == 0){
                list1.add(A[i]);
            }else {
                list2.add(A[i]);
            }
        }

        for (int j = 0; j < A.length;j++){
            if (j % 2 == 0){

                A[j] = list1.get(0);
                list1.remove(0);

            }else {
                A[j] = list2.get(0);
                list2.remove(0);
            }
        }

        return A;
    }

    //效率有点低，看看别人的解法，这个算是双指针法
    //直接跨间隔存储就行
    public int[] sortArrayByParityII2(int[] A){
        int[] ans = new int[A.length];
        int evenIdx = 0;
        int oddIdx = 1;
        for (int i = 0; i < A.length; i++) {
            if ((A[i] % 2) == 1) {
                ans[oddIdx] = A[i];
                oddIdx += 2;
            } else {
                ans[evenIdx] = A[i];
                evenIdx += 2;
            }
        }
        return ans;
    }

    //双指针交换法
    public static int[] SortArrayByParityII3(int[] A) {

        int a = 0, b = 1;   //a偶数指针,b奇数指针;
        int length = A.length;

        while (a < length && b < length) {
            if (A[a] % 2 != 0 && A[b] % 2 == 0) {
                int temp = A[a];
                A[a] = A[b];
                A[b] = temp;
            } else {
                if (A[a] % 2 == 0) a += 2;
                if (A[b] % 2 != 0) b += 2;
            }
        }

        return A;
    }

}