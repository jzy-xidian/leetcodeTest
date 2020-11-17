package 两两交换链表中的节点24;

import java.util.ArrayList;
import java.util.List;

public class TwoNodeExchange {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(9);
        ListNode node2 =new ListNode(2);
        ListNode node3 =new ListNode(5);
        head.next = node1;
        head.next.next = node2;
        head.next.next.next = node3;
        Solution solution = new Solution();
        ListNode result = solution.swapPairs(head);
        System.out.println(result.val);
        System.out.println(result.next.val);
        System.out.println(result.next.next.val);

        System.out.println(result.next.next.next.val);


    }
}

class Solution {

    //自己的思路：采用队列模式，用两个队列交替入队出队，然后组成新的链表
    public ListNode swapPairs(ListNode head) {

        ListNode node1 = head;
        ListNode node2 = head.next;

        List<ListNode> list1 = new ArrayList<>();
        List<ListNode> list2 = new ArrayList<>();

        //先入队
        while (node1!= null){

            list1.add(node2);
            list2.add(node1);
            node1 = node2.next;
            if (node1 == null){
                break;
            }
            if (node1.next == null){
                list2.add(node1);
                node1 = node1.next;
            }
            node2 = node1.next;

        }

        node1 = list1.get(0);
        node2 = list2.get(0);
        node1.next = node2;

        ListNode temp = node1;

        list1.remove(0);
        list2.remove(0);

        //这里一定要注意，判断队列是否还有值要用size而不是用null
        while (list1.size() != 0 && list2.size()!=0){
            node1 = list1.get(0);
            node2.next = node1;
            node2 = list2.get(0);
            node1.next = node2;

            list1.remove(0);
            list2.remove(0);
        }
        if (list1.size()!=0){
            node2.next = list1.get(0);
            list1.remove(0);
        }


        return temp;

    }

    //很奇怪自己验证结果并没有问题，但是提交出错，看一下别人的解答

    //第一种是递归的方法
//    递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
//
//    如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，
//    原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。在对链表中的其余节点递归地两两交换之后，
//    更新节点之间的指针关系，即可完成整个链表的两两交换。
//
//    用 head 表示原始链表的头节点，新的链表的第二个节点，用 newHead 表示新的链表的头节点，
//    原始链表的第二个节点，则原始链表中的其余节点的头节点是 newHead.next。令 head.next = swapPairs(newHead.next)，
//    表示将其余节点进行两两交换，交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，
//    即完成了所有节点的交换。最后返回新的链表的头节点 newHead。


    public ListNode swapPairs2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs2(newHead.next);
        newHead.next = head;
        return newHead;

    }

    //第二种就是采用迭代法的思想
//    创建哑结点 dummyHead，令 dummyHead.next = head。令 temp 表示当前到达的节点，
//    初始时 temp = dummyHead。每次需要交换 temp 后面的两个节点。
//    如果 temp 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换。
//    否则，获得 temp 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点。


    public ListNode swapPairs3(ListNode head){
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null){
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }

        return dummyHead.next;

    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}