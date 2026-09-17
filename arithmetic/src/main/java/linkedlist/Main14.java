import util.MyUtils;

import java.util.Arrays;
import java.util.Stack;

// 判断链表是否是回文序列
// 笔试解法和面试解法
public class Main14 {
    public static void main(String[] args) {
        Node head = palindromeLinkedListGenerator(7,1,100);
        Main12.printLinkedList(head);

        System.out.println(verifyPalindromeLinkedList(head));
        System.out.println(verifyPalindromeLinkedListPlusVersion(head));


        Main12.printLinkedList(head);

    }

    // 面试写法，快慢指针
    public static boolean verifyPalindromeLinkedListPlusVersion(Node head) {
        Node slow = head;
        Node fast = head;

        // 如果是奇数个node那么slow到正中点是faster到正末尾
        // 如果是偶数个node那么slow到中左
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 现在slow后面的一部分刚好是完整的右部分

        // 对后半部分进行方向反转
        Node midNode = slow; // 记录链表中间位置或者中左位置
        slow = slow.next;

        Node cur = slow;
        Node prev = null;
        Node next ;

        while((next = cur.next) != null){
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        cur.next = prev;

        Node tail = cur; // 此刻的cur也是tail位置

        // 头尾同时向中间靠近并判断
        boolean result = true;

        while(head!=null && tail!=null){ // 奇数时不对称，少的一边先为null
            if(head.value != tail.value) {
                result = false;
                break;
            }

            head = head.next;
            tail = tail.next;
        }

        // 还原链表
        prev = null;
        while((next = cur.next) != null){
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        cur.next = prev;

        return result;
    }


    // 笔试写法，使用额外空间O(N),时间复杂度O(N)
    public static boolean verifyPalindromeLinkedList(Node head) {
        Stack<Integer> valueStack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            valueStack.add(cur.value);
            cur = cur.next;
        }

        cur = head;

        while(cur != null){
            if(valueStack.pop() != cur.value){
                return false;
            }
            cur = cur.next;
        }

        return true;
    }

    /**
     * 回文链表生成器
     * @param nodeCounts 链表的节点数量（包含头节点）
     * @param min,max 节点值的范围
     * @return 返回链表头节点
     */
    public static Node palindromeLinkedListGenerator(int nodeCounts, int min, int max) {
        // 先取一半，或者半偏左
        int[] palindromeArray = MyUtils.palindromeArrayGenerator(nodeCounts, min, max);

        Node head = new Node(palindromeArray[0]);
        Node tail = head;

        for (int i = 1; i < nodeCounts; i++) {
            tail.next = new Node(palindromeArray[i]);
            tail = tail.next;
        }

        return head;
    }


}
