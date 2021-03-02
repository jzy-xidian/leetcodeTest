package 按要求补齐数组330;

public class FinishArray {
}

class Solution {


    //采用贪心算法，可以把这个题看成加砝码的方式，
    //可以考虑原本的数组nums中，缺少哪些在区间[1,n]的数字，由小到大进行添加
    public int minPatches(int[] nums, int n) {

        /**
         * 如果我有砝码0，能称的重量为0
         * 再加一个砝码1，能称的重量不到2    1
         * 再加一个砝码2，能称的重量不到4    1    1+2
         * 再加一个砝码4，能称的重量不到8    1    2    1+2    4  1+4  2+4   1+2+4
         * 再加一个砝码8，能称的重量不到16（16以内的全都能称出来）
         * 因为我每次前一步已经完全覆盖[0，n),再加一个n kg的不就能覆盖[0，2n)了吗。
         * 同时为了加的次数最少我们才这样加，不需要1，2，3，4，5.....每种砝码都有。
         * 只有这样先做到局部加砝码最少，才能保证到后边n很大时加的砝码最少。
         */

        //这里涉及到一个数学理论：有一个整数x，如果在区间[1,x-1]内所有数字已经被覆盖，且x在数组中，区间[1,2x-1]内所有数字也被覆盖

        //假设数字 x 缺失，则至少需要在数组中补充一个小于或等于 x 的数，才能覆盖到 x，否则无法覆盖到 x

        //区间[1,x-1]内的所有数字都已经被覆盖，则从贪心的角度考虑，补充 x 之后即可覆盖到 x，且满足补充的数字个数最少。
        // 在补充 x 之后，区间[1,2x-1]内的所有数字都被覆盖，下一个缺失的数字一定不会小于 2x

        //由此可以提出一个贪心的方案。每次找到未被数组 nums 覆盖的最小的整数 x，在数组中补充x，然后寻找下一个未被覆盖的最小的整数，
        // 重复上述步骤直到区间[1,n]中所有的数字都被覆盖

        //具体实现方面，任何时候都应满足区间[1,x-1]内所有数字被覆盖，令x的初始值为1，数组下标 index 的初始值为 0，则初始状态下区间[1,x-1]为空区间
        //满足区间内的所有数字都被覆盖。进行如下操作。


        int patches = 0;// 初始化要补的次数为0次
        long x = 1;// 初始化x，此时区间为[0,0]
        //传入的初始数组的长度，初始化下标为0
        int length = nums.length, index = 0;// 从第0个位置开始

        while (x <= n){ // 能连续覆盖的重量还没到n，有两种情况：断码(缺砝码)，或者还有砝码没加，或者理解为退出条件为x大于n，因为x总是代表[1, x-1]被覆盖到

            if (index < length && nums[index] <= x){
                // 从x=1开始依次判断，每次如果x在数组中，则更新x为x+nums[index]，因为根据贪心思想，我们总保证区间小于x的所有值会被覆盖掉，
                // 因此x+1，x+2，... x+nums[index]-1都会被覆盖到，更新x += nums[index]。
                x += nums[index];
                index++;

                //上面判断语句可能不好理解，若现有砝码1, 4, 6, n为10，当一次循环后砝码1已计入x,x=2，但是nums[1]=4>2;
                //这说明断码了，我们需要的是重量为x=2的砝码，所以加一个，现在能称的重量就能覆盖到原来的2倍=4了（不到4）
                //然后nums[1]=4就等于4，4的砝码我们刚好有，加进来，现在能称的重量就能覆盖到原来的2倍=8了（不到8）
                //然后nums[2]=6<8，这个不是必需的砝码，加进来，现在能称的重量就能覆盖到8+6=14了（不到14）

            }else {
                // x不在数组中，则将x加入，覆盖范围变为2 * x - 1，更新 x *= 2
                x *= 2;
                patches++;
            }
        }

        return patches;

    }
}