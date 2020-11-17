package 求根到叶子节点数字之和129;

public class rootToleaves {
}
class Solution {
    //这个题肯定是要用递归的，类似深度优先遍历
    public int sumNumbers(TreeNode root) {

        if (root == null){
            return 0;
        }

        return nextValue(root);
    }

    //按照一层一层往上走，让左边的数字和右边的数字每次每次累加，最终得到的结果就是根到叶子的所有数字的和
    //好像自底向上有点问题，换一个思路，自顶向下试试
    //当前节点如果还有下一层，传入节点*10，然后再加上左子树或者右子树的值，递归去做，直到找到叶子
    public int nextValue(TreeNode node){


        int leftResult = 0;
        int rightResult = 0;

        if (node.left != null){
            int left = nextValue(node.left);
            leftResult = node.val*10 + left;
        }

        if (node.right != null){
            int right = nextValue(node.right);
            rightResult = node.val*10 + right;
        }

        return leftResult + rightResult;

    }

    //在乘10这一步骤好像出现了问题，看看官方解答
    public int sumNumbers2(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }


}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }