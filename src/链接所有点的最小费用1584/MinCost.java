package 链接所有点的最小费用1584;

import java.util.*;

public class MinCost {
}
class Solution {

    //要采用克鲁斯卡尔最小生成树算法
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;
        DisjointSetUnion disjointSetUnion = new DisjointSetUnion(n);

        //边集合
        List<Edge> edges = new ArrayList<Edge>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                //两点之间的距离，统计所有的情况
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }


        //对统计好的边进行排序
        Collections.sort(edges, new Comparator<Edge>() {
            public int compare(Edge edge1, Edge edge2) {
                return edge1.len - edge2.len;
            }
        });

        int ret = 0, num = 1;

        //从排好序的边取出，然后统计节点是否已经联通
        for (Edge edge : edges) {
            //这里的x和y是边对应的两个点，而不是坐标
            int len = edge.len, x = edge.x, y = edge.y;
            if (disjointSetUnion.unionSet(x, y)) {
                ret += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ret;



    }


    //根据两点的坐标计算之前的花费
    public int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }


}

class DisjointSetUnion {

    //节点数组
    int[] f;
    int[] rank;
    int n;

    public DisjointSetUnion(int n) {
        this.n = n;
        this.rank = new int[n];
        Arrays.fill(this.rank, 1);
        this.f = new int[n];

        //点数初始化
        for (int i = 0; i < n; i++) {
            this.f[i] = i;
        }
    }

    public int find(int x) {

        //节点合并，形成联通
        return f[x] == x ? x : (f[x] = find(f[x]));
    }

    public boolean unionSet(int x, int y) {
        int fx = find(x), fy = find(y);

        //两个点已经联通
        if (fx == fy) {
            return false;
        }

        //对比权值大小
        if (rank[fx] < rank[fy]) {
            int temp = fx;
            fx = fy;
            fy = temp;
        }
        rank[fx] += rank[fy];
        //进行合并联通
        f[fy] = fx;
        return true;
    }
}

class Edge {
    int len, x, y;

    //边的结构体
    public Edge(int len, int x, int y) {

        //长度要通过公式计算
        this.len = len;
        this.x = x;
        this.y = y;
    }
}
