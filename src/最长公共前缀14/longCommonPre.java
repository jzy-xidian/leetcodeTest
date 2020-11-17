package 最长公共前缀14;

public class longCommonPre {

    public static void main(String[] args) {
        String[] strings = {"ab","a"};
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix(strings));
    }
}

class Solution {

    //自己的思路：每次截取一位，然后进行对比
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        if(strs.length == 1){
            return strs[0];
        }

        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = true;
        //注意越界和长度问题
        for (int i = 0; i < strs[0].length(); i++){

            if (flag == false){
                break;
            }

            char str = strs[0].charAt(i);
            for (int j = 1; j <strs.length; j++){

                if (i >= strs[j].length()){
                    flag  = false;
                    break;
                }

                if (str == (strs[j].charAt(i)) && j == strs.length-1){
                    stringBuilder.append(str);
                }
                else if (str == (strs[j].charAt(i))){
                    continue;
                }
                else {
                    flag  = false;
                    break;
                }

            }


        }

        return stringBuilder.toString();
    }
}