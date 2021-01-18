package 除法求值399;

import java.util.*;

public class Chufaqiuzhi {
}
class Solution {


    //广度优先遍历
    //将问题建模成一张图，给定图中一些点，以及某些边的权值，对任意两袋奶求出路径长，也就是变量的比值
    //先要遍历equations数组，找出其中所有不同的字符串，并通过hash表讲每个不同的字符串映射成整数
    //将图构建完成后，直接从起点出发，通过广度优先搜索，不断更新起点和当前点之间的路径长度，直到搜索到终点
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        int nvars = 0;
        Map<String, Integer> variables = new HashMap<>();
        //变量对数组的大小
        int n = equations.size();
        //这一步算是对对数组每个不同的字母给一个权值，为了构建图
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }


        //对于每个点，存储其直接连接到的所有点和对应的权值
        //点可以看成上一步构建的每个字母在哈希表中对应的value

        //对于每个点，存储其直接连接到的所有点及对应的权值
        //这个时候nvars已经统计好了所有不同的字符
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++){
            //对每个点要存储的list进行初始化
            edges[i] = new ArrayList<>();
        }

        //对数数组里面对数的个数
        for (int i = 0; i < n; i++){
            //把每个对数分别提取出来,也就是在hash表中存的该点的值
            int va = variables.get(equations.get(i).get(0)),vb = variables.get(equations.get(i).get(1));

            //把对数对应values数组里的值拿出来，作为两点之间的权重进行存储
            edges[va].add(new Pair(vb,values[i]));
            //反着的权重就是倒数
            edges[vb].add(new Pair(va,1.0/values[i]));

        }

        //统计问题的个数
        int queriesCount = queries.size();
        //用来存储结果
        double[] ret = new double[queriesCount];

        //遍历问题对
        for (int i = 0; i < queriesCount; i++){

            //遍历传入的queries数组，分别拿到要解决的问题
            List<String> query = queries.get(i);

            //如果提出的问题在对数数组中没有该字符的出现，就以默认值赋值
            double result = -1.0;

            //每个问题对，必须要两个字符都存在才可以进行计算，否则就按默认值处理
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))){

                //分别看下两个字母代表的自身权值是多少
                int ia = variables.get(query.get(0)),ib = variables.get(query.get(1));

                //如果问题对的两个字符完全一样，必然结果是1
                if (ia == ib){
                    result = 1.0;
                }else {

                    //创建队列，准备广度优先搜索
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(ia);

                    //navrs个不同字符,创建数组根据自身权值进行结果统计
                    double[] ratios = new double[nvars];

                    //先给每个元素填充成-1.0
                    Arrays.fill(ratios,-1.0);

                    //ia是问题对的起点，根据图的原理要向下寻找
                    //先把起点初始化为1，向后算权
                    ratios[ia] = 1.0;

                    while (!points.isEmpty() && ratios[ib] < 0){
                        //出队
                        int x = points.poll();
                        //把已经存入的图的权重进行统计
                        for (Pair pair : edges[x]){

                            //获得该边是到哪个点
                            int y = pair.index;
                            //两点之间的权重
                            double val = pair.value;

                            //从存储的对数后位的边开始算权重
                            if (ratios[y] < 0){
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }

                        }
                    }

                    //ib是要算的问题的终点，所以计算结果就是它的权
                    result = ratios[ib];
                }


            }

            //每次for循环结束，统计一个问题的答案
            ret[i] = result;

        }

        return ret;
    }




}

//构建图
class Pair {

    int index;
    double value;

    Pair(int index, double value){
        this.index = index;
        this.value = value;
    }
}