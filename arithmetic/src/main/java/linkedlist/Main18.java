package linkedlist;

import linkedlist.pojo.Node;
import linkedlist.util.MyUtils;

import java.util.HashSet;
import java.util.Set;

// 链表相交问题（两个链表可能带环）
// 1. 因为链表可以带环，所以我们先对链条链表的带环情况进行判断，更具不同的状况采取不同的找首个相交节点的方法
public class Main18 {
    public static void main(String[] args) {
        // ========== 生成统一测试数据 ============
        //  1. 两条普通链表
        Node t1_head1 = MyUtils.linkedListGenerator(10,1,100);
        Node t1_head2 = MyUtils.linkedListGenerator(10,1,100);
        System.out.println(queryIntersectantNode(t1_head1, t1_head2));
        System.out.println();

        // 2. 两条环形链表
        Node t2_head1 = withLoopLinkedList(10,1,100);
        Node t2_head2 = withLoopLinkedList(10,1,100);
        System.out.println(queryIntersectantNode(t2_head1, t2_head2));
        System.out.println();

        // 3. 一条普通一条带环
        Node t3_head1 = MyUtils.linkedListGenerator(10,1,100);
        Node t3_head2 = withLoopLinkedList(10,1,100);
        System.out.println(queryIntersectantNode(t3_head1, t3_head2));
        System.out.println();

        // 4. 普通链表相交
        Node[] t4_nodes = intersectantLinkedListGenerator(13,1,100);
        Node t4_head1 = t4_nodes[0];
        Node t4_head2 = t4_nodes[1];
        System.out.println(queryIntersectantNode(t4_head1, t4_head2));
        System.out.println();

        // 5. >o形相交链表
        Node[] t5_nodes = intersectantLoopLinkedListGenerator(15,1,100);
        Node t5_head1 = t5_nodes[0];
        Node t5_head2 = t5_nodes[1];
        System.out.println(queryIntersectantNode(t5_head1, t5_head2));
        System.out.println();

        // 6. >-o形相交链表
        Node[]  t6_nodes = specialIntersectantLinkedListGenerator(16,1,100);
        Node t6_head1 = t6_nodes[0];
        Node t6_head2 = t6_nodes[1];
        System.out.println(queryIntersectantNode(t6_head1, t6_head2));
        System.out.println();


        // ========== 进行算法测试 ===========

    }

    // ============ 链表相交节点查找 ==================
    public static Node queryIntersectantNode(Node head1, Node head2) {
        Node loop1 = firstLoopNodePlus(head1);
        Node loop2 = firstLoopNodePlus(head2);

        // 1. 不带环
        if(loop1 == null && loop2 == null){
            return normalQueryIntersectantNode(head1, head2);
        }

        // 2. 带环>o >-o
        if(loop1 != null && loop1 == loop2){
            return specialQueryIntersectantNode(head1, head2, loop1);
        }

        // 如果两条链表一条带环一条不带环那么一定不相交
        return null;
    }


     public static Node normalQueryIntersectantNode(Node head1, Node head2) {
         Node cur1 = head1;
         Node cur2 = head2;

         // 1. 找出两条链表的长度差
         int countsDiff = 0;
         while(cur1 != null) {
             cur1 = cur1.next;
             countsDiff++;
         }

         while(cur2 != null) {
             cur2 = cur2.next;
             countsDiff--;
         }

         if(cur1 != cur2) return null;

         // 2. 接下来cur1用来绑定长链表，cur2用来绑定短链表
         cur1 = countsDiff >= 0 ? head1 : head2;
         cur2 = cur1 == head1 ? head2 : head1;

         countsDiff = Math.abs(countsDiff);

         // 3. 让长的链表先走完差距
         while(countsDiff > 0){
             cur1 = cur1.next;
             countsDiff--;
         }

         // 4. 两边同时走直到第一次相遇
         while(cur1 != cur2){
             cur1 = cur1.next;
             cur2 = cur2.next;
         }

         return cur1;
     }

