package 二叉树层序遍历102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cengxu {
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> listAll = new ArrayList<>();

        if(root == null){
            return listAll;
        }

        //采用广度优先遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        //在while里面有个很关键的for循环，就是为了按层将节点的值存入list
        while(!queue.isEmpty()){

            List<Integer> list = new ArrayList<>();
            int size = queue.size();

            //只把该层的节点值取出并存入list，加入的子节点并不影响，实在下一次while循环才会poll出
            for(int i = 1; i <= size; i++){

                TreeNode node = queue.poll();
                list.add(node.val);

                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }

            listAll.add(list);
        }

        return listAll;



    }
}