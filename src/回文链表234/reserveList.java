package 回文链表234;

import java.util.ArrayList;

public class reserveList {

    public static void main(String[] args) {
        ListNode head = new ListNode(-129);
        ListNode node1 = new ListNode(-129);
        head.next = node1;

        Solution solution = new Solution();
        System.out.println( solution.isPalindrome(head));
    }
}


class Solution {

    //直接采用数组存储，然后双指针进行回文对比
    public boolean isPalindrome(ListNode head) {

        if (head == null ){
            return false;
        }

        boolean flag = true;

        ArrayList<Integer> list = new ArrayList<>();
        while (head.next != null){
            list.add(head.val);
            head = head.next;
        }
        list.add(head.val);
        int start = 0;
        int end = list.size()-1;

        //切记，如果是Integer类型，一定要用.equals来比较！涨知识了。。。

        while (start < end){
            int a = list.get(start);
            int b = list.get(end);
            Integer c = list.get(start);
            Integer d = list.get(end);

            if (!c.equals(d)){
                System.out.println(list.get(start));
                System.out.println(list.get(end));
                System.out.println("------------------------------------");
                System.out.println(list.get(start) != list.get(end));
                System.out.println(a != b);
                System.out.println(c != d);
                System.out.println(-129 != -129);
                flag = false;
                break;
            }
            start++;
            end --;

        }

        return flag;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }