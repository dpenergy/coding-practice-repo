package linkedlist;
import java.util.Arrays;

// 手动实现归并排序
public class Main03 {
    public static void main(String[] args) {
        int[] arr = {3,4,2,4,23,2,2,6,57,3,21,11,432,2};
        mergerSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergerSort(int[] arr, int L, int R) {
        process(arr,L,R);
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) return ;
        int mid  = L + (R - L) / 2;
        process(arr, L, mid); // 使得左半部分有序
        process(arr, mid+1,R); // 使得右半部分有序
        merge(arr,L,mid,R); // 使整体有序
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R-L+1]; // 辅助空间
        int i = 0;
        int p1 = L;
        int p2 = M+1;

        while(p1<=M && p2<=R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= M) {
            help[i++] = arr[p1++];
        }
        while(p2 <= R) {
            help[i++] = arr[p2++];
        }

        // 本地方法，底层使用C++实现性能比手动for要快许多
        // src:源数组; srcPos:源数组起始索引; dest:目标数组; destPos:目标数组起始位置; length:要复制的元素个数
        System.arraycopy(help,0,arr,L,help.length);
    }
}
