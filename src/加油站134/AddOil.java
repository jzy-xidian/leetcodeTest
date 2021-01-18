package 加油站134;

public class AddOil {
}
class Solution {

    //题都没看懂，直接看答案吧
    public int canCompleteCircuit(int[] gas, int[] cost) {

        //加油站的总数
        int n = gas.length;
        int i = 0;

        //从头遍历每个加油站，并检查以该加油站为起点，能否行驶一周
        while (i < n){

            int sumOfGas = 0; //总共加的油
            int sumOfCost = 0; //总共消费的油

            int count = 0; //记录能走过几个站点
            while (count < n){ //退出循环的条件是走过所有站点

                int j = (i + count) % n; //加油站是环形的
                sumOfGas += gas[j]; //在j加油站可以获取的油
                sumOfCost += cost[j]; //开往下一个加油站消费的油

                if (sumOfCost > sumOfGas){
                    break;//这个站点开往下个站点的油不够
                }

                count++;//这个站点满足情况，开往下一个站点
            }

            if (count == n){

                //说明能够环绕一圈
                return i;

            }else {
                //当前站点不行的话从下一个站点开始检查
                i = i + count + 1;
            }

        }

        //所有站点都不满足情况
        return -1;
    }
}