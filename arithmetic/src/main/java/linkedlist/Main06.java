import java.util.Arrays;

// 再手敲一遍归并排序和快速排序
public class Main06 {
    public static void main(String[] args) {
        int[] arr = {3,2,3,5,2,1000000,23,3,1,4,54,5,213};
//        mergeSort(arr,0,arr.length-1);
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    // 不进行数据校验，默认只传递有效数据

    public static void mergeSort(int[] arr,int L,int R){
        if(L==R) return;

        int mid = L+(R-L)/2;

        mergeSort(arr,L,mid); // 使左边有序
        mergeSort(arr,mid+1,R); // 使右边有序
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr,int L, int M, int R){
        // 左边和右边都已已经有序了，现在就有序的合并两边
        // 辅助空间
        int[] help = new int[R-L+1];
        int i = 0;// 用来跟踪辅助空间
        int p1 = L;
        int p2 = M+1;
        while(p1 <= M && p2 <=R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while(p1 <= M) {
            help[i++] = arr[p1++];
        }

        while(p2 <= R) {
            help[i++] = arr[p2++];
        }

        System.arraycopy(help,0,arr,L,help.length);
    }


    public static void quickSort(int[] arr, int L,int R) {
        if(L==R) return;
        int[] res = partition(arr,L,R); // 5 5 5 5,res存放边界内的边界索引
        if(res[0] >= L) quickSort(arr,L,res[0]);
        if(res[1] <= R) quickSort(arr,res[1],R);
    }

    public static int[] partition(int[] arr,int L,int R){
        // 归并排序才需要辅助空间，快速排序不需要辅助空间
//        // 辅助空间
//        int[] help = new int[R-L+1];


        // 先完成交换和取值操作
        swap(arr,R,(int)(Math.random()*(R-L+1)) + L);
        int num = arr[R];

        // 为了更加清晰的表示，我们新建两个变量用来记录下标
        int i = L; // 遍历指针
        int p1 = --L; // 记录小于区域有边界
        int p2 = R; // 记录大于区域左边界
        // 先随机交换位置到最后位置，再取出最后一个值
//        swap(arr,R,(int)(Math.random()*(R-L+1)) + L); // 这里有问题：L的值已经改变了才交换
//        int num = arr[R];

        while(i < p2) {
            if(arr[i] < num) {
                swap(arr,i++,++p1);
            } else if(arr[i] > num) {
                swap(arr,i,--p2);
            } else {
                i++;
            }
        }

        swap(arr,p2,R);

        return new int[]{p1,p2};
    }


    // 默认传入的数据都是有效的数据，不进行数据校验
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
