package linkedlist;

import linkedlist.pojo.TreeNode;
import linkedlist.util.BinaryTreeUtils;

// 二叉树：递归序
public class Main19 {
    public static void main(String[] args) {
        TreeNode root = BinaryTreeUtils.binaryTreeGenerator(99,1,100);
//        BinaryTreeUtils.printBinaryTree(root);
        BinaryTreeUtils.myPrintBinaryTree(root);
    }
}
