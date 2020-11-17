package 环形链表2_142;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CircleLinkNode2 {
}

class Solution {
    //思路：此次的环形链表需要返回连接的节点
    //之前的环形链表不需要返回连接节点，所以用快慢指针即可
    //可以采用map，每次的统计，K是节点的值，value作为节点的索引，如果有环形链表，
    public ListNode detectCycle(ListNode head) {

        if (head == null || head.next ==null){
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        while (head.next != null){
            if(!map.containsKey(head.val)){
                map.put(head.val,index);
                index++;

            }
            else {
                return head;
            }

            head = head.next;

        }

        return head;

    }
    public ListNode detectCycle2(ListNode head){
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;

    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
        next = null;
    }
}