package 移除最多的同行或同列石头947;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveSameStones {
}
class Solution {

    //从并查集的角度考虑这个问题
    //一定可以把一个连通图里的所有顶点根据这个规则删到只剩下一个顶点
    //可以通过遍历的方式（深度优先遍历或者广度优先遍历）遍历到这个连通图的所有顶点。
    // 那么就可以按照遍历的方式 逆向 移除石头，最后只剩下一块石头。所以：最多可以移除的石头的个数 = 所有石头的个数 - 连通分量的个数
    //
    //删到最后，留在图中的顶点一定位于不同的行和不同的列。因此，并查集里的元素是 描述「横坐标」和「纵坐标」的数值。
    // 因此我们需要遍历数组 stones，将每个 stone 的横坐标和纵坐标在并查集中进行合并。理解合并的语义十分重要。
    //

    //在并查集里应该如何区分横纵坐标呢

    //
    //在并查集里我们需要维护连通分量的个数，新创建顶点的时候连通分量加 1；合并不在同一个连通分量中的两个并查集的时候，连通分量减 1。

    public int removeStones(int[][] stones) {

        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones){
            unionFind.union(stone[0]+ 10001, stone[1]);
        }

        return stones.length - unionFind.getCount();

    }

    //构造并查集的内部类
    private class UnionFind {

        //构造联通分量
        private Map<Integer,Integer> parent;

        //联通分量个数
        private int count;

        public UnionFind(){

            //成员变量初始化
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount(){
            return count;
        }

        public int find(int x){

            //查看x是否在分量中。
            if (!parent.containsKey(x)){
                parent.put(x,x);
                // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
                count++;
            }

            //查找一个连通分量中的祖先节点并返回
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);

        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY){
                return;
            }
            parent.put(rootX, rootY);

            // 两个连通分量合并成为一个，连通分量的总数 -1
            count--;
        }


    }

    //还可以采用深度优先搜索
    //们将这个二维平面抽象成图，把石子看作「点」，石子间的同行或同列关系看作「边」。如果两个石子同属某一行或某一列，
    // 我们就认为这两个石子之间有一条边。由题意可知，对于任意一个点，只要有点和它相连，我们就可以将其删除。
    //
    //显然，对于任意一个连通图，我们总可以通过调整节点的删除顺序，把这个连通图中删到只剩下一个节点。本题中我们不需要关注如何安排删除顺序，只需要了解这个性质即可。
    //

    //在实际代码实现中，我们首先枚举计算任意两点间的连通性，然后使用深度优先搜索的方式计算连通块的数量即可。

    public int removeStones2(int[][] stones){

        int n = stones.length;

        //存边
        List<List<Integer>> edge = new ArrayList<>();

        for (int i = 0; i < n; i++){
            edge.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    //只要横坐标或者纵坐标一样，就加进去
                    edge.get(i).add(j);
                }
            }

        }

        boolean[] vis = new boolean[n];

        //计算能连到一起的点
        int num = 0;

        for (int i = 0; i < n; i++){
            if (!vis[i]){
                num++;
                dfs(i,edge,vis);
            }
        }

        return n-num;

    }

    //深度优先遍历算法
    public void dfs(int x, List<List<Integer>> edge, boolean[] vis){

        //表示该节点已经访问
        vis[x] = true;

        //找x下的节点
        for (int y : edge.get(x)){

            //如果该节点没有被访问，则继续深度优先
            if (!vis[y]){
                dfs(y,edge,vis);
            }
        }
    }
}