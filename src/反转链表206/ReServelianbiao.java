package 反转链表206;

import java.util.List;

public class ReServelianbiao {
}
class Solution {

    //第一种方法就是入栈
    //采用递归试试
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null){
            return head;
        }

        //直接找到最后的点，然后作为头
        ListNode newHead = reverseList(head.next);

        //反着指向
        head.next.next = head;

        //断掉节点
        head.next = null;


        //为了保持头节点
        return newHead;


    }

    //迭代
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {

            //提取出下一个节点
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;




    }




}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}