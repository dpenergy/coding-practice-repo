package linkedlist;
// 归并排序

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main07 {
    public static void main(String[] args) {
        // 测试归并排序是否能正常运行
//        int[] arr = {4,3,2,4,2,654,23,5432,24,65435};
//        mergeSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));
        // 测试结果正常

        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Set<Integer> set = new HashSet<>(); // Set集合单列集合，不重复，无索引
        for(int i=0; i < N; i++){
            set.add(in.nextInt());
        }

        // 使用toArray方法只能将传递引用类型的普通数组并返回引用类型的普通数组，不能获得或者传递普通类型的数组
//        // 泛型只接收包装类所以不能使用int[0]
//        Integer[] arr = set.toArray(new Integer[0]);
//        mergeSort(,0,arr.length-1);

        // 使用Stream API来获得基本的普通数组
        // mapToInt接收一个函数式接口(接收对象，返回基本数据类型),将对象流变换为基本数据流
        // Integer::valueOf本身是接收基本数据类型返回Integer类型，但是Integer类型可以自动拆箱所以就变回了基本数据类型，然后传递给toArray最终返回基本数据类型的int数组
        int[] arr = set.stream().mapToInt(Integer::intValue).toArray();
        mergeSort(arr,0,arr.length-1);

        System.out.println(arr.length);

        String result = Arrays.toString(arr);

        System.out.println(result.replace("[","").replace("]","").replace(",",""));
    }

    // 首先完成归并排序
    public static void mergeSort(int[] arr,int L,int R){
        if(L==R) return;
        int mid = L+(R-L)/2;
        mergeSort(arr,L,mid);
        mergeSort(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    public static void merge(int[] arr,int L,int M,int R){
        // 辅助空间
        int[] help = new int[R-L+1];
        int i = 0;

        int p1 = L;
        int p2 = M+1;

        while(p1<=M && p2<=R){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1<=M){
            help[i++] = arr[p1++];
        }
        while(p2<=R){
            help[i++] = arr[p2++];
        }

        System.arraycopy(help,0,arr,L,help.length);
    }
}
