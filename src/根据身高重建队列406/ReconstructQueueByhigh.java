package 根据身高重建队列406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ReconstructQueueByhigh {
}

class Solution {

    //个人思路：（h，k）的模式，h前面必须有k个大于等于h的身高
    //先找出最大的
    public int[][] reconstructQueue(int[][] people) {

        //先按照二维数组的第一位数排，然后按第二位数排
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                } else {
                    return person2[1] - person1[1];
                }
            }
        });


        //排序结束，但是后面的获取逻辑好像一直有点问题

        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;


    }

    //看看官方题解，第一步思路没有问题
    //第二种就是倒序排序
    public int[][] reconstructQueue2(int[][] people){
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });

        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            //我们可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。
            //插入一个会把其他的往后面挤
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);


    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0,1);
        list.add(0,2);
        list.add(0,3);
        list.add(1,6);
        list.add(2,8);
        list.add(5,4);
        System.out.println(list.toString());
    }
}