package 无重复字符最长字串03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class longCharNoRepeat {
    public static void main(String[] args) {


        String s = "abcabcbb";
        String s2 = "bbbbbbbbbbb";

        Solution2 solution = new Solution2();

        System.out.println(solution.lengthOfLongestSubstring(s));

    }
}

//初始思路：采用map进行计算，统计map长度，这种思路采用的是类似滑动窗口
//其实不需要map，采用set即可 换第二种思路
class Solution {
    public int lengthOfLongestSubstring(String s) {

        char[] arr =  s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        int length = 0;
        int j = 0;

        for(int i=0; i < arr.length; i++){

            j = i;

            while (j< arr.length && !map.containsKey(arr[j])){

                map.put(arr[j++],1);

            }

            if(length <= map.size()){
                length = map.size();
            }

            map.clear();

        }


        return length;
    }
}

//其实不需要用map，采用set即可解决该问题
class Solution2 {
    public int lengthOfLongestSubstring(String s) {

        Set<Character> set = new HashSet<>();

        int n = s.length();

        // 右指针，初始值为 0，相当于我们在字符串的左边界第一个下标，还没有开始移动
        //ans记录不重复的最大长度
        int rk = 0, ans = 0;

        for (int i=0; i < n; i++){

            if(i != 0){
                // 左指针向右移动一格，移除一个字符,也是类似于滑动窗口
                set.remove(s.charAt(i - 1));
            }

            while (rk < n && !set.contains(s.charAt(rk))) {
                // 不断地移动右指针
                set.add(s.charAt(rk));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i);

        }

        return ans;
    }
}

