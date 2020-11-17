package 颜色分类75;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ColorClassification {

    public static void main(String[] args) {

        int[] nums = {2,0,2,1,1,0};
    }

    //自己的思路
    //建立一个map，然后按顺序排序
    //这样做过于复杂
    public void sortColors(int[] nums) {

        int[] a = new int[nums.length];
        int[] b = new int[nums.length];

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){

            if (!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }

            else{
                int value = map.get(nums[i]);
                map.put(nums[i],++value);
            }
        }

        Set<Integer> set = map.keySet();

        Iterator<Integer> iterator = set.iterator();


        while (iterator.hasNext()){

        }


    }

    //题目理解错误，这里只有0，1，2，先采用单指针
    //先把0全部交换到前面，然后再用一个for循环把1交换到前面，最后剩下的就是2
    public void sortColors2(int[] nums) {
        int n = nums.length;
        int move = 0;

        for (int i = 0; i < n; i++){
            if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[move];
                nums[move] = temp;
                move++;
            }
        }

        //这一部分是从move开始，继续存1，把1存完，剩下的2全部已经交换到末尾
        for (int i = move; i < n; i++){
            if(nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[move];
                nums[move] = temp;
                move++;
            }
        }

    }


    //用双指针法可以只需要一次遍历
    public void sortColors3(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;

        for (int i = 0; i < n; i++){
            if (nums[i] == 1){
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            }
            else if (nums[i] == 0){
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                //如果p0指针比p1还小，
                if(p0 < p1){
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;

                }
                ++p0;
                ++p1;
            }
        }



    }

    //计数排序的两趟扫描算法
    public void sortColors4(int[] nums) {
        int c0 = 0, c1 = 0, c2 = 0;
        for(int i=0; i<nums.length; i++){ //第一趟算出0，1，2出现的次数
            if(nums[i] == 0) c0++;
            else if(nums[i]==1) c1++;
            else if(nums[i]==2) c2++;
        }
        for(int j=0; j<nums.length; j++){ //第二趟按照0，1，2出现的次数 依次重写数组
            if(j < c0) nums[j]=0;
            else if(j < c1+c0) nums[j]=1;
            else if(j < c2+c1+c0) nums[j] =2;
        }
    }

}
