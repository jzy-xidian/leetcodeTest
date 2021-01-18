package 排序链表148;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSort {
}
class Solution {
    public ListNode sortList(ListNode head) {
        //最适合链表的排序算法是归并排序。
        //
        //归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，
        if (head == null) return null;
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        Collections.sort(list, (n1, n2) -> n1.val - n2.val);
        for (int i = 1; i < list.size(); i++) {
            list.get(i - 1).next = list.get(i);
        }
        list.get(list.size() - 1).next = null;
        return list.get(0);


    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }