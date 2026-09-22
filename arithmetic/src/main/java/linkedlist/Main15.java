package linkedlist;

import linkedlist.pojo.Node;
import linkedlist.util.LinkedListUtils;

// 左右中划分partition链表
// 两端划分
// 简单做法：使用node存放节点然后进行partition然后返回新链表
public class Main15 {
    public static void main(String[] args) {
        int nodeCounts = 10;
        Node head = LinkedListUtils.linkedListGenerator(nodeCounts, 1, 10);
//        int[] arr = {5, 6, 8, 5, 1, 10, 6, 6, 4, 9 };
//        Node head = MyUtils.linkedListGenerator(arr);
        Node copyHead = LinkedListUtils.copyLinkedList(head);
        LinkedListUtils.printLinkedList(head);
        LinkedListUtils.printLinkedList(threePartPartition(head,5));
        LinkedListUtils.printLinkedList(normalPartition(copyHead,5));
    }

    // 面试解法（限制空间复炸度为O(1)）
    public static Node threePartPartition(Node head, int num) {
        Node sH = null, sT = null; // 小于部分
        Node eH = null, eT = null; // 等于部分
        Node bH = null, bT = null; // 大于部分

        while (head != null) {
            if (head.value < num) {
                if(sH == null) {
                    sH = head;
                    sT = head;
                    head = head.next;
                    continue;
                }
                sT.next = head;
                sT = sT.next;
            } else if (head.value == num) {
                if(eH == null) {
                    eH = head;
                    eT = head;
                    head = head.next;
                    continue;
                }
                eT.next = head;
                eT = eT.next;
            } else {
                if(bH == null) {
                    bH = head;
                    bT = head;
                    head = head.next;
                    continue;
                }
                bT.next = head;
                bT = bT.next;
            }

            head = head.next;
        }

        // 屎山代码优化后
        // 1. 连接数据
        if(sT != null) sT.next = eT != null  ? eH : bH;
        if(eT != null) eT.next = bH;
        if(bT != null) bT.next = null;

        // 2. 确定头节点
        head = sH != null ? sH : eH;
        head = head != null ? head : bH;

        return head;
    }

    // 笔试解法（空间复杂度不限制）
    public static Node normalPartition(Node head, int num) {
        Node[] nodes = LinkedListUtils.linkedListToArray(head);

        int p1 = -1; // < 区域内边界
        int p2 = nodes.length; // > 区域内边界
        int i = 0; // 用来遍历的指针

        while(i < p2) {
            if(nodes[i].value < num) {
                LinkedListUtils.swap(nodes,i++,++p1);
            } else if(nodes[i].value == num) {
                i++;
            } else {
                LinkedListUtils.swap(nodes,i,--p2);
            }
        }

        for (int j = 1; j < nodes.length; j++) {
            nodes[j-1].next = nodes[j];
        }

        nodes[nodes.length-1].next = null;

        return nodes[0];
    }


}
