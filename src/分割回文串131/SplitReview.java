package 分割回文串131;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitReview {
}
class Solution {

    //遇见长的字符串需要考虑多种情况，所以要使用动态规划+回溯的预处理

    //对需求的字符串考虑所有的分割方案，用搜索+回溯的方法进行枚举所有可能
    //假设我们当前搜索到字符串的第 i 个字符，s[0..i−1] 位置的所有字符已经被分割成若干个回文串，并且分割结果被放入了答案数组 ans 中，
    // 那么我们就需要枚举下一个回文串的右边界 j，使得 s[i..j] 是一个回文串。
    //因此，我们可以从 i 开始，从小到大依次枚举 j。对于当前枚举的 j 值，我们使用双指针的方法判断 s[i..j] 是否为回文串：如果 s[i..j] 是回文串，
    // 那么就将其加入答案数组 ans 中，并以 j+1 作为新的 i 进行下一层搜索，并在未来的回溯时将 s[i..j] 从 ans 中移除。
    //
    //如果我们已经搜索完了字符串的最后一个字符，那么就找到了一种满足要求的分割方法。
    //当我们在判断 s[i..j] 是否为回文串时，常规的方法是使用双指针分别指向 i 和 j，每次判断两个指针指向的字符是否相同，直到两个指针相遇。
    //然而这种方法会产生重复计算，例如下面这个例子：
    //
    // 当 s=aaba 时，对于前 2 个字符 aa，我们有 2 种分割方法 [aa] 和 [a,a]，当我们每一次搜索到字符串的第 i=2 个字符 b 时，
    // 都需要对于每个 s[i..j] 使用双指针判断其是否为回文串，这就产生了重复计算。
    //

    boolean[][] f;
    List<List<String>> ret = new ArrayList<>();
    List<String> ans = new ArrayList<>();

    int n;



    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }


    public void dfs(String s, int i){
        if (i == n){
            ret.add(new ArrayList<>(ans));
            return;
        }

        for (int j = i; j < n; j++){
            if (f[i][j]){
                ans.add(s.substring(i,j+1));
                dfs(s,j+1);
                ans.remove(ans.size() - 1);
            }
        }
    }
}