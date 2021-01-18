package 移动零283;

public class MoveZeros {
}
class Solution {
    //个人思路：直接把0后移
    //必须在原数组上操作，不能拷贝额外的数组。
    public void moveZeroes(int[] nums) {

        for (int i = 0; i < nums.length-1; i++){

            for (int j = i + 1; j < nums.length; j++){

                if (nums[i] == 0 && nums[j] != 0){

                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;

                }

            }

        }
    }

    //覆盖法
    public void moveZeroes2(int[] nums) {

        int len = nums.length;
        int index = 0;
        for(int i = 0; i < len; i ++) {
            if(nums[i] != 0) {
                nums[index ++] = nums[i];
            }
        }
        while(index < len) {
            nums[index ++] = 0;
        }
    }

    //双指针

    //效率更高一些
    public void moveZeroes3(int[] nums) {
        int i=0;//找0
        int j=0;//找非0
        while(j<nums.length){
            if(nums[j]!=0){
                int t=nums[j];
                nums[j]=nums[i];
                nums[i]=t;
                i++;
            }
            j++;
        }
    }

}
