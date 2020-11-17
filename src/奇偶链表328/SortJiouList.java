package 奇偶链表328;

public class SortJiouList {
}

//感觉这个和昨天的奇偶数组排序有点像，但是这个是链表，而且空间复杂是O1

class Solution {

    //个人思路：双指针，然后两两判断交换
    //能让奇偶交替，说明坐标和奇偶数的数量是一定的

    //这次的空间复杂度，不允许再打乱重排链表，所以采用双指针试试
    public ListNode oddEvenList(ListNode head) {

        //先保存第一个奇数节点和第二个偶数节点

        ListNode start = new ListNode(-1);
        ListNode nextStart = new ListNode(-2);

        ListNode node1 = head;
        ListNode node2 = head.next;

        start.next = node1;

        nextStart.next = node2;

        //开始穿插保存
        while (node1 != null || node2 != null){

            ListNode node1Next = node1.next.next;
            ListNode node2Next = node2.next.next;

            if (node1Next != null){
                node1.next = node1Next;
                node1 = node1Next;
            }

            if (node2 != null){
                node2.next = node2Next;
                node2= node2Next;
            }
        }

        node1.next = nextStart.next;

        return start.next;




    }

    //没有考虑next的跨范围问题，感觉被卡死了好几次，看看题解

    //MD官方竟然真用的是打散重排的方式，分离奇偶节点形成奇数链表和偶数链表
    public ListNode oddEvenList2(ListNode head) {

        if (head == null){
            return null;
        }

        //带有even的保存的是偶数节点
        ListNode evenHead = head.next;
        ListNode odd = head,even = evenHead;

        //这里的判断都是以even为基准的
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;




    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}