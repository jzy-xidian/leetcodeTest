package 冗余连接684;

public class Rongyu {
}
class Solution {

    //关键是找出断掉图的必要条件
    //看下并查集原理，在一棵树中，边的数量比节点的数量少 1。如果一棵树有 N 个节点，则这棵树有 N−1 条边。这道题中的图在树的基础上多了一条附加的边，因此边的数量也是 N。
    //树是一个连通且无环的无向图，在树中多了一条附加的边之后就会出现环，因此附加的边即为导致环出现的边

    //可以通过并查集寻找附加的边。初始时，每个节点都属于不同的连通分量。遍历每一条边，判断这条边连接的两个顶点是否属于相同的连通分量。
    //如果两个顶点属于不同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间不连通，因此当前的边不会导致环出现，合并这两个顶点的连通分量。
    //如果两个顶点属于相同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间已经连通，因此当前的边导致环出现，为附加的边，将当前的边作为答案返回。
    //


    public int[] findRedundantConnection(int[][] edges) {

        //统计节点的数量
        int nodesCount = edges.length;

        //这里多留一个空位
        int[] parent = new int[nodesCount + 1];

        //先对每个节点赋值，作为要联通的节点
        for (int i = 1; i <= nodesCount; i++){
            parent[i] = i;
        }

        //并查集核心代码
        for (int i = 0; i < nodesCount; i++){
            //取出边的连接情况
            int[] edge = edges[i];

            //把两个连接的节点先取出来
            int node1 = edge[0],node2 = edge[1];

            //如果发现有相同出现相同分量的节点的时候，就把这个点进行返回
            if (find(parent,node1) != find(parent,node2)){
                union(parent,node1,node2);
            }else {
                return edge;
            }
        }

        return new int[0];


    }

    public void union(int[] parent, int index1, int index2){
        //把两个节点公用一个祖先节点，也就是合并到一个连通分量上
        parent[find(parent,index1)] = find(parent,index2);
    }


    //典型的根据索引判断是否已经为最顶层的节点
    public int find(int[] parent, int index){

        if (parent[index] != index){
            //继续向上找，直到找到最祖先节点
            parent[index] = find(parent,parent[index]);
        }

        //找到后返回祖先节点
        return parent[index];

    }
}