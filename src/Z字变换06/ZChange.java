package Z字变换06;

import java.util.ArrayList;
import java.util.List;

/*
* 先判断有几行，每行作为一个stringbuilder进行存储
* 先将每行的stringbuilder存入总的row对应的list中，用于按行输出
* 直接采用1232123212321这样的存储方式即可
*
* */
public class ZChange {
    public static void main(String[] args) {



    }
}

class Solution {

    public String convert(String s, int numRows) {

        //如果只有一行，直接输出字符串即可
        if(numRows < 2) return s;

        //用来存储每行的StringBuilder
        List<StringBuilder> rows = new ArrayList<StringBuilder>();

        //根据输入的行数，创建与行数相同的StringBuilder
        for(int i = 0; i < numRows; i++){
            rows.add(new StringBuilder());
        }

        //设定一个标志位，i是行的初始位置，表示从row中取出第几个String Builder
        int i = 0, flag = -1;


        for(char c : s.toCharArray()) {

            //取出StringBuilder后，按string的顺序依次插入字符
            rows.get(i).append(c);

            if(i == 0 || i == numRows -1) {
                //当达到边界后，标志为转换，第一行为正，到最后一行时变为负
                flag = - flag;
            }
            //按行下标推进或回退
            i += flag;
        }

        //全部执行完之后，重新将三个StringBuilder取出，拼接进一个StringBuilder,最后输出
        StringBuilder res = new StringBuilder();

        for(StringBuilder row : rows) {
            res.append(row);
        }

        return res.toString();
    }

}


