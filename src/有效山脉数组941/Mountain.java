package 有效山脉数组941;

import java.security.interfaces.ECKey;

public class Mountain {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] A = {0,1,2,3,4,8,9,10,11,12,11};
        System.out.println(solution.validMountainArray(A));
    }



}

class Solution {

    //个人思路：要求数组先递增再递减，出现等于的情况也不是
    //如果出现只两边不对称的情况，要考虑停止问题
    public boolean validMountainArray(int[] A) {

        if (A.length < 3){
            return false;
        }

        if (A[1] < A[0] || A[A.length-2]< A[A.length-1]){
            return false;
        }

        //用双指针试试
        int start = 1;
        int end = A.length - 2;

        for (int i = 0; i <= A.length-1; i++){



            if (start>end){
                return true;
            }

            if (A[start] > A[start-1] && A[end] > A[end+1]){

                start++;
                end--;
                continue;

            }
            else if (A[start] > A[start-1] && A[end] < A[end+1]){
                start++;
                continue;
            }
            else if (A[start] < A[start-1] && A[end] > A[end+1]){
                end--;
                continue;
            }

            else {
                break;
            }

        }

        return false;

    }

    //自己的解法应该是有点蠢，看看官方的
    //采用线性扫描
    public boolean validMountainArray2(int[] A) {
        int N = A.length;
        int i = 0;

        //递增扫描
        while (i+1 < N && A[i] < A[i+1]){
            i++;
        }

        //最高点不能是数组第一个或者最后一个位置
        if (i == 0 || i == N - 1) {
            return false;
        }

        // 递减扫描
        while (i + 1 < N && A[i] > A[i + 1]) {
            i++;
        }

        return i == N - 1;


    }

    //还有一种解法是两边同时向中间靠拢，是否是一个山峰
    public boolean validMountainArray3(int[] A) {
        int len = A.length;
        int left = 0;
        int right = len - 1;
        //从左边往右边找，一直找到山峰为止
        while (left + 1 < len && A[left] < A[left + 1])
            left++;
        //从右边往左边找，一直找到山峰为止
        while (right > 0 && A[right - 1] > A[right])
            right--;
        //判断从左边和从右边找的山峰是不是同一个
        return left > 0 && right < len - 1 && left == right;
    }



}