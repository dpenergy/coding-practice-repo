package linkedlist;
import linkedlist.pojo.Node;
import linkedlist.util.LinkedListUtils;

// 打印有序链表的公共部分
public class Main13 {
    public static void main(String[] args) {
        int nodeCounts = 30;

        Node head1 = orderedLinkedListGenerate(nodeCounts,1,50);
        Node head2 = orderedLinkedListGenerate(nodeCounts,1,59);

        LinkedListUtils.printLinkedList(head1);
        LinkedListUtils.printLinkedList(head2);

        printPublicPart(head1,head2);
    }


    public static void printPublicPart(Node head1, Node head2) {

        StringBuilder sb = new StringBuilder();

        while(head1 != null && head2 != null){
            if(head1.value == head2.value){
                sb.append(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            } else if(head1.value > head2.value){
                head2 = head2.next;
            } else {
                head1 = head1.next;
            }
        }

        System.out.println(sb.toString());
    }

    /**
     * 生成一个有序链表
     * @param nodeCounts 节点数量（包含头节点）
     * @param min,max 节点值的范围
     * @return 返回一个有序链表的头节点
     */
    public static Node orderedLinkedListGenerate(int nodeCounts, int min, int max) {
        // 先生成数，再排列为有序数，再转换为链表，之后返回头节点
        int[] arr = orderedArrayGenerate(nodeCounts, min, max);

        Node head = new Node(arr[0]);
        Node tail = head;

        for (int i = 1; i < arr.length; i++) {
            tail.next = new Node(arr[i]);
            tail = tail.next;
        }

        return head;
    }

    public static int[] orderedArrayGenerate(int size, int min, int max) {
        int[] arr = new int[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*(max-min+1)+min);
        }

        quickSort(arr, 0, arr.length-1);

        return arr;
    }

    public static void quickSort(int[] arr, int L, int R) {
        if(L == R) return;

        swap(arr,R,(int)(Math.random()*(R-L+1))+L);
        int num = arr[R];
        int[] res = partition(arr, L, R, num);
        if(res[0] > L) quickSort(arr, L, res[0]); // 对小于部分进行partition
        if(res[1] < R) quickSort(arr, res[1], R); // 对大于部分进行partition
    }

    public static int[] partition(int[] arr, int L, int R, int num) {
        int p1 = L - 1; // 记录小于区域的右内边界
        int p2 = R; // 记录大于区域的左边界，R位置上面的值是num
        int i = L; // 遍历指针

        while(i < p2) {
            if(arr[i] < num) {
                swap(arr,i++,++p1);
            } else if(arr[i] == num) {
                i++;
            } else {
                swap(arr,i,--p2);
            }
        }

        swap(arr,R,p2++);

        return new int[]{p1,p2};
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
