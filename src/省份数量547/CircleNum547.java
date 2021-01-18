package 省份数量547;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CircleNum547 {
}
class Solution {
    public int findCircleNum(int[][] isConnected) {

        //先统计有几个节点
        if(isConnected.length == 0){
            return 0;
        }
        int num = isConnected[0].length;

        int count = num;

        //用list统计已经进圈的
        List<Integer> list = new ArrayList<>();

        //可以直接查询，每次找比自己大的下标，这是无向图
        //最后一行必占一部分
        //第一个元素必定在里面
        //但是按照思路如果1先和7连接，6再连，这里就会出现问题
        //忽然感觉存边才是关键
        list.add(0);
        for(int i = 0; i < num - 1; i++){
            for(int j = i + 1; j < num; j++){
                if(isConnected[i][j] == 1 && !list.contains(j)){
                    list.add(j);
                    count--;
                }
            }
        }

        return count;


    }

    //果然这个题应该是图的深度和广度优先，结合昨天的题，可以再看下并查集
    //深度优先的话思路比较直观，采用标志位进行统计，看是否访问过，从某个城市开始，深度优先搜索
    //通过矩阵计算与该城市相连的有哪些，这些城市和该城市属于一个省份
    public int findCircleNum2(int[][] isConnected){

        //没有任何联通情况下默认的城市个数
            int provinces = isConnected.length;

            //用来标志城市是否被访问过
            boolean[] visited = new boolean[provinces];

            //用来统计最终的省份
            int circles = 0;

            for (int i = 0; i < provinces; i++){

                if (!visited[i]){

                    dfs(isConnected,visited,provinces,i);

                    //遍历成功一个加一个，如果该点已经和前面的点连接，必然不会进入if判断
                    circles++;
                }

        }

        return circles;
    }

    //深度优先遍历

    /**
     *
     * @param isConnected
     * @param visited
     * @param provinces
     * @param i 现在遍历到第几个城市了，如果有和当前节点相连的城市，继续深度遍历
     */
    public void dfs(int[][] isConnected, boolean[] visited, int provinces, int i){

           for (int j = 0; j < provinces; j++){
               if (isConnected[i][j] == 1 && !visited[j]){
                   visited[j] = true;
                   dfs(isConnected,visited,provinces,j);
               }
           }
    }

    //再看看广度优先的解法
    public int findCircleNum3(int[][] isConnected){

        int provinces = isConnected.length;

        //用来标志城市是否被访问过
        boolean[] visited = new boolean[provinces];

        //用来统计最终的省份
        int circles = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < provinces; i++){

            //根据城市加标志位，已经访问过的没有必要加进队列
            if (!visited[i]){

                queue.offer(i);
                while (!queue.isEmpty()){
                    int j = queue.poll();
                    visited[j] = true;

                    for (int k = 0; k < provinces; k++){
                        if (isConnected[j][k] == 1 && !visited[k]){
                            queue.offer(k);
                        }
                    }
                }

                circles++;
            }
        }

        return circles;
    }

    //第一次接触并查集，看看这个东西的原理
    //初始时，每个城市都属于不同的连通分量，遍历矩阵，如果两个城市之间有关联，对他们合并
    //遍历结束后，计算连通分量的个数，就是省份的总数

    public int findCircleNum4(int[][] isConnected){
        int provinces = isConnected.length;
        int[] parent = new int[provinces];

        for (int i = 0; i < provinces; i++){

            //先初始化，每个城市都是一个分量，父节点都是自己
            parent[i] = i;
        }

        for (int i = 0; i < provinces; i++){
            for (int j = i + 1; j < provinces; j++){
                if (isConnected[i][j] == 1){
                    union(parent,i,j);
                }
            }
        }

        int circles = 0;
        for (int i = 0; i < provinces; i++){
            if (parent[i] == i){
                circles++;
            }
        }

        return circles;
    }




    //好好体会一下下面两个函数

    //这一步是合并两个节点的关键
    public void union(int[] parent, int index1, int index2){

        //index1城市的祖宗节点变成index2的，相当于两个城市现在有了共同的组先
        parent[find(parent,index1)] = find(parent,index2);
    }

    //找祖先节点
    public int find(int[] parent, int index){

        //联通分量存储的值不是自己的时候，说明已经和别的节点相连，要向上找父节点
        if (parent[index] != index){
            //继续向上找，直到存储和本身一样时，找到的节点就是该节点的祖先节点
            parent[index] = find(parent,parent[index]);
        }

        return parent[index];
    }

}