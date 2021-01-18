package 旋转数组189;

public class reserveArray {
}
class Solution {

    //最普通的写法就是直接提取后K位重新赋值
    public void rotate(int[] nums, int k) {

        int n = nums.length;
        int[] newArray = new int[n];

        for (int i =0; i < n; i++){
            newArray[(i+k) % n] = nums[i];
        }

        System.arraycopy(newArray,0,nums,0,n);
    }


    //数组反转的话可以保证O（1）不用额外开辟空间
    //先整体反转，然后再截取K，分别反转
    public void rotate2(int[] nums, int k){

        k %= nums.length;
        reserve(nums,0,nums.length-1);
        reserve(nums,0,k-1);
        reserve(nums,k,nums.length-1);


    }

    public void reserve(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}