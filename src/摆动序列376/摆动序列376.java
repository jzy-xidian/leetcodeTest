package 摆动序列376;

public class 摆动序列376 {
}
class Solution {
    public int wiggleMaxLength(int[] nums) {

        //需要存储上一次标志位
        int storeFlag = 0;

        //用于和上一次的标志位进行对比
        int testFlag = 0;

        if(nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return 1;
        }

        int sum = 0;

        if(nums[1] - nums[0] > 0){
            storeFlag = 1;
            sum = 2;
        } else if(nums[1] - nums[0] < 0){
            storeFlag = -1;
            sum = 2;
        }else{
            storeFlag = 0;
            sum = 1;
        }

        for(int i =1; i < nums.length-1; i++){

            if(nums[i+1] - nums[i] > 0){
                testFlag = 1;
                if(storeFlag ==-1){

                    if(sum != 0){
                        sum += 1;
                    }else{
                        if(sum <= 2){
                            sum = 2;
                        }

                    }

                }else if(storeFlag== 1){
                    if(sum <= 2){
                        sum = 2;
                    }
                }
                else{
                    if(sum < 2){
                        sum = 1;
                    }
                }
                storeFlag = testFlag;
            }else if(nums[i+1] - nums[i] < 0){
                testFlag = -1;
                if(storeFlag == -1){
                    if(sum <= 2){
                        sum = 2;
                    }


                }else if(storeFlag== 1){

                    if(sum != 0){
                        sum += 1;
                    }else{
                        if(sum <= 2){
                            sum = 2;
                        }

                    }
                }
                else{
                    if(sum < 2){
                        sum = 1;
                    }
                }
                storeFlag = testFlag;
            }else{
                testFlag = 0;

                if(sum < 2){
                    sum = 1;
                }

                storeFlag = testFlag;

            }
        }

        return sum;

    }

    //这个题自己理解的有问题，这个题要删除多余的过度元素，形成真正的摆动序列，也就是相邻的差正负交替

    //动态规划：
    public int wiggleMaxLength2(int[] nums){
        int n = nums.length;
        if (n < 2) {
            return n;
        }

        //计算上升和下降序列
        int[] up = new int[n];
        int[] down =new int[n];

        up[0] = down[0] = 1;

        //开始计算序列变化
        for (int  i = 1; i < n; i++){
            //上升
            if (nums[i] > nums[i-1]){

                //要保证两边的均衡
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];

            }
            else if (nums[i] < nums[i-1]){
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }

        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    //动态规划优化：
    public int wiggleMaxLength3(int[] nums){
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);


    }

    //贪心算法
    public int wiggleMaxLength4(int[] nums){
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;


    }

}