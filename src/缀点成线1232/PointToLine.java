package 缀点成线1232;

public class PointToLine {
}

class Solution {

    //纵坐标差和横坐标差比例就是判断条件，只要不满足，直接返回false

    //要考虑一下全在竖线或者全在横线的情况下
    public boolean checkStraightLine(int[][] coordinates) {

        double rate = 0.0;

        boolean flag = true;


        for (int i = 0; i < coordinates.length - 1; i++){


                int x = coordinates[i+1][0] - coordinates[i][0];
                int y = coordinates[i+1][1] - coordinates[i][1];

                if (rate == 0.0){
                    rate = (double)y / x;
                }else {

                    if (rate == (double)y / x)  {
                        continue;
                    }else {
                        return false;
                    }

                }

        }

        return true;


    }

    //要考虑一下全在竖线或者全在横线的情况下
    //把第一个点移动到原点，设其方程为 Ax+By=0
    public boolean checkStraightLine2(int[][] coordinates){

        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];

        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }

        int A = coordinates[1][1], B = -coordinates[1][0];

        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }

        return true;

    }
}