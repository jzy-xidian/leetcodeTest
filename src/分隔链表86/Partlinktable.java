package 分隔链表86;

public class Partlinktable {
}

class Solution {
    public ListNode partition(ListNode head, int x) {

        ListNode small = new ListNode(0);

        //保存小串的头节点
        ListNode smallHead = small;

        ListNode large = new ListNode(0);

        //保存大串的头
        ListNode largeHead = large;

        //开始对原链表进行遍历
        while (head != null){
            if (head.val < x){
                small.next = head;
                small = small.next;
            }else {
                large.next = head;
                large = large.next;
            }

            head = head.next;
        }
        large.next = null;

        //两个串进行相连
        small.next = largeHead.next;

        return smallHead.next;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}