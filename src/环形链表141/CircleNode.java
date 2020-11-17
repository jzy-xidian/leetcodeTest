package 环形链表141;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CircleNode {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {


        //容易想到的方法是遍历所有节点，每次遍历到一个节点时，判断该节点此前是否被访问过。
        //但这个我是真的想不到
        //本质存的是hashcode，而不是value
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;

        }
        return false;

    }

    //这个题要采用快慢指针
    //我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。
    // 初始时，慢指针在位置 head，而快指针在位置 head.next。这样一来，如果在移动的过程中，
    // 快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。

    public boolean hasCycle2(ListNode head) {

       if(head == null || head.next == null){
           return false;
       }

       ListNode slow = head;
       ListNode fast = head.next;

       while (slow != fast){
           if (fast == null || fast.next == null){
               return false;
           }
           slow = slow.next;
           fast = fast.next.next;
       }

        return true;
    }
}



class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
}