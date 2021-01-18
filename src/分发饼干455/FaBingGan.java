package 分发饼干455;

import java.util.Arrays;

public class FaBingGan {
}
class Solution {
    public int findContentChildren(int[] g, int[] s) {

        //排序加贪心
        Arrays.sort(g);
        Arrays.sort(s);
        int numOfChildren =g.length, numOfCookies = s.length;
        int count = 0;
        for (int i = 0,j = 0; i < numOfChildren && j < numOfCookies; i++,j++){
            while (j < numOfCookies && g[i] > s[j]){

                j++;
            }
            if (j < numOfCookies){
                count++;
            }
        }

        return count;

    }
}