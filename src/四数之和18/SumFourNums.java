package 四数之和18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumFourNums {

    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}

class Solution{
    //这个题和三数之和有点类似，难度应该是介于三数之和与最接近的三数之和之间
    //感觉就是在三数之和外层加一个循环判定条件
    //思想应该是对的，但是去重好像出了点问题

    //感觉这种题的关键还是在于去重

    public List<List<Integer>> fourSum(int[] nums,int target){

        int length = nums.length;
        int[] arr = Arrays.copyOf(nums,length);

        List<List<Integer>> lists = new ArrayList<>();

        int sum = 0;

        Arrays.sort(arr);

        for(int i = 0; i <length - 3; i++){

            if( i > 0 && arr[i] == arr[i-1]){
                continue;
            }

            //这个循环用的是三数之和的部分
            for(int j = i+1; j < length; j++){

                if(j > i+1 && arr[j] == arr[j-1]){
                    continue;
                }

                //作为左指针
                int L = j + 1;
                //作为右指针
                int R = length - 1;

                while (L < R){

                    sum = arr[i] + arr[j] + arr[L] + arr[R];

                    if (sum ==target){
                        lists.add(Arrays.asList(arr[i],arr[j],arr[L],arr[R]));

                        while (L < R && nums[L] == nums[L+1])
                            L++;//去重
                        while (L <R && nums[R] == nums[R-1])
                            R--; // 去重
                        L++;
                        R--;

                    }

                    else if(sum < target)
                        L++;
                    else if(sum > target)
                        R--;


                }

            }

        }

        return lists;

    }

    //看下官方的去重方案
    //其实官方和我的思路也大致一样
    //关键就在于中间那段的重复判断，才是此题的难点！

    public List<List<Integer>> fourSum2(int[] nums,int target){
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;

        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            //这两个新加的判断才是关键！！！

            //如果前四个书已经比目标大了，就没必要再比下去了
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            //如果当前坐标和末尾三个数进行比较，如果小于目标，可以继续向后推进了
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }

            for (int j = i + 1; j < length - 2; j++) {

                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                //这两个判断也是同理
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }

                //然后就是三数之和的写法
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;


    }
}