     public static Node specialQueryIntersectantNode(Node head1, Node head2, Node loop) {
        Node cur1 =  head1;
        Node cur2 =  head2;

        int countsDiff = 0;
        while(cur1 != loop) {
            cur1 = cur1.next;
            countsDiff++;
        }
        while(cur2 != loop) {
            cur2 = cur2.next;
            countsDiff--;
        }

        cur1 = countsDiff >= 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        countsDiff = Math.abs(countsDiff);

        while(countsDiff > 0){
            cur1 = cur1.next;
            countsDiff--;
        }

        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
     }

    // ============= 判断一条链表是否带环 ===================
    // 1. 简单方式：使用哈希表
    public static Node firstLoopNode(Node head) {
        Set<Node> set = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }

        return null;
    }

    // 2. 优化方式，使用快慢指针和结论（结论原理我们不清楚就当黑盒来使用）（快慢指针相遇后快指针回到起点然后再次相遇就是入环的第一个节点）
    public static Node firstLoopNodePlus(Node head) {
        if(head.next == null || head.next.next == null) return null;

        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(fast.next == null || fast.next.next == null) return null; // 不带环

            if(slow == fast) break; // 带环并且相遇了
        }

        fast = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }




    // ============ 测试数据成器 ====================
    /**
     * -o带环链表（默认环都为3个节点形成的环）
     */
    public static Node withLoopLinkedList(int nodeCounts, int min, int max) {
        if(nodeCounts < 3) return null;
        Node head = MyUtils.linkedListGenerator(nodeCounts - 1, min, max);

        Node fistLoopNode = head;
        while(fistLoopNode.next != null && fistLoopNode.next.next != null) fistLoopNode = fistLoopNode.next;
        Node newNode = new Node((int)(Math.random() * (max - min + 1)) + min);
        newNode.next = fistLoopNode;
        fistLoopNode.next.next = newNode;

        return head;
    }

    /**
     * >-形相交链表生成器
     */
    public static Node[] intersectantLinkedListGenerator(int nodeCounts, int min, int max) {
        if (nodeCounts < 3) return null;
        // 1/3 分成三部分
        Node head1 = MyUtils.linkedListGenerator(nodeCounts/3, min, max);
        Node head2 = MyUtils.linkedListGenerator(nodeCounts/3, min, max);
        Node head3 = MyUtils.linkedListGenerator(nodeCounts/3, min, max);
        Node tail1 = head1;
        Node tail2 = head2;
        while(tail1.next != null) tail1 = tail1.next;
        while(tail2.next != null) tail2 = tail2.next;
        tail1.next = head3;
        tail2.next = head3;

        return new Node[]{head1, head2};
    }

    /**
     * >o形相交链表生成器
     */
    public static Node[] intersectantLoopLinkedListGenerator(int nodeCounts, int min, int max) {
        if (nodeCounts < 5) return null;
        Node loop = MyUtils.loopLinkedListGenerator(3,min,max);
        Node head1 = MyUtils.linkedListGenerator((nodeCounts-3)/2, min, max);
        Node head2 = MyUtils.linkedListGenerator((nodeCounts-3)/2+(nodeCounts - 3) % 2, min, max);

        Node tail1 = head1;
        Node tail2 = head2;
        while(tail1.next != null) tail1 = tail1.next;
        while(tail2.next != null) tail2 = tail2.next;
        tail1.next = loop;
        tail2.next = loop;

        return new Node[]{head1, head2};
    }

    /**
     * >-o形相交链表生成器
     */
    public static Node[] specialIntersectantLinkedListGenerator(int nodeCounts, int min, int max) {
        if (nodeCounts < 6) return null;
        Node loop = MyUtils.loopLinkedListGenerator(3,min,max);
        Node head1 = MyUtils.linkedListGenerator((nodeCounts-4)/2, min, max);
        Node head2 = MyUtils.linkedListGenerator((nodeCounts-4)/2 + (nodeCounts-4) % 2, min, max);
        Node tail1 = head1;
        Node tail2 = head2;
        while(tail1.next != null) tail1 = tail1.next;
        while(tail2.next != null) tail2 = tail2.next;

        Node linkNode = new Node((int)(Math.random() * (max - min + 1) + min));
        tail1.next = linkNode;
        tail2.next = linkNode;
        linkNode.next = loop;

        return new Node[]{head1, head2};
    }

}
