package 单词拆分Ⅱ140;

import java.util.*;

public class WordSplit2 {

    //debug走一下
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "pineapplepenapple";

        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pen");
        list.add("applepen");
        list.add("pine");
        list.add("pineapple");

        System.out.println(solution.wordBreak(s,list));
    }
}

class Solution {

    //为了避免动态规划的方法超时，需要首先使用第 139 题的代码进行判断，在可以拆分的情况下再使用动态规划的方法进行拆分。
    // 相比之下，自顶向下的记忆化搜索可以在搜索过程中将不可以拆分的情况进行剪枝，因此记忆化搜索是更优的做法。



    //这次的要求是可以重复使用
    //之前单纯采用动态规划去拆分，这次的要求可能有单词重叠的结果

    //直接看官方解法吧，你暂时还做不出来这个题

    //采用记忆化搜索
    //对于字符串 s，如果某个前缀是单词列表中的单词，则拆分出该单词，然后对 s 的剩余部分继续拆分。
    // 如果可以将整个字符串 s 拆分成单词列表中的单词，则得到一个句子。在对 s 的剩余部分拆分得到一个句子之后，
    // 将拆分出的第一个单词（即 s 的前缀）添加到句子的头部，即可得到一个完整的句子。上述过程可以通过回溯实现。

    //假设字符串 s 的长度为 n，回溯的时间复杂度在最坏情况下高达 O(n)。
    // 时间复杂度高的原因是存在大量重复计算，可以通过记忆化的方式降低时间复杂度。

    //具体做法是，使用哈希表存储字符串 s 的每个下标和从该下标开始的部分可以组成的句子列表，
    // 在回溯过程中如果遇到已经访问过的下标，则可以直接从哈希表得到结果，而不需要重复计算。
    // 如果到某个下标发现无法匹配，则哈希表中该下标对应的是空列表，因此可以对不能拆分的情况进行剪枝优化。

    //还有一个可优化之处为使用哈希集合存储单词列表中的单词，
    // 这样在判断一个字符串是否是单词列表中的单词时只需要判断该字符串是否在哈希集合中即可，而不再需要遍历单词列表。


    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();
        List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<String>(wordDict), 0, map);
        List<String> breakList = new LinkedList<String>();
        for (List<String> wordBreak : wordBreaks) {
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        if (!map.containsKey(index)) {
            List<List<String>> wordBreaks = new LinkedList<List<String>>();
            if (index == length) {
                wordBreaks.add(new LinkedList<String>());
            }
            for (int i = index + 1; i <= length; i++) {
                String word = s.substring(index, i);
                if (wordSet.contains(word)) {
                    List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);
    }



}