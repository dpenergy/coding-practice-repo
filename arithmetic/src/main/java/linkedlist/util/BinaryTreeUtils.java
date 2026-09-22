package linkedlist.util;

import linkedlist.pojo.TreeNode;

public class BinaryTreeUtils {

    /**
     * 生成完成二叉树
     *
     * @return root根节点
     */
    public static TreeNode binaryTreeGenerator(int nodeCounts, int min, int max) {
        // 1. 将所有节点创建出来，并放到数组里面
        TreeNode[] nodes = new TreeNode[nodeCounts];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new TreeNode((int) (Math.random() * (max - min + 1) + min));
        }

        // 2. 按照数组的索引关系将二叉树的节点连接起来形成二叉树
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].left = 2 * i + 1 <= nodes.length - 1 ? nodes[2 * i + 1] : null;
            nodes[i].right = 2 * i + 2 <= nodes.length - 1 ? nodes[2 * i + 2] : null;
        }

        return nodes[0];
    }

    /**
     * 直观打印树的结构（包括各种二叉树）
     */
    public static void printBinaryTree(TreeNode root) {
        if (root == null) {
            System.out.println("(empty)");
            return;
        }

        // 右子树打印在上方
        printSubTree(root.right, "", false);

        // 打印根节点
        System.out.println(root.value);

        // 左子树打印在下方
        printSubTree(root.left, "", true);
    }

    private static void printSubTree(TreeNode node, String prefix, boolean isLeftChild){
        if (node == null) {
            return;
        }

        /*
         * 先打印右子树，使右子树出现在当前节点上方
         */
        if (node.right != null) {
            String nextPrefix = prefix + (isLeftChild ? "│   " : "   ");
            printSubTree(node.right, nextPrefix, false);
        }

        /*
         * 当前节点：
         * 右孩子使用 ┌──
         * 左孩子使用 └──
         */
        String branch = isLeftChild ? "└── " : "┌── ";
        System.out.println(prefix + branch + node.value);

        /*
         * 再打印左子树，使左子树出现在当前节点下方
         */
        if (node.left != null) {
            String nextPrefix = prefix + (isLeftChild ? "    " : "│   ");
            printSubTree(node.left, nextPrefix, true);
        }
    }


    public static void myPrintBinaryTree(TreeNode root) {
        if (root == null) return;

        // 首先打印右子树
        myPrintSubTree(root.right, "", true);
        // 再打印根节点
        System.out.println(root.value);

        // 最后打印左子树
        myPrintSubTree(root.left, "", false);
    }

    private static void myPrintSubTree(TreeNode cur,String prefix, boolean isRightChild) {
        if (cur == null) return;

        // 首先递归到最右
        if(cur.right != null) {
            String nextPrefix = prefix + (isRightChild ? "    ":"│   ");
            myPrintSubTree(cur.right, nextPrefix, true);
        }

        // 打印cur节点
        String branch = isRightChild ? "┌── " : "└── ";
        System.out.println(prefix + branch +cur.value);

        // 在递归打印左子树
        if(cur.left != null) {
            String nextPrefix = prefix + (isRightChild ? "│   " : "    ");
            myPrintSubTree(cur.left, nextPrefix, false);
        }
    }


}
