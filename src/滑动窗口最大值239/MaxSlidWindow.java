package 滑动窗口最大值239;

import java.util.*;

public class MaxSlidWindow {
}
class Solution {

    //不能每进去一次就排序一次，复杂度太高，而且要考虑滑动窗口的大小，
    //考虑采用队列，核心的解决点就是对窗口内的数组取最大值，用max比较试试

    //根据题解的意思，可以采用优先队列，也就是大根堆，每次把数组的k个元素放入优先队列，此时堆顶就是最大元素，出现在滑动窗口最左侧
    //但这里有个问题就是下次滑动，不能改变原先数组的顺序


    /***
     * 我们可以想到，对于两个相邻（只差了一个位置）的滑动窗口，它们共用着 k-1 个元素，而只有 1 个元素是变化的。我们可以根据这个特点进行优化。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

       int n = nums.length;

        // 1. 优先队列存放的是二元组(num,index) : 大顶堆（元素大小不同按元素大小排列，元素大小相同按下标进行排列）
        // num :   是为了比较元素大小
        // index : 是为了判断窗口的大小是否超出范围
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });

        // 2. 优选队列初始化 : k个元素的堆
        //把值和索引同时传入优先队列，并按指定规则进行排序
        for (int i = 0; i < k; i++){
            pq.offer(new int[]{nums[i],i});
        }

        // 3. 处理堆逻辑
        int[] res = new int[n-k+1]; // 初始化结果数组长度 ：一共有 n - k + 1个窗口
        res[0] = pq.peek()[0];   // 初始化res[0] ： 拿出目前堆顶的元素  peek：查看首个元素，不会移除首个元素，如果队列是空的就返回null
        for (int i = k; i < n; i++){  // 向右移动滑动窗口
            pq.offer(new int[]{nums[i],i});  //每移动一次，把新元素加入大顶堆中
            while (pq.peek()[1] <= i-k){   // 将下标不在滑动窗口中的元素都干掉
                pq.poll();  // 维护：堆的大小就是滑动窗口的大小
            }
            res[i-k+1] = pq.peek()[0];   // 此时堆顶元素就是滑动窗口的最大值
        }

        return res;

    }

    //第二种方法就是单调队列,但是可以做到同时弹出队首和队尾，所以也可以称为双端队列
    public int[] maxSlidingWindow2(int[] nums, int k) {

        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k ;i++){

            //因为是while循环，所以一定是按照递减排的
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                //出现新的元素比队列存的索引对应的元素大的时候，应当移除，要保证队列是呈递减的姿态
                deque.pollLast();
            }

            //把索引存进去
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];

        //第一个滑动窗口
        ans[0] = nums[deque.peekFirst()];

        for (int i = k; i < n; i++){
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                //把新加入的元素大小和前一个窗口的对比
                deque.pollLast();
            }

            deque.offerLast(i);

            //如果不在窗口范围内的数据，也要剔除
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            //在窗口范围内，队头就是最大元素的索引，直接从数组获取
            ans[i - k + 1] = nums[deque.peekFirst()];

        }

        return ans;

    }
}