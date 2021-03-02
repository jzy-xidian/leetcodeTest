package 无重叠区间435;

import java.util.Arrays;
import java.util.Comparator;

public class NoRepeat {
}

class Solution {

    //把区间进行统计，只要出现重叠区间，直接+1，这里取最大是最好的，准备两个变量，统计区间
    //但是中间要考虑断层的问题，不能只考虑两头，而且这里是不能重叠的

    //标准的动态规划问题
    public int eraseOverlapIntervals(int[][] intervals) {

        //该问题等价于选出数量最多的区间，让他们互不重叠，选出的区间互不重叠，则按照端点从小到大的顺序进行排序，无论从左端点还是从右端点，都是一样的
        //排序结束后，左右端点分别是l0,l1,...ln-1和r0，r1....rn-1;

        //令fi表示以区间i为最后一个区间，可以选出的区间数量的最大值，

        //也就是美剧倒数第二个区间的编号j满足j < i,并且第i个区间必须要与第j个区间不重叠，由于已经按照左端点进行升序了，只要第j个区间的右端点没有超越第i个区间的左端点，就说明
        //第i个区间和第j个区间没有重叠，选择fi最大的哪个进行状态转移，

        if (intervals.length == 0){
            return 0;
        }

        //对二维数组中的数组元素，按照左端点的大小进行排序
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] interval1, int[] interval2){
                return interval1[0] - interval2[0];
            }
        });

        //计算有多少个区间
        int n = intervals.length;

        //用来计算状态转移
        int[] f = new int[n];
        //先进行1的填充，意思是无论怎样先有自己本身这一个区间
        Arrays.fill(f,1);

        //进行连续的比对,比如第二个只需要和第一个比对，第三个就要和前两个的情况进行比对了
        for (int i = 1; i < n; i++){

            for (int j = 0 ; j < i; j++){

                //让前一个区间的右端点和后一个区间的左端点进行对比
                //其实理解下来就是前面的最大区间数和新的区间进行对比，如果不重叠，就把原先的区间最大数+1,并更新为i区间的不重叠最大区间数
                if (intervals[j][1] <= intervals[i][0]){

                    f[i] = Math.max(f[i],f[j] + 1);

                }

            }


        }

        //采用流计算
        return n - Arrays.stream(f).max().getAsInt();
    }


    //上一种方法的时间复杂度过高，n^2的时间复杂，采用贪心再进行对比
    public int eraseOverlapIntervals2(int[][] intervals){

        //选择哪个区间作为首区间，就是贪心算法，选取最优、
        //在首区间的左侧没有任何区间，如果有一个区间的右端点小于原定区间的右端点，就换成更小的，因为更小的于右侧被选择区间仍然不重叠

        //我们可以不断地寻找右端点在首个区间右端点左侧的新区间，将首个区间替换成该区间。那么当我们无法替换时，
        // 首个区间就是所有可以选择的区间中右端点最小的那个区间。因此我们将所有区间按照右端点从小到大进行排序，
        // 那么排完序之后的首个区间，就是我们选择的首个区间。
        //

        //如果有多个区间的右端点都同样最小怎么办？由于我们选择的是首个区间，因此在左侧不会有其它的区间，那么左端点在何处是不重要的，我们只要任意选择一个右端点最小的区间即可。
        //
        //当确定了首个区间之后，所有与首个区间不重合的区间就组成了一个规模更小的子问题。由于我们已经在初始时将所有区间按照右端点排好序了，
        // 因此对于这个子问题，我们无需再次进行排序，只要找出其中与首个区间不重合并且右端点最小的区间即可。用相同的方法，我们可以依次确定后续的所有区间。
        //
        //在实际的代码编写中，我们对按照右端点排好序的区间进行遍历，并且实时维护上一个选择区间的右端点 right。如果当前遍历到的区间 [li,ri]与上一个区间不重合，也就是li > right;
        //我们就可以贪心地选择这个区间，并将 right 更新为 ri


        if (intervals.length == 0){
            return 0;
        }

        //这里按照右端点进行排序
        Arrays.sort(intervals, new Comparator<int[]>(){

            public int compare(int[] interval1, int[] interval2){
                return interval1[1] - interval2[1];
            }

        });

        int n = intervals.length;
        //标志位
        int right = intervals[0][1];

        int ans = 1;

        //因为是按照右端点进行排序的，所以直接用新区间的左端点对比原区间的右端点，如果符合条件，实时更新即可
        for (int i = 1; i < n; i++){

            //左右端点可以重合，不能重叠
            if (intervals[i][0] >= right){
                ++ans;
                right = intervals[i][1];
            }
        }

        return n - ans;


    }
}