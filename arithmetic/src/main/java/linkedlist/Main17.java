package linkedlist;

import linkedlist.pojo.SpecialNode;
import linkedlist.util.MyUtils;

public class Main17 {
    public static void main(String[] args) {
        int size = 10;
        SpecialNode head = MyUtils.specialLinkedListGenerator(size,1,100);
        MyUtils.printLinkedList(head);
        MyUtils.printLinkedList(copySpecialLinkedList(head));
        MyUtils.printLinkedList(normalCopySpecialLinkedList(head));
    }

    // 不考虑额外空间的方法
    public static SpecialNode normalCopySpecialLinkedList(SpecialNode head) {
        int nodeCounts  = 0;
        SpecialNode tempHead = head;
        while (tempHead != null) {
            nodeCounts++;
            tempHead = tempHead.next;
        }

        SpecialNode[] nodes = new SpecialNode[nodeCounts];

        tempHead = head;

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = tempHead;
            tempHead = tempHead.next;
        }

        SpecialNode copyHead = new SpecialNode(nodes[0].value,nodes[0].next,nodes[0].randomPointer);
        SpecialNode copyTail = copyHead;
        for (int i = 1; i < nodes.length; i++) {
            copyTail.next = new SpecialNode(nodes[i].value,nodes[i].next,nodes[i].randomPointer);
            copyTail = copyTail.next;
        }

        return copyHead;
    }


    // 考虑额外空间O(1)的方法
    public static SpecialNode copySpecialLinkedList(SpecialNode head) {

        // 1. 每一个节点后面插入一个复制节点
        SpecialNode cur = head;
        SpecialNode next;

        while((next = cur.next) != null){
            SpecialNode newNode = new SpecialNode(cur.value);
            cur.next = newNode;
            newNode.next = next;
            cur = next;
        }

        cur.next = new SpecialNode(cur.value);

//        // 验证一下这一步的正确性
//        MyUtils.printLinkedList(head);

        // 2. 映射randomPointer指针
        cur = head;
        SpecialNode copy = cur.next;
        while(cur != null){
            copy.randomPointer = cur.randomPointer != null ? cur.randomPointer.next : null;
            cur = copy.next;
            copy = cur != null ? cur.next : null;
        }

        // 3. 还原原链表并组织新链表
        cur = head;
        copy = cur.next;
        SpecialNode copyHead = copy;
        while(cur != null){
            cur.next = copy.next;
            copy.next = copy.next != null ? copy.next.next : null;
            cur = cur.next;
            copy = cur != null ? cur.next : null;
        }

        return copyHead;
    }
}
