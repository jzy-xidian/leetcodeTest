package 删除字符串中的所有相邻重复项1047;


import java.util.Deque;

public class DeleteRepeateItem {
}
class Solution {

    //采用入栈对比的方式，一样的话直接弹出
    public String removeDuplicates(String S) {


        //用来统计坐标
        int top = -1;
        StringBuffer stack = new StringBuffer();

        for (int i = 0; i < S.length(); i++){

            char ch  = S.charAt(i);
            if (top>=0 && ch == stack.charAt(top)){
                stack.deleteCharAt(top);

                //一定要注意更新索引
                --top;
            }else {
                stack.append(ch);

                ++top;
            }


        }

        return stack.toString();

    }
}