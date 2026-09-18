package linkedlist;
import java.util.Arrays;

// 使用归并排序解决小和问题
// 3 1 2 4 6 7 5
// 12+8+5+12+6 = 43
public class Main04 {
    public static void main(String[] args) {
        int[] arr = {3,1,2,4,6,7,5,33,43,2,123,4342,34,2111,312};
        System.out.println(process(arr,0,arr.length-1));
        System.out.println(Arrays.toString(arr));
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) return 0;
        int mid = L + (R-L)/2;
        return process(arr, L, mid)+ // 左边的所有小和
               process(arr, mid+1, R)+  // 右边的所有小和
               merge(arr, L, mid, R); // 整合过程中的小和
    }

    // 返回当前区域的小和总和
    public static int merge(int[] arr,int L,int M,int R) {
        // 辅助空间
        int[] help = new int[R-L+1];
        int i = 0; // 用来维护辅助空间
        int p1 = L; // 用来处理左边
        int p2 = M+1; // 用来处理右边
        int smallSum = 0;

        while(p1 <=M && p2<=R) {
//            if(arr[p1] < arr[p2]) {
//                help[i++] = arr[p1];
//                smallSum += (R-p2+1) * arr[p1];
//                p1++;
//            } else {
//                help[i++] = arr[p2++];
//            }

            // 优化改写
            smallSum += arr[p1] < arr[p2] ? arr[p1] * (R-p2+1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while(p1 <=M) {
            help[i++] = arr[p1++];
        }
        while(p2<=R) {
            help[i++] = arr[p2++];
        }

        System.arraycopy(help,0,arr,L,help.length);

        return smallSum;
    }
}
