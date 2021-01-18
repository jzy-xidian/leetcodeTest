package 对链表进行插入排序147;

public class ListInsertSort {
}
class Solution {

    //个人思路，每次取出输入链表的头节点，再与新的链对比并插入
    public ListNode insertionSortList(ListNode head) {

        if (head == null){
            return null;
        }

        if (head.next == null){
            return head;
        }

        ListNode newHead = new ListNode(-1);

        //先把第一个取出来,和新的头节点链接，后面的节点开始判断大小并插入
        newHead.next = new ListNode(head.val);

        head = head.next;

        while (head != null){

            ListNode inertNode = new ListNode(head.val);

            //必定是有一个节点的
            ListNode start = newHead.next;

            //始终让头节点是最小的
            if (inertNode.val <= start.val){
                inertNode.next = start;
                newHead.next = inertNode;
            }

            //新链表中只有一个节点的情况，把新链表变成两个节点
            else if (inertNode.val > start.val && start.next == null){

                start.next = inertNode;

            }
            else {
                //开始对比插入
                //要插入的节点必然是大于第一个节点的
                ListNode end = start.next;

                while (inertNode.val > end.val && end.next != null){

                    start = start.next;
                    end = end.next;

                }

                if (end.next == null){
                    if (inertNode.val > end.val){
                        end.next = inertNode;
                    }else {
                        start.next = inertNode;
                        inertNode.next = end;
                    }
                }else {
                    //说明中途就可以插入
                    start.next = inertNode;
                    inertNode.next = end;
                }

            }


            //把原先的链表往前推进
            head = head.next;
        }

        return newHead.next;

    }

    //思想是一样的，官方的更简洁一些
    public ListNode insertionSortList2(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = new ListNode(0);
        ListNode node = head;
        while(node != null){
            ListNode next = node.next;
            //在排好序的链表中找到合适的位置, 插入
            ListNode prevNode = newHead;
            ListNode sortNode = newHead.next;
            while(sortNode != null){
                if(node.val > sortNode.val){
                    prevNode = sortNode;
                    sortNode = sortNode.next;
                }else{
                    break;
                }
            }
            prevNode.next = node;
            node.next = sortNode;

            node = next;
        }
        return newHead.next;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}