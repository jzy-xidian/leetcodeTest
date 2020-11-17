package 重排链表143;

import java.util.ArrayList;
import java.util.List;

public class ReorderNode {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next  = node4;

        System.out.println(head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);
        System.out.println(head.next.next.next.val);
        System.out.println(head.next.next.next.next.val);

        Solution solution = new Solution();

        solution.reorderList(head);

        System.out.println(head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);
        System.out.println(head.next.next.next.val);
        System.out.println(head.next.next.next.next.val);
    }

}

class Solution {
    //给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    //将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

    //自己的思路：根据以前的做题思路，采用双指针，构造哑节点，断链重连
    //但是这个和前后交换还不一样，这个要进行的是首尾交换，如果直接交换，需要直接遍历到结尾，然后回退
    //感觉这个题还是需要递归，令temp指向head，用于控制前半段，递归控制后半段
    //如果是数组，可以考虑二分法，但是这里是链表
    public void reorderList(ListNode head) {

        if (head == null || head.next == null){
            return;
        }

        ListNode newHead = head;
        ListNode temp = newHead.next;
        ListNode tail = temp;
        while (temp.next != null){
            tail = swap(tail);
            newHead.next = tail.next;
            tail.next.next = temp;
            tail.next = null;

            newHead = temp;

            if (temp.next == null){
                break;
            }else {
                temp = temp.next;
                tail = temp;
            }

        }

    }public ListNode swap(ListNode tail){
        ListNode result = new ListNode();
        if (tail.next != null && tail.next.next != null){
            result = swap(tail.next);
        }
        else if (tail.next !=null && tail.next.next ==null){
            return tail;
        }

        return result;
    }

    //思路没有大问题，但是没有考虑一些边界问题，看下答案的解法吧
//    为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。
//
//    因此比较容易想到的一个方法是，我们利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。
    public void reorderList2(ListNode head){
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;

        while (node != null){
            list.add(node);
            node = node.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j){
            list.get(i).next = list.get(j);
            i++;
            //用于判断是否到最后一个可交换点
            if(i == j){
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;

    }



}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}