// 堆结构和堆排序
// 大根堆

import java.util.Arrays;

public class Main09 {
    public static void main(String[] args) {

        int[] arr = new int[10];
        int heapSize = 0;

        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * (722 - 654 + 1)) + 654;
            headInsert(arr, num, heapSize);
            heapSize++;
        }

        System.out.println(heapSize);
        System.out.println(Arrays.toString(arr));


        for (int i = 0; i < 10; i++) {
            heapify(arr, 0, heapSize);
            heapSize--;
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void headInsert(int[] arr, int num, int heapSize) {
        // arr:堆总空间 num:新值 heapSize:堆已用空间，同时是新来的数的下标位置（从下标0开始的一段连续的位置）
        // 首先我们需要把num这个值给放到堆里面
        arr[heapSize] = num;

        int index = heapSize;
        while (arr[(index - 1) / 2] < num) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        // index:抽取的值的下标 heapSize:当前的堆使用空间
        // 从堆结构中随机取出了一个值，然后使剩下的数据在原有基础上继续维护堆结构
        // 取出了一个值，所以heapSize必须减小，所以不要忘记了调用该方法后减小heapSize

        swap(arr, index, heapSize - 1);
        // 交换之后index~heapSize-2才是有效的堆范围
        // 因为是大根堆结构，交换上来的值一定是小的，所以子需要和子节点进行交换位置就行了
        // ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
        // 原来是这里的有效堆范围写错了

        while (index * 2 + 1 < heapSize-1) { // 有子节点

            int left = index * 2 + 1;
            int largest = left;

            largest = left + 1 < heapSize-1 && arr[left + 1] > arr[left] ? left + 1 : left; // 先找到子节点中的最大值位置
            largest = arr[largest] > arr[index] ? largest : index; // 被取点移入的新值和最大子节点比较，并记录最大值位置

            if (largest == index) break; // 如果这个换过来的值已经在正确位置上了，那么任务就已经完成了

            swap(arr, largest, index);
            index = largest; // 新的需要判断的位置
        }

    }


    public static void swap(int[] arr, int i, int j) {
        if (i == j) return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
