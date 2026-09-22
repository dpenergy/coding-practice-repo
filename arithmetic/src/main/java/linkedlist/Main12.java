package linkedlist;

import linkedlist.pojo.Node;
import linkedlist.util.LinkedListUtils;

// 链表题目
public class Main12 {
    // 1. 反转单向和双向链表
    public static void main(String[] args) {
        final int nodeCounts = 50;
        Node head = singlyLinkedListGenerator(nodeCounts,1,100);

        // test singlyLinkedListInversion()
        LinkedListUtils.printLinkedList(head);
        head = singlyLinkedListInversion(singlyLinkedListInversion(head)); // 直接转换两次就可以边界的看是否反转成功了
        LinkedListUtils.printLinkedList(head);

        System.out.println("\n\n==================\n\n");

        DoubleNode head2 = doubleLinkedListGenerator(nodeCounts,1,100);
        printLinkedList(head2);
        head2 = doubleLinkedListInversion(doubleLinkedListInversion(head2));
        printLinkedList(head2);

    }

    /**
     * 反转双向链表
     * @param head 头节点
     * @return 返回反转后的链表的头节点
     */
    public static DoubleNode doubleLinkedListInversion(DoubleNode head) {
        DoubleNode next;
        DoubleNode cur = head;

        while ((next = cur.next) != null) {
            swapNextAndPrev(cur);
            cur = next;
        }

        swapNextAndPrev(cur);
        return cur;
    }

    /**
     * 交换一个双向链表节点的前驱指向和后继指向
     * @param doubleNode 一个双向链表的节点
     */
    public static void swapNextAndPrev(DoubleNode doubleNode) {
        DoubleNode temp = doubleNode.next;
        doubleNode.next = doubleNode.prev;
        doubleNode.prev = temp;
    }


    /**
     * 生成双向链表
     * @param nodeCounts 节点数量（包含头节点）
     * @param min,max 节点值范围
     * @return 返回双向链表的头节点
     */
    public static DoubleNode doubleLinkedListGenerator(int nodeCounts, int min, int max) {
        DoubleNode head = new DoubleNode(randomValueGenerator(min, max));
        DoubleNode tail = head;
        for (int i = 1; i < nodeCounts; i++) {
            DoubleNode newNode = new DoubleNode(randomValueGenerator(min, max));
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        return head;
    }


    /**
     * 单链表反转
     * @param head 头节点
     * @return 返回反转后的链表的头节点
     */
    public static Node singlyLinkedListInversion(Node head) {
        // 1. 单独处理head
        Node next = head.next;
        Node cur = head;

        head.next = null;
        Node prev = head;
        cur = next;

        while((next = cur.next) != null) { // 如果cur.next == null说明cur就是链表最后的一个节点
            cur.next = prev; // 改变指向
            prev = cur; // 改变指向后的节点变成prev节点
            cur = next; // 同时原先的next节点变为cur节点
        }

        cur.next = prev;

        return cur;
    }



    /**
     * 单向链表随机生成器
     * @param nodeCounts 传入需要生成的链表的节点数量
     * @param min,max 随机生成节点的值的范围
     * @return 返回单向链表的头节点（包含数据）
     */
    public static Node singlyLinkedListGenerator(int nodeCounts, int min, int max) {
        Node head = new Node(randomValueGenerator(min, max));
        Node tail = head;
        for (int i = 1; i < nodeCounts; i++) { // i从1开始是因为我们的头节点已经创建了
            Node newNode = new Node(randomValueGenerator(min, max));
            tail.next = newNode;
            tail = newNode;
        }

        return head;
    }

    /**
     * 返回[min,max]范围内的整数
     * @param min 最小值
     * @param max 最大值
     * @return 返回随机生成的值
     */
    public static int randomValueGenerator(int min, int max) {
        return (int)(Math.random() * (max - min +1) + min);
    }



    /**
     * 重载printSinglyLinkedList方法
     * @param head 头节点
     */
    public static void printLinkedList(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

}

// 单向链表节点


// 双链表节点
class DoubleNode {
    int value;
    DoubleNode prev;
    DoubleNode next;

    // 因为Node类中没有定义空参构造，所以必须显示调用父类的构造方法super(int value)
    public DoubleNode(int value) {
        this.value = value;
    }
}