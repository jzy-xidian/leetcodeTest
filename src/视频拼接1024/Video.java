package 视频拼接1024;

import java.util.Arrays;

public class Video {
}

class Solution {
    //自己的思路：输入的是一个二维数组，每行只有两个数据用来记录时长，先找到含有0和最大值的部分，
    //这样首先有了两个片段保证T的大小，然后再寻找中间部分
    public int videoStitching(int[][] clips, int T) {

        //先判断二维数组长度为0和1的情况
        if (clips.length == 0){
            return -1;
        }
        if (clips.length == 1){
            int length = clips[0].length;
            if (clips[0][0] !=0 || clips[0][length - 1] <= T){
                return -1;
            }
            else
                return 1;
        }

        //先找到尽量大的头部和尾部片段
        int headstart = 100;
        int headEnd = 0;
        int tailStart = 100;
        int tailEnd = 100;
        int count = 0;//用来统计时间片段
        for (int i = 0; i < clips.length; i++){
            for (int j = 0; j < clips[i].length; j++){
                if (clips[i][0] == 0){
                    headstart = 0;
                    headEnd = Math.max(clips[i][1],headEnd);
                }

                if (headstart == 0 && headEnd == T){
                    return 1;
                }

                if (clips[i][1] >= T){
                    tailEnd = clips[i][1];
                    tailStart = Math.min(clips[i][0],tailStart);
                }

                if (tailStart == 0 && tailEnd == T){
                    return 1;
                }

                if (headstart == 0 && tailEnd >= T && headEnd >= tailStart){
                    return 2;
                }
            }
        }

        //现在已经确认了四个变量，如果中间还缺少，需要去中间继续找
        //这里是没有合理解决的一个部分，去看看别人的思路
        return 0;
    }

    //这个题需要贪心算法和动态规划

    //第一种方法就是动态规划
    // 比较容易想到的方法是动态规划，我们令 dp[i] 表示将区间 [0,i)
    // 覆盖所需的最少子区间的数量。由于我们希望子区间的数目尽可能少，因此可以将所有 dp[i] 的初始值设为一个大整数，
    // 并将 dp[0]（即空区间）的初始值设为 0。

    //我们可以枚举所有的子区间来依次计算出所有的 \textit{dp}dp 值。我们首先枚举 ii，同时对于任意一个子区间[aj,bj)
    //​若其满足 aj < i 小于等于 b_j，那么它就可以覆盖区间 [0,i) 的后半部分，
    //而前半部分则可以用dp[aj] 对应的最优方法进行覆盖，因此我们可以用 dp[a_j] + 1来更新dp[i]。

    public int videoStitching2(int[][] clips, int T){
        int[] dp = new int[T+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++){
            for (int[] clip : clips){
                if (clip[0] < i && i <= clip[1]){
                    dp[i]=Math.min(dp[i],dp[clip[0]]+1);
                }
            }
        }

        return dp[T] == Integer.MAX_VALUE -1 ? -1 : dp[T];
    }

    // 第二种就是贪心算法
    // 注意到对于所有左端点相同的子区间，其右端点越远越有利。且最佳方案中不可能出现两个左端点相同的子区间。
    // 于是我们预处理所有的子区间，对于每一个位置 i，我们记录以其为左端点的子区间中最远的右端点，记为 maxn[i]。
    // 具体地，我们枚举每一个位置，假设当枚举到位置 i 时，记左端点不大于 i 的所有子区间的最远右端点为 last。
    // 这样 last 就代表了当前能覆盖到的最远的右端点。
    // 每次我们枚举到一个新位置，我们都用 maxn[i] 来更新 last。
    // 如果更新后 last==i，那么说明下一个位置无法被覆盖，我们无法完成目标。
    // 同时我们还需要记录上一个被使用的子区间的结束位置为 pre，每次我们越过一个被使用的子区间，
    // 就说明我们要启用一个新子区间，这个新子区间的结束位置即为当前的 last。也就是说，每次我们遇到i==pre，则说明我们用完了一个被使用的子区间。
    // 这种情况下我们让答案加 1，并更新 pre 即可。

    public int videoStitching3(int[][] clips, int T){
        int[] maxn = new int[T];
        int last = 0,ret = 0,pre = 0;
        //预处理所有的子区间,对于每一个位置 i，我们记录以其为左端点的子区间中最远的右端点，记为 maxn[i]
        for (int[] clip : clips){
            if (clip[0] < T){
                //这一步就是计算每一个子区间左端点开始，最远的右端点距离，maxn 不一定会被用完，
                // 因为不是每个位置都是某个子区间的左端点，如果没有就按0处理就行
                maxn[clip[0]] = Math.max(maxn[clip[0]],clip[1]);
            }
        }

        for (int i = 0; i < T; i++){
            //我们枚举每一个位置，假设当枚举到位置 i 时，记左端点不大于 i 的所有子区间的最远右端点为 last。
            //这样 last 就代表了当前能覆盖到的最远的右端点。
            //每次我们枚举到一个新位置，我们都用 maxn[i] 来更新 last。
            //如果更新后 last==i，那么说明下一个位置无法被覆盖，我们无法完成目标。
            last = Math.max(last,maxn[i]);
            if (i == last){
                return -1;
            }
            //同时我们还需要记录上一个被使用的子区间的结束位置为 pre，每次我们越过一个被使用的子区间，
            //就说明我们要启用一个新子区间，这个新子区间的结束位置即为当前的 last。也就是说，每次我们遇到i==pre，则说明我们用完了一个被使用的子区间。
            //这种情况下我们让答案加 1，并更新 pre 即可
            if (i == pre){
                ret++;
                pre = last;
            }

        }

        return ret;
    }


}
