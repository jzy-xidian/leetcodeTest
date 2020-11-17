package 填充每一个节点下一个右侧节点指针116;

public class NextNode {
}


//此题个人思路是，关键在于如何解决2，3和5，6之间的联合关系
class Solution {
    public Node connect(Node root) {


        if (root.left != null && root.right != null) {
            connect(root.left);
            connect(root.right);

            root.left.right = root.right;
            root.right.right = null;

            if (root.left.right != null && root.right.left != null) {
                root.left.right.left = root.right.left;
            }

        }

        return root;
    }

    //实在没搞出来，看下别人的思路吧，不过递归的思想是正确的
    //按照层序遍历的思想，利用next，让当前访问节点的左节点的next为右节点
    //如果还有下一层，要考虑不同父节点的情况，即两个相邻节点，右节点的next等于右边父节点的左节点
    //先写while循环解法
    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }

        //从根节点开始从上到下检测
        Node leftMost = root;

        //采用前序遍历的思想，每次找最左节点，如果最左节点都没有左子树了，说明达到最后一层
        while (leftMost.left != null) {

            //用head来存储next关系
            Node head = leftMost;

            while (head != null) {

                head.left.next = head.right;

                //如果当前还有下一层，拼接下一层的不同父节点的两个子节点关系
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                head = head.next;
            }

            leftMost = leftMost.left;
        }

        return root;

    }

    //采用递归实现
    public Node connect3(Node root) {
        if (root == null) {
            return root;
        }

        if(root.left!=null){
            root.left.next = root.right;
            root.right.next = root.next!=null?root.next.left : null;
            connect(root.left);
            connect(root.right);
        }

        return root;

    }
}




class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}