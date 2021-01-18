package 任务调度器621;

import java.util.*;

public class taskSchedule {
}
class Solution {

    //个人思路：统计重复的任务号，尝试插空？
    public int leastInterval(char[] tasks, int n) {


        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++){

            int num = map.getOrDefault(tasks[i],0);
            map.put(tasks[i],++i);
        }


        //如何取出不重复的任务？
        return 0;
    }

    public int leastInterval2(char[] tasks, int n) {

        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char ch : tasks) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        //任务总数
        // inextValidi
        //  表示其因冷却限制，最早可以执行的时间；resti
        //  表示其剩余执行次数
        //

        int m = freq.size();
        List<Integer> nextValid = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();

        Set<Map.Entry<Character,Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character,Integer> entry : entrySet){
            int value = entry.getValue();
            nextValid.add(1);
            //剩余任务次数
            rest.add(value);
        }

        int time = 0;

        for (int i = 0; i < tasks.length; ++i){
            //每个任务肯定都要加一次的
            ++time;
            int minNextVaild = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++){
                if (rest.get(j) != 0){
                    minNextVaild = Math.min(minNextVaild,nextValid.get(j));
                }
            }

            time = Math.max(time,minNextVaild);
            int best = -1;
            for (int j = 0; j < m; j++){
                if (rest.get(j) != 0 && nextValid.get(j) <= time){
                    if (best == -1 || rest.get(j) > rest.get(best)){
                        best = j;
                    }
                }
            }

            nextValid.set(best,time + n +1);
            rest.set(best, rest.get(best) -1);
        }

        return time;
    }

    //看下构造法
    public int leastInterval3(char[] tasks, int n){
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);


    }
}