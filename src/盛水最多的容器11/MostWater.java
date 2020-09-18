package 盛水最多的容器11;

import org.w3c.dom.NamedNodeMap;

public class MostWater {

    public static void main(String[] args) {

        int[] arr = {1,8,6,2,5,4,8,3,7};

        Solution2 solution = new Solution2();
        System.out.println(solution.maxArea(arr));
    }
}

//自己的思路，依次遍历，计算面积
//O(n^2),效率不够高
class Solution {
    public int maxArea(int[] height) {

        int maxArea = 0;

        //对比两个数字的最小值，短板效应
        int minHeight = 0;

        for(int i = 0; i < height.length - 1; i++){
            for(int j = i + 1; j < height.length; j++){
                minHeight = Math.min(height[i],height[j]);

                maxArea = Math.max(maxArea,minHeight * (j-i));
            }
        }

        return maxArea;
    }
}

//采用双指针法
//从开始和结尾计算，每次对比，去掉比较下的那个边，不断向中间推进
class Solution2 {
    public int maxArea(int[] height) {

        int max = 0;
        int start = 0,end = height.length - 1;
        while (start < end){

            int result = Math.min(height[start],height[end]) * (end - start);

            if ((height[start] <= height[end])){
                start++;
            }
            else {
                end--;
            }

            max = Math.max(result, max);
        }

        return max;

    }
}