package 交换字符串中的元素1202;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ExchangeChar {
}

class Solution {


    //ASCII 值越小的字符位于字符串中的位置越靠前，整个字符串的字典序就越靠前。

    //采用并查集。先遍历 pairs 中的索引对，将索引对中成对的索引输入并查集，并查集会帮助我们实现同属于一个连通分量中的元素的合并工作。注意：并查集管理的是索引不是字符

    //遍历输入字符串 s，对于每一个索引，找到这个索引在并查集中的代表元，把同属于一个代表元的字符放在一起。
    // 这一步需要建立一个映射关系。键：并查集中的代表元，值：同属于一个代表元的 s 中的字符。可以使用哈希表建立映射关系。
    //
    //分组排序。即对同属于一个连通分量中的字符进行排序。

    //这一步实现可以这样做：重新生成一个长度和 s 相同的字符串，
    //对于每一个索引，查询索引在并查集中的代表元，再从哈希表中获得这个代表元对应的字符集列表，从中移除 ASCII 值最小的字符依次拼接起来
    //


    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        if (pairs.size() == 0){
            return s;
        }

        // 第 1 步：将任意交换的结点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);

        for (List<Integer> pair : pairs){
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1,index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
//            if (hashMap.containsKey(root)) {
//                hashMap.get(root).offer(charArray[i]);
//            } else {
//                PriorityQueue<Character> minHeap = new PriorityQueue<>();
//                minHeap.offer(charArray[i]);
//                hashMap.put(root, minHeap);
//            }
            // 上面六行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();




    }

    private class UnionFind {

        private int[] parent;

        /**
         * 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
         */
        private int[] rank;

        public UnionFind(int n){
            this.parent = new int[n];
            this.rank = new int[n];

            for (int i = 0; i < n; i++){
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY){
                return;
            }

            if (rank[rootX] == rank[rootY]){
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度仅加了 1
                rank[rootY]++;
            }else if(rank[rootX] < rank[rootY]){
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度不变
            }else {
                // 同理，此时以 rootX 为根结点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        public int find(int x){
            if (x != parent[x]){
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }


    }
}