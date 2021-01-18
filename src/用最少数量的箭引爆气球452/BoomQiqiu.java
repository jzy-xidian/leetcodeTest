package 用最少数量的箭引爆气球452;

import java.util.Arrays;
import java.util.Comparator;

public class BoomQiqiu {

    //做一个单元测试
    public static void main(String[] args) {
        //[[-2147483646,-2147483645],[2147483646,2147483647]]

        int[][] arr = {{-2147483646,-2147483645},{2147483646,2147483647}};

        Solution solution = new Solution();



        System.out.println(solution.findMinArrowShots(arr));

    }

}
class Solution {

    //个人思路：其实就是在取交集
    public int findMinArrowShots(int[][] points) {

        if (points.length == 0 || points == null){
            return 0;
        }

        //应该先进行排序，然后再取，这里采用从小到大的顺序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {

                if(a[0]==b[0]){
                    if (a[1] > b [1]){
                        return 1;
                    }
                    if (a[1] == b[1]){
                        return 0;
                    }else{
                        return -1;
                    }

                }else {
                    if (a[0] > b [0]){
                        return 1;
                    }
                    if (a[0] == b[0]){
                        return 0;
                    }else{
                        return -1;
                    }
                }
            }
        });

        int start = points[0][1];
        int count = 1;

        for (int i = 1; i < points.length; i++){

            if (start >= points[i][0]){

                if(points[i][1] < start){
                    start = points[i][1];
                }
                continue;
            }
            else {


                    count++;

                start = points[i][1];
            }

        }

        return count;


    }
    //用这种方法会忽略一种情况就是如果一个大的集合包含两个不相交的子集，这样必须有两次而不是一次
    //虽然最后还是解决了，但是方法并不是特别好，看看官方解法吧

    //一个是溢出问题，一个是交集问题
    public int findMinArrowShots2(int[][] points) {

        //官方采用的排序是先找到最小的有边界，然后一一对比剩下区间的左边是否能够重合，进行比对
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] point1, int[] point2) {
                if (point1[1] > point2[1]) {
                    return 1;
                } else if (point1[1] < point2[1]) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon: points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;

    }
}