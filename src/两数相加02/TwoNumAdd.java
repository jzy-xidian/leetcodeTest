package 两数相加02;

import sun.java2d.pipe.SolidTextRenderer;

public class TwoNumAdd {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);

        node4.next = node5;
        node5.next = node6;

        Solution solution =new Solution();

        ListNode head = solution.addTwoNumbers(node1,node4);
        System.out.println(head.val + "," + head.next.val + ","+ head.next.next.val);
    }
}


class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);

        ListNode p = l1,q = l2, resultNode =head;
        int carry = 0;

        while (p!=null || q!=null){

            int a1 = (p == null) ? 0 : p.val;
            int a2 = (q == null) ? 0 : q.val;
            int sum = a1 + a2 + carry;
            carry = sum / 10;

            resultNode.next = new ListNode(sum % 10);

            resultNode = resultNode.next;

            if (p != null){
                p = p.next;
            }

            if (q != null){
                q = q.next;
            }
        }

        if(carry > 0){

            resultNode.next = new ListNode(carry);
        }


        return head.next;
    }
}

class ListNode {


    int val;
    ListNode next;

    public ListNode(){

    }


    public ListNode(int x){
        val = x;
    }
}