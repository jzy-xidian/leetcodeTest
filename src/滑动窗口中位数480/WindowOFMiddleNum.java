package 滑动窗口中位数480;

import java.util.*;

public class WindowOFMiddleNum {

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
//        int k = 3;
//        double[] result = solution.medianSlidingWindow(nums,k);
//        System.out.println(Arrays.toString(result));

        System.out.println((double)(2+3)/2);
    }

}

//根据窗口大小，要判断是奇数还是偶数，偶数的话排序后求中间两个数的平均值，奇数直接找最中间的数
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {

        int len = nums.length;

        //采用双指针，分别表示滑动窗口的左右
        int left = 0;
        int right = left + k - 1;

        //定义数组用来存储中位数
        double[] result = new double[len - k + 1];

        //只要还没滑动到最右边，就进行检索
        while (right < len){

            int[] cal = new int[k];

            for (int i = left,j = 0; i <= right; i++,j++){
                cal[j] = nums[i];
            }

            //对取到的滑动窗口进行排序
            Arrays.sort(cal);

            if (k % 2 == 0){

                double mid = (double)(cal[k/2-1] + cal[k/2]) / 2;
                result[left] = mid;

            }else {
                result[left] = cal[k/2];
            }

            //同步更新左右下标
            left++;
            right++;


        }

        return result;



    }

    //[2147483647,2147483647]这样的大数，会出现报错的情况
    //这个题没有那么简单
    //采用双优先队列+延迟删除

    public double[] medianSlidingWindow2(int[] nums, int k) {
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; ++i) {
            dh.insert(nums[i]);
        }
        double[] ans = new double[nums.length - k + 1];
        ans[0] = dh.getMedian();
        for (int i = k; i < nums.length; ++i) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            ans[i - k + 1] = dh.getMedian();
        }
        return ans;
    }



}

class DualHeap {
    // 大根堆，维护较小的一半元素
    private PriorityQueue<Integer> small;
    // 小根堆，维护较大的一半元素
    private PriorityQueue<Integer> large;
    // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
    private Map<Integer, Integer> delayed;

    private int k;
    // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int smallSize, largeSize;

    public DualHeap(int k) {
        this.small = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2.compareTo(num1);
            }
        });
        this.large = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num1.compareTo(num2);
            }
        });
        this.delayed = new HashMap<Integer, Integer>();
        this.k = k;
        this.smallSize = 0;
        this.largeSize = 0;
    }

    public double getMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    public void insert(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        makeBalance();
    }

    public void erase(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        makeBalance();
    }

    // 不断地弹出 heap 的堆顶元素，并且更新哈希表
    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int num = heap.peek();
            if (delayed.containsKey(num)) {
                delayed.put(num, delayed.get(num) - 1);
                if (delayed.get(num) == 0) {
                    delayed.remove(num);
                }
                heap.poll();
            } else {
                break;
            }
        }
    }

    // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
    private void makeBalance() {
        if (smallSize > largeSize + 1) {
            // small 比 large 元素多 2 个
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            // small 堆顶元素被移除，需要进行 prune
            prune(small);
        } else if (smallSize < largeSize) {
            // large 比 small 元素多 1 个
            small.offer(large.poll());
            ++smallSize;
            --largeSize;
            // large 堆顶元素被移除，需要进行 prune
            prune(large);
        }
    }
}

