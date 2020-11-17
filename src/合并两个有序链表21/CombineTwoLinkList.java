package 合并两个有序链表21;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CombineTwoLinkList {
}
class Solution {

    //个人思路：直接打乱重排
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        List<Integer> list = new ArrayList<>();

        while (l1!= null){
            list.add(l1.val);
            l1 = l1.next;
        }
        while (l2!= null){
            list.add(l2.val);
            l2 = l2.next;
        }

        Collections.sort(list);

        ListNode node = new ListNode(list.get(0));
        list.remove(0);

        ListNode head = node;

        while (list.size()!= 0){

            ListNode temp = new ListNode(list.get(0));
            node.next = temp;
            node = node.next;

        }

        return head;

    }


    //先用最常规的迭代吧
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2){

        ListNode preHead = new ListNode(-1);

        ListNode pre = preHead;

        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else {
                pre.next = l2;
                l2 = l2.next;
            }

            pre = pre.next;
        }

        //整合没有被合并完的那一部分
        pre.next = l1 == null ? l2 : l1;

        return preHead.next;


    }

    //看下递归方法
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2){
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }



    }

}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }