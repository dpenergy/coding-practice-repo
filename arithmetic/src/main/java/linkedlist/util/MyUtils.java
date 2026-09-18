package linkedlist.util;
import linkedlist.pojo.Node;
import linkedlist.pojo.SpecialNode;

public class MyUtils {
    //swap是程序员
    public static void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j]) return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void swap(Node[] arr, int i, int j) {
        if (arr[i] == arr[j]) return;

        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 随机数组生成器
     * @param size 数组大小
     * @param min,max 数组值的范围
     * @return 返回一个数组
     */
    public static int[] randomArrayGenerator(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1) + min);
        }

        return arr;
    }


    /**
     * 回文数组生成器(如果是奇数大小的数组，那么最中间的值就设置为0)
     * @return 返回一个回文数组
     */
    public static int[] palindromeArrayGenerator(int size, int min, int max) {
        int halfSize = size/2; // 避免魔法数字，赋予变量语义
        int[] res = new int[size];

        int[] halfArray = MyUtils.randomArrayGenerator(halfSize, min, max);

        System.arraycopy(halfArray, 0, res, 0, halfSize);

        halfArray = arrInversion(halfArray);

        int inversionStartPlace = halfSize;
        inversionStartPlace = (size % 2 == 0) ? inversionStartPlace : inversionStartPlace + 1;

        System.arraycopy(halfArray, 0, res, inversionStartPlace, halfSize);

        return res;
    }


    /**
     * 数组反转方法
     * @return 返回反转后的新数组
     */
    public static int[] arrInversion(int[] arr) {
        int size = arr.length;
        int[] copyArr = new int[size];

        // 首位交换，奇偶都正确处理中间值
        for (int i = 0; i <= size / 2; i++) {
            copyArr[i] = arr[size - 1 -i];
            copyArr[size - 1 -i] = arr[i];
        }

        return copyArr;
    }

    /**
     * 链表生成器
     */
    public static Node linkedListGenerator(int nodeCounts, int min, int max) {
        Node head = new Node((int)(Math.random() * (max - min + 1) + min));
        Node tail = head;

        for (int i = 1; i < nodeCounts; i++) {
            tail.next = new Node((int)(Math.random() * (max - min + 1) + min));
            tail = tail.next;
        }

        return head;
    }

    /**
     * 按照数组生成链表
     */
    public static Node linkedListGenerator(int[] arr) {
        Node head = new Node(arr[0]);
        Node tail = head;
        for (int i = 1; i < arr.length; i++) {
            tail.next = new Node(arr[i]);
            tail = tail.next;
        }

        return head;
    }

    /**
     * 将单链表值输出到控制台
     * @param head 头节点
     */
    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printLinkedList(SpecialNode head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }

        System.out.println();
    }

    /**
     * 将链表节点放到数组并返回
     */
    public static Node[] linkedListToArray(Node head) {
        // 遍历一遍获取节点数量
        Node tempHead = head;
        int nodeCounts = 0;
        while (tempHead != null) {
            tempHead = tempHead.next;
            nodeCounts++;
        }

        Node[] nodes = new Node[nodeCounts];
        tempHead = head;

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = tempHead;
            tempHead = tempHead.next;
        }

        return nodes;
    }

    /**
     * 拷贝链表
     */
    public static Node copyLinkedList(Node head) {
        Node newHead = new Node(head.value);
        Node newTail = newHead;
        while(head.next != null) {
            head = head.next;
            newTail.next = new Node(head.value);
            newTail = newTail.next;
        }

        return newHead;
    }

    /**
     * 特殊链表生成器（带有randomPointer指针）
     */
    public static SpecialNode specialLinkedListGenerator(int nodeCounts, int min, int max) {
        SpecialNode[] nodes = new SpecialNode[nodeCounts];

        // 1. 生成基础链表
        SpecialNode head = new SpecialNode((int)(Math.random() * (max - min + 1) + min));
        SpecialNode tail = head;
        nodes[0] = head;

        for (int i = 1; i < nodeCounts; i++) {
            tail.next =  new SpecialNode((int)(Math.random() * (max - min + 1) + min));
            tail = tail.next;
            nodes[i] = tail;
        }

        // 2. 处理随机指针
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].randomPointer = randomChoiceSpecialNode(nodes);
        }

        return head;
    }

    /**
     * 随机返回数组里面的一个节点或者返回一个null(0~length为length的时候返回null)
     */
    public static SpecialNode randomChoiceSpecialNode(SpecialNode[] nodes) {
        int index = (int)(Math.random()* ((nodes.length) + 1));
        if(index == nodes.length) {
            return null;
        }

        return nodes[index];
    }

}
