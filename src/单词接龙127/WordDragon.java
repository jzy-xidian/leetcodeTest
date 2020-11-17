package 单词接龙127;

import java.util.*;

public class WordDragon {

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";

        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");

        Solution solution = new Solution();
        System.out.println(solution.ladderLength2(beginWord,endWord,list));
    }
}
class Solution {

    /**
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     * 根据题目要求
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     */

    //个人思路：这个题要找最短的转换序列长度，可以与字典中单词对比，每次仅变动一个字符

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        //首先判断有没有开始单词和结束单词，如果没有的话直接返回0
        if(!wordList.contains(endWord)){
            return 0;
        }

        int count = 1;

        String index = beginWord;
        String temp = endWord;

        while (index != endWord){

            for (int i = 0; i < wordList.size(); i++){

                //如果取到的值和endword只差一个字母，那么可以直接下一步，接龙结束，否则继续，只要找到一个差一的，就更新index，继续循环
                if (wordList.get(i).equals(endWord)){

                    //思路卡在怎么找差1的这个操作
                    if (index.equals(endWord)){

                    }else {
                        continue;
                    }

                }else {
                    if (index.equals(endWord)){

                    }
                    else {
                        continue;
                    }
                }

            }

        }


        return count;
    }

    //先给每个单词赋予一个id
    Map<String,Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int nodeNum = 0;


    //看看官方解法吧
    //采用的是广度优先搜索，抽象成图的模型，看成是找最短路径问题
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        for (String word : wordList){
            //先把每个单词对应的带*的情况统计出来
            addEdge(word);
        }

        addEdge(beginWord);
        //如果列表中没有结束单词，直接返回0
        if (!wordId.containsKey(endWord)){
            return 0;
        }

        //在给定的案例中，nodenum有4*7=28种
        int[] dis = new int[nodeNum];

        //先对数组全部填充最大值，后面根据下一个点来选择新的值
        Arrays.fill(dis,Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord),endId = wordId.get(endWord);
        dis[beginId] = 0;

        //采用链表模式
        Queue<Integer> que = new LinkedList<>();
        que.offer(beginId);

        // 我们将起点加入队列开始广度优先搜索，当搜索到终点时，
        // 我们就找到了最短路径的长度。注意因为添加了虚拟节点，所以我们得到的距离为实际最短路径长度的两倍。
        // 同时我们并未计算起点对答案的贡献，所以我们应当返回距离的一半再加一的结果。

        while (!que.isEmpty()){
            int x = que.poll();
            if (x == endId){
                return dis[endId] / 2 + 1;
            }

            //这一步就是利用存在list中的节点循环匹配带*的值，和与begin契合的单词全部变成1，
            for (int it : edge.get(x)){
                if (dis[it] == Integer.MAX_VALUE) {
                    //初始为0
                    dis[it] = dis[x] + 1;
                    //把带*的都放入的队列中
                    que.offer(it);
                }
            }
        }

        return 0;


    }

    //加边操作
    public void addEdge(String word){

        addWord(word);
        //获取当前传入单词的id
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; i++){
            char tmp = array[i];

            //从这一步开始，就是为了计算是否有只差一位的单词
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            //map把带*号的所有情况也会加入映射中
            //每种*的id是不同的，但是对应的原单词肯定是一样的id1
            int id2 = wordId.get(newWord);
            //这一步是要统计初始单词的其实num和他对应带*的num，要做到一一对应，
            //这一步是为了让原单词对应所有的带*单词
            edge.get(id1).add(id2);
            //每增加一个带*的单词，必定多一个ArrayList，所以所有带*的添加id1是为了对应原单词
            edge.get(id2).add(id1);
            //恢复原单词，更新下一个带*单词
            array[i] = tmp;


        }

    }

    public void addWord(String word){
        if (!wordId.containsKey(word)) {
            wordId.put(word,nodeNum++);
            edge.add(new ArrayList<>());
        }
    }
}