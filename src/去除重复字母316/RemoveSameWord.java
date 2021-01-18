package 去除重复字母316;

public class RemoveSameWord {
}
class Solution {

    //采用贪心+栈的方法
    public String removeDuplicateLetters(String s) {

        //标志位，记录栈中是否已经有该字母
        boolean[] vis= new boolean[26];

        //统计字符串中每个字符出现的次数
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++){
           //统计字符串中每个字符出现的次数
            // 按照字母的字典顺序统计
            num[s.charAt(i) - 'a'] ++;
        }

        //看作栈
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            //先看下标志位
            //如果栈中已经有这个数就不再考虑
            if (!vis[ch - 'a']){

                //看栈中是否有字母，而且要把栈顶元素和当前字母进行大小比较

                //有一步很关键：需要记录每个字符的剩余数量，当这个值为 0 时，就不能弹出栈顶字符了。
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch){
                    //说明后面还有栈顶元素，当前的这个栈顶元素可以移除
                    if (num[sb.charAt(sb.length() -1) - 'a'] > 0 ){
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    }else {
                        //如果后面没有这个元素了，就不能移除，同时
                        break;
                    }
                }

                //改变标志位并且入栈
                vis[ch - 'a'] = true;
                sb.append(ch);
            }

            num[ch - 'a'] -= 1;
        }

        return sb.toString();
    }
}