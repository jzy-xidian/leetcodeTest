package 完全二叉树的节点个数222;

public class TheNodeOfTheTree {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.left = new TreeNode(6);

        Solution solution = new Solution();
        System.out.println(solution.countNodes(node));
    }
}

class Solution {

    //个人思路：这个必然要用递归
    //对于没有约束的二叉树而言，可以很简单地想到使用下面这个递归的解法：
    public int countNodes(TreeNode root) {

        if(root == null){
            return 0;
        }


        int count = 0;

        //把统计的结果返回就行
        return getNodeNum(root,count);


    }

    public int getNodeNum(TreeNode node,int count){

        int left = 0;
        int right = 0;

        if (node.left != null){
            left = getNodeNum(node.left,count);
        }

        if (node.right != null){
            right = getNodeNum(node.right,count);
        }

        return left + right + 1;
    }

    //但这是一个普适的解法，对于此题给的完全二叉树的特点没有利用起来，进一步考虑如何使用完全二叉树的特点更快解出此题。
    //这道题规定了给出的是完全二叉树，因此可以利用完全二叉树的特性计算节点个数。
    //对于层数为h的完全二叉树节点个数在2的h次方到2的h+1次方-1的范围内
    //它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
    public int countNodes2(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }



    }

    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }




}

class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
      TreeNode(int x) { val = x; }
  }