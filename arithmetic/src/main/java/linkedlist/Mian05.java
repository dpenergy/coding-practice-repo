package linkedlist;
import java.util.Arrays;

// 快速排序v3.0实现
// 有了思路自己去实现细节和看过别人实现的方式在自己去实现细节是两件事情，前者是从0到1后者是从0.x到1，前者是创造后者是修复跟随
// 我觉得能记住或者熟悉别人的方法的人是没法找到自己的底气的，能自己也从0到1才有底气
public class Mian05 {
    public static void main(String[] args) {
        int[] arr = {40000000,3434,1,321,6,3,2,6,7000,41,100000};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L == R) return;

        int[] res = partition(arr,L,R); // 返回的区域内边界可能越界 5 5 5 5 5
        if(res[0] >= L)quickSort(arr,L,res[0]);  // 存在数组越界 5 5 5 5 5
        if(res[1] <= R)quickSort(arr,res[1],R);
    }

    public static int[] partition(int[] arr, int L, int R) {
        // 随机选择一个数作为num分割并放置在该片段数组末尾
        int numIndex = R;
        swap(arr,R,(int)(Math.random()*(R-L+1))+L); // 随机范围L~R
        int num = arr[R];
        int i = L--;  // L和R都是记录的界内边界

        while(i < R) {
            if(arr[i] < num ) {
                swap(arr,++L,i++);
            } else if(arr[i] > num) {
                swap(arr,i,--R);
            } else {
                i++;
            }
        }

        swap(arr,R,numIndex); // 预防 5 5 5 5 5 5这种索引越界

        return new int[]{L,R+1};
    }



    // 默认传递的是有效值，没进行数据校验
    public static void swap(int[] arr,int i, int j) {
        if(i==j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
