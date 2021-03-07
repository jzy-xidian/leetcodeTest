package 俄罗斯套娃信封问题354;

import java.util.Arrays;
import java.util.Comparator;

public class TaoWa {
}
class Solution {

    //参考第300题，最长子序列问题

    /**
     * 排完序的结果为 [(w,h)]=[(1,1),(1,2),(1,3),(1,4)]，由于这些信封的 w 值都相同，不存在一个信封可以装下另一个信封，
     * 那么我们只能在其中选择 1 个信封。然而如果我们完全忽略 w 维度，剩下的 hh 维度为 [1,2,3,4]，这是一个严格递增的序列，
     * 那么我们就可以选择所有的 4 个信封了，这就产生了错误。
     *
     * 因此，我们必须要保证对于每一种 w 值，我们最多只能选择 1 个信封。
     *

     * @param envelopes
     * @return
     */

    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length == 0){
            return 0;
        }

        //有多少行
        int n = envelopes.length;

        Arrays.sort(envelopes,new Comparator<int[]>(){

            //先按第一个元素从小到大排列
            public int compare(int[] a1, int[] a2){

                if (a1[0] != a2[0]){
                    return a1[0] - a2[0];
                }

                //两个第一元素一样的情况下，对比数组的第二元素
                /**
                 *
                 * 我们可以将 h 值作为排序的第二关键字进行降序排序，
                 * 这样一来，对于每一种 w 值，其对应的信封在排序后的数组中是按照 h 值递减的顺序出现的，
                 * 那么这些 hh 值不可能组成长度超过 1 的严格递增的序列，这就从根本上杜绝了错误的出现。
                 *
                 */
                else{

                    return a2[1] - a1[1];
                }

            }
        });

        //排序结束后，参考最长子序列问题
        //作为状态转移数组
        int[] f = new int[n];

        //每个信封先保证自己一个
        Arrays.fill(f,1);

        //起始的统计值，最终用来统计最大值
        int ans = 1;

        //要注意如果第一元素相同，只能取一个，第二元素相同的情况下也是同理
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){

                //在第一元素相同的情况下，第二元素是递减的，所以避免了相同值情况下的叠加
                if (envelopes[j][1] < envelopes[i][1]){

                    //第二个for循环中不断更新f[i]可以套的数量
                    f[i] = Math.max(f[i],f[j] + 1);
                }

            }

            //一轮结束后，找最大
            ans = Math.max(ans,f[i]);
        }

        return ans;


    }
}