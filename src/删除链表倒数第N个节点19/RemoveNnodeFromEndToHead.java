package 删除链表倒数第N个节点19;

public class RemoveNnodeFromEndToHead {
}

class Solution {

    //自己的思路：把整个链表进行反转，然后删除（这种应该是常规思维）
    //思想没有问题，但是写的太傻逼，看看答案把，写的太复杂
    public ListNode removeNthFromEnd(ListNode head, int n) {

        if(head == null){
            return null;
        }
        if(head.next == null){
            if(n == 1){
                return head;
            }
            else{
                return null;
            }
        }

        ListNode node1 = head;
        ListNode node2 = head.next;
        ListNode node3 = new ListNode();
        ListNode temp = new ListNode();
        ListNode temp2 = new ListNode();
        ListNode temp3 = new ListNode();
        temp = node3;

        while(node1.next != null){
            reverse(node1,node2,node3);
        }

        temp2 = temp;
        temp3 = temp.next;
        int count = 1;
        while(count!=n){
            temp2 = temp.next;
            temp3 = temp2.next;
            count++;
        }

        temp2.next = temp3.next;

        return temp;

    }

    public void reverse(ListNode node1,ListNode node2,ListNode node3){

        if(node1.next == null){
            node3.next = node1;
            node3 = node3.next;
        }

        if(node2.next == null){
            node3.next = node2;
            node1.next = null;
            node3 = node3.next;

        }else{
            reverse(node1.next,node2.next,node3);
        }

    }


    //真正的解法其实并不需要递归
    public ListNode removeNthFromEnd2(ListNode head, int n){

        //先设置一个头节点，0值，指向真正的头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode seconde = dummy;
        //这里是算法的核心，如果n=1，为什么要从0开始，走两步呢
        //是因为second作为标识计算，要让second.next = second.next.next来删除对应节点，
        //正好second.next就是要删除的倒数第N个节点，所以这就是让first多走一步的原因
        ///且当first = null时，seconde所位移的地方，下一个节点就是要删除的。
        for (int i = 0; i <=n; i++){
            first = first.next;
        }

        while (first != null){
            first =first.next;
            seconde = seconde.next;
        }

        seconde.next = seconde.next.next;
        return dummy.next;
    }
}


class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}