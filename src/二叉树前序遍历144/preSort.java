package 二叉树前序遍历144;

import java.util.ArrayList;
import java.util.List;

public class preSort {
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {



        ArrayList<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }
        list.add(root.val);

        if (root.left != null){
            list.addAll(preorderTraversal(root.left));
        }
        if (root.right != null){
            list.addAll(preorderTraversal(root.right));
        }

        return list;


    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
         this.right = right;
      }
}
