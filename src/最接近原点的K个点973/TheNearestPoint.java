package 最接近原点的K个点973;

import java.util.*;

public class TheNearestPoint {
}
class Solution {

    //自己的思路：根据输入点，求平方和，然后进行从小到大排序
    //取出前K个点即可
    public int[][] kClosest(int[][] points, int K) {

        //计算每行两个数的平方和，用一维数组进行存储，最后排序
        int length = points.length;
        int[] store = new int[length];

        //需要有个数组来存储下标该值对应的下标
        Map<Integer,Integer> map = new HashMap<>();

        int distance = 0;
        for (int i =0; i < length; i++){

            distance = points[i][0] * points[i][1];
            store[i] = distance;



            //用下标对应距离的值
            map.put(i,distance);


        }

        Arrays.sort(store);

        //这里根据map存储的值，获取对应的key下标，数量达到key的时候，停止
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < K; i++){

            if (set.size() >= K){
                break;
            }

            String key = "";
            for (Map.Entry<Integer,Integer> m : map.entrySet()){

                if (m.getValue().equals(store[i])){
                    //获取下标
                    set.add(m.getKey());
                }


            }

        }

        List <Integer> list = new ArrayList<>(set);//B是set型的

        int[][] Result = new int[K][];

        for (int i = 0; i < K; i++){
            Result[i] = points[list.get(i)];
        }

        return Result;

    }

    //自己写的方法也太沙雕了，看看别人的解法
    //用一下Arrays.sort和Arrays.copyof
    public int[][] kClosest2(int[][] points, int K){

        //可以直接对二维数组排序是真的长知识了
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]);
            }
        });


        return Arrays.copyOfRange(points,0,K);
    }

    //第二种方法就是采用优先队列，也就是大根堆，实时维护前 K 个最小的距离平方。
    public int[][] kClosest3(int[][] points, int K){

        //这里是降序排序，制造大顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] array1, int[] array2) {
                return array2[0] - array1[0];
            }
        });
        //offer()方法用于将给定元素(ele)添加到此PriorityQueue中。
        //暂时先存了钱K个
        for (int i = 0; i < K; ++i) {
            //直接把（距离，下标）这样一个数组入队，再通过自定义比较器形成大顶堆
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        int n = points.length;
        for (int i = K; i < n; ++i) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (dist < pq.peek()[0]) {
                //比自己大的就直接出队，让再入队继续调整
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; ++i) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;


    }

    //最后参考一下别人的map写法
    public int[][] kClosest4(int[][] points, int K){

        //treeMap是有序的
        //一个距离值可能对应多个数组，所以采用list将多个数组进行存储
        Map<Long, List<int[]>> map = new TreeMap<>();

        for (int[] p : points) {
            long len = p[1] * p[1] + p[0] * p[0];
            //直接用数组的形式进行存储
            //Map.getOrDefault(Object key, V defaultValue)方法的作用是：
            //  当Map集合中有这个key时，就使用这个key值；
            //  如果没有就使用默认值defaultValue。
            List<int[]> list = map.getOrDefault(len, new ArrayList<>());
            list.add(p);
            map.put(len, list);
        }
        int[][] res = new int[K][2];
        int i = 0;
        for (long len : map.keySet()) {
            if (i == K) break;
            List<int[]> list = map.get(len);
            for (int[] p : list) {
                res[i++] = p;
            }
        }
        return res;
    }

}
