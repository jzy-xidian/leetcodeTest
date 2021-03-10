package 可以被5整除的二进制前缀1018;

import java.util.ArrayList;
import java.util.List;

public class Deletefive {

    public static void main(String[] args) {
        int[] A = new int[]{0,1,1,1,1,1};
        Solution solution = new Solution();
        System.out.println(solution.prefixesDivBy5(A).toString());
    }


}
class Solution {
    public List<Boolean> prefixesDivBy5(int[] A) {

        List<Boolean> result= new ArrayList<>();


        for (int i = 0; i < A.length; i++){
            int sum = 0;
            int calen = 0;

            for (int j = i; j >=0; j--){

                sum += A[j]*Math.pow(2,calen++);


            }
            System.out.println(sum);
            if (sum % 5 == 0){
                result.add(true);
            }else {
                result.add(false);
            }

        }

        return result;
    }

    //大数的时候还是有问题，重新看下题解

    //考虑到数组 A 可能很长，如果每次都保留 Ni的值，则可能导致溢出。由于只需要知道每个 Ni是否可以被 5 整除，因此在计算过程中只需要保留余数即可

    public List<Boolean> prefixesDivBy5s(int[] A){
        List<Boolean> answer = new ArrayList<Boolean>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            answer.add(prefix == 0);
        }
        return answer;


    }
}