package 反转字符串344;

public class WayToReverseString {
}

//注意题目要求：不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题

//自己的思路：不能分配空间，采用双指针，两边进行交换即可
class Solution {
    public void reverseString(char[] s) {

        int length = s.length;

        for (int i=0, left = 0,right = length-1; left < right; left ++,right--){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }

    }
}