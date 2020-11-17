package 长按键入925;

public class LongPress {
}

class Solution {

    //自己的思路：对比输入和键盘键入，先看是name输入是否连续，如果不连续，从键盘键入开始与name进行对比
    //如果一样的话，两个指针都要后移，如果出现不同，返回false，可以采用统计数量的方式，看键盘键入的数量是否大于等于输入的
    //解法还是有问题
    public boolean isLongPressedName(String name, String typed) {

        int i = 0;
        int j = 0;
        int count1 = 1;
        int count2 = 1;
        while (i < name.length()){

            char a = name.charAt(i);
            if (i > 0){
                if (a == name.charAt(i -1)){
                    count1++;
                }
                else {
                    count1 = 1;
                }
            }
            while (j < typed.length()){

                char b = typed.charAt(j);
                if (j > 0){
                    if (b == typed.charAt(i -1)){
                        count2++;
                    }
                    else {
                        count2 = 1;
                    }
                }
            }
//            if ()
//
//            i++;
//            j++;
        }

        return false;

    }

    //看一下官方思路
    //这种题还是采用双指针的方式
    public boolean isLongPressedName2(String name, String typed){
        int i = 0,j = 0;
        //只需要按键盘键入的情况进行判别即可
        while (j < typed.length()){
            if (i < name.length() && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(j > 0 && typed.charAt(j) == typed.charAt(j-1)){
                j++;
            }
            else {
                return false;
            }

        }
        return i == name.length();
    }
}
