package 二进制手表401;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryWatch {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.readBinaryWatch(10).toString());
    }
}
class Solution {

    private List<String> res = new LinkedList<>();

    //二进制的情况，通过不同的排列组合，可以有多种情况，总共是 0 --- 10种情况，每种亮灯情况分别讨论
    //r(n)=r(n−1)+w,w 代表从 numsnums 中选出一个数字。
    //采用递归回溯
    public List<String> readBinaryWatch(int num) {

        res.clear();
        dfs(num, 0, 0, 1, 1, new LinkedList<Integer>(), new LinkedList<Integer>());
        return res;


    }

    // h：小时数，m：分钟数
    // hstart、mstart：从hstart、mstart开始继续遍历小时和分钟
    // hours：选中的小时，minutes：选中的分钟

    private void dfs(int num, int h, int m, int hstart, int mstart, LinkedList<Integer> hours, LinkedList<Integer> minutes){

        if (hours.size() + minutes.size() == num){
            if (h < 12 && m < 60){

                //都先从0 开始
                res.add(String.format("%d:%02d",h,m));

                return;

            }

        }

        //i: 1  2 4 8
        for (int i = hstart; i <= 8; i<<=1){
            hours.addLast(i);
            dfs(num,h+i,m, i << 1, mstart,hours,minutes);
            hours.removeLast();
        }

        for (int i = mstart; i <= 32; i <<= 1) {
            minutes.addLast(i);
            dfs(num, h, m + i, 16, i << 1, hours, minutes); // hstart 直接设置为16（>=12)，避免重复计算
            minutes.removeLast();
        }

    }



}

//Integer.bitCount() 法
class Solution2 {

    public List<String>readBinaryWatch(int num){
        List<String> times = new ArrayList<>();
        for(int h=0;h<12;h++){
            for(int m=0;m<60;m++){
                //因为是二进制表，所以1的个数就可以看成亮的灯
                if(Integer.bitCount(h)+Integer.bitCount(m)==num)
                    times.add(String.format("%d:%02d",h,m));
            }
        }
        return times;
    }


}