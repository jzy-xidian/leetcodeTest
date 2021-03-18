package 验证二叉树前序序列化331;

import java.util.Deque;
import java.util.LinkedList;

public class QIanxu {
}

class Solution {

    //采用栈
    //我们可以定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。
    //
    //二叉树的建立也伴随着槽位数量的变化。每当遇到一个节点时：
    //
    //如果遇到了空节点，则要消耗一个槽位；
    //
    //如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。

    //我们使用栈来维护槽位的变化。栈中的每个元素，代表了对应节点处剩余槽位的数量，
    // 而栈顶元素就对应着下一步可用的槽位数量。当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。
    // 无论何时，如果栈顶元素变为 0，就立刻将栈顶弹出。
    //
    //遍历结束后，若栈为空，说明没有待填充的槽位，因此是一个合法序列；否则若栈不为空，则序列不合法。此外，在遍历的过程中，若槽位数量不足，则序列不合法。
    //


    public boolean isValidSerialization(String preorder) {

        int n = preorder.length();
        int i = 0;
        Deque<Integer> stack = new LinkedList<>();

        stack.push(1);

        while (i < n){
            if (stack.isEmpty()){
                return false;
            }

            if (preorder.charAt(i) == ','){
                i++;
            }
            else if(preorder.charAt(i) == '#'){
                //'#'占一个槽位
                int top = stack.pop() - 1;
                if (top > 0){
                    stack.push(top);
                }
            }

            else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }

                //数字占一个槽位，同时要补充两个槽位
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }

                stack.push(2);

            }


        }
        return stack.isEmpty();
    }

    //第二种方法是计数
    //回顾方法一的逻辑，如果把栈中元素看成一个整体，即所有剩余槽位的数量，也能维护槽位的变化。
    public boolean isValidSerialization2(String preorder) {
        int n = preorder.length();
        int i = 0;
        int slots = 1;
        while (i < n) {
            if (slots == 0) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#'){
                slots--;
                i++;
            } else {
                // 读一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;
    }


}