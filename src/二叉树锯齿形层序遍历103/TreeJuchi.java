package 二叉树锯齿形层序遍历103;

import java.util.*;

public class TreeJuchi {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.right = node5;
        node3.left = node4;

        Solution solution = new Solution();
        List<List<Integer>> listAll = solution.zigzagLevelOrder(node1);


        for (List<Integer> list : listAll){
            for (Integer id : list){
                System.out.print(id + " ");
            }

            System.out.println();
        }

    }




}
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        //加个flag，每次递归改变一次flag即可，也就是调整顺序
        //根据根节点开始向下找
        boolean flag = false;
        List<List<Integer>> listAll = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = diGui(root,listAll,flag);
        listAll.add(0,list2);
        list.add(root.val);
        listAll.add(0,list);

        return listAll;




    }
    public List<Integer> diGui(TreeNode root,List<List<Integer>> listAll,boolean flag){

        if(flag){
            List<Integer> list = new ArrayList<>();
            flag = !flag;
            if(root.left != null){
                //diGui(root.left,listAll,flag);
                list.add(root.left.val);
            }
            if(root.right != null){
                list.add(root.right.val);
            }

            List<Integer> right = diGui(root.right,listAll,flag);
            List<Integer> left = diGui(root.left,listAll,flag);

            right.addAll(left);
            listAll.add(0,right);

            return list;



        }else{

            List<Integer> list = new ArrayList<>();
            flag = !flag;
            if(root.right != null){
                //diGui(root.left,listAll,flag);
                list.add(root.right.val);
            }
            if(root.left != null){
                list.add(root.left.val);
            }

            List<Integer> left = diGui(root.left,listAll,flag);
            List<Integer> right = diGui(root.right,listAll,flag);

            left.addAll(right);

            listAll.add(0,left);

            return list;

        }



    }

    //方法不太对，试试广度优先队列

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root){
        //加个flag，每次递归改变一次flag即可，也就是调整顺序
        //根据根节点开始向下找

        List<List<Integer>> listAll = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();

        if(root == null){
            return listAll;
        }

        //用来变换存储方向
        boolean isleft = true;

        queue.offer(root);
        //这个题要用到双端队列，要用

        while(!queue.isEmpty()){
            //采用双端队列
            Deque<Integer> list = new LinkedList<>();

            int size = queue.size();
            for(int i = 0; i < size; i++){

                TreeNode node = queue.poll();

                if(isleft){

                    list.offerLast(node.val);
                }else{
                    list.offerFirst(node.val);
                }

                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }

            }

            //这里不能直接把队列加进去，要把队列转换为linkedlist,向下转型
            listAll.add(new LinkedList<Integer>(list));
            isleft = !isleft;

        }

        return listAll;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }