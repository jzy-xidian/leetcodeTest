package 种花问题605;

import java.util.ArrayList;
import java.util.List;

public class PlaceFlower {
}
class Solution {

    //贪心的思想，在剩余空的情况下，避开相邻，得到最多的插花数量
    //要注意0并不一定连续，还要考虑中间有一朵花的情况，试一下统计两朵花之间0的个数，分奇数偶数两种讨论
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        //通过自己观测，发现如果是奇数的情况，对2算商，如果是偶数的情况，对3算商
        List<Integer> list = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < flowerbed.length; i++){

            if (flowerbed[i] == 0){
                sum++;
            }
            else if (flowerbed[i] == 1 && sum != 0 ){

                list.add(sum);
                sum = 0;
            }else {
                continue;
            }

        }

        int result = 0;

        //[0010]这种情况报错是因为没有考虑边界，大体思路其实是对的

        for (int i = 0; i < list.size(); i++){

            int num = list.get(i);

            if (num %2 != 0){
                result += num / 2;
            }else {
                result += num / 3;
            }

        }

        return result >= n;


    }
    //看下别人的一个解法，思路一样，但是解法更好

    /**
     * 统计连续的0的区间，分别有多少个连续的0即可。对于每一段0区间，都可以根据公式直接算出可以种几朵花。
     *
     * 公式可以通过数学归纳法推出来，很简单：
     *
     * 1）对于中间的0区间：
     *
     * 1~2个0：可种0朵；
     *
     * 3~4个：可种1朵；
     *
     * 5~6个：可种2朵；
     *
     * ...
     *
     * count个：可种 (count-1)/2 朵
     *
     * 2）对于两头的0区间，由于左边、右边分别没有1的限制，可种花朵数稍有不同。
     *
     * 为了代码流程的统一，可以在数组最左边、数组最右边分别补1个0，意味着花坛左边、右边没有花。（这一步才是精髓）
     *
     * 这样公式就跟1）相同了。
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {

        if(flowerbed == null || flowerbed.length == 0)
            return n == 0;

        int countOfZero = 1; // 当前全0区段中连续0的数量，刚开始预设1个0，因为开头花坛的最左边没有花，可以认为存在一个虚无的0(这个是真的牛逼)
        int canPlace = 0;//可以种的花的数量

        for (int bed : flowerbed){
            if (bed == 0){

                countOfZero++;

            }else {
                canPlace += (countOfZero-1) / 2;
                if (canPlace >= n){
                    return true;
                }
                else {
                    countOfZero = 0;
                }
            }
        }

        //最后边界的0还没有结算
        countOfZero++; // 最后再预设1个0，因为最后花坛的最右边没有花，可以认为存在一个虚无的0
        canPlace += (countOfZero-1)/2;

        return canPlace >= n;

    }

    //再看看官方题解的思路

}