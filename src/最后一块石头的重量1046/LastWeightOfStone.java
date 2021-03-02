package 最后一块石头的重量1046;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LastWeightOfStone {
}
class Solution {
    //先对数组进行排序，选出最大的两个，然后粉碎，重新放回数组，再进行排序，直到剩下最后一块

    //知道用队列，但是没用考虑到优先队列的写法，也就是采用最大堆的方式


    public int lastStoneWeight(int[] stones) {

        //采用lamda表达式，逆向排序
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b - a);

        for(int stone : stones){

            //将数组中每个元素入堆
            queue.offer(stone);

        }

        while (queue.size() > 1){

            //先把最大的两个取出来
            int a = queue.poll();
            int b = queue.poll();

            //有差值的情况下，重新入堆进行排序
            if (a > b){
                queue.offer(a - b);
            }

        }

        //最后返回0或者1
        return queue.isEmpty() ? 0 : queue.poll();
    }

    //复习下手写大顶堆
    public int lastStoneWeight2(int[] stones){

        //先把整个数组调整成堆
        genHeap(stones);

        for (int i = 0; i < stones.length; i++){

            //保存第一个最大数，然后置0，再把第一个数和次大数进行相减，再对堆进行调整
            int first = stones[0];
            stones[0] = 0;
            change(stones,0);
            stones[0] = first - stones[0];
            change(stones,0);
        }

        return stones[0];


    }

    //调用调整方法生成堆
    private void genHeap(int[] nums){
        for (int i = (nums.length - 1 ) / 2; i >=0; i--){

            //从倒数第二层开始，然后依次向上找。自底向上对比大小，然后调整堆
            change(nums,i);
        }
    }



    //对生成的堆进行调整
    private void change(int[] nums, int start){

        //节点与自己的左子节点的坐标的关系公式
        int next = start * 2 + 1;

        //要置换的节点
        int temp = nums[start];

        //不能超过整个数组的长度
        while (next < nums.length){

            //next < nums.length - 1这一步是为了保障不会数组越界,
            // 因为如果最后一层如果只有一个左子节点，无论如何只能和这个左子节点交换，不能再越界去和右子节点比较
            if (next < nums.length - 1 && nums[next + 1] > nums[next]){
                next++;
            }

            //在右子节点大于左子节点的情况下，让父节点和右子节点交换，否则和左子节点交换，如果父节点更大，则不交换
            if (nums[next] > temp){

                //先改变父节点的值，因为子节点还要继续往下探索,为了把小的往下放
                nums[start] = nums[next];
                start = next;
                next = 2*start + 1;
            }else {
                break;
            }
        }

        nums[start] = temp;
    }
}