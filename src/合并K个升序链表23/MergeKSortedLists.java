package 合并K个升序链表23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeKSortedLists {
}
class Solution {

    //思路：这个题set和map就别想了，两个键是唯一的
    //全存到list，然后排序，最后重新生成链表
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0){
            return null;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < lists.length; i++){
            while (lists[i] != null){
                list.add(lists[i].val);
                lists[i] = lists[i].next;
            }
        }

        if(list.size() == 0){
            return null;
        }

        Collections.sort(list);


        ListNode headNode = new ListNode(-1);

        ListNode nextNode =  new ListNode(list.get(0));
        headNode.next = nextNode;
        for (int i = 1; i < list.size(); i++){

            nextNode.next =  new ListNode(list.get(i));
            nextNode = nextNode.next;

        }

        return headNode.next;


    }
}

 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }