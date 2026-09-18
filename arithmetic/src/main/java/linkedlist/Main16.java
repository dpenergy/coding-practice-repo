package linkedlist;

import linkedlist.util.MyUtils;

import java.util.Arrays;

// 两段的partition
public class Main16 {
    public static void main(String[] args) {
        int[] arr = MyUtils.randomArrayGenerator(10, 1, 10);
        twoPartPartition(arr,5);
        System.out.println(Arrays.toString(arr));
    }

    public static void twoPartPartition(int[] arr, int num) {
        int p = -1; // 小于等于区域内边界

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] <= num) {
                MyUtils.swap(arr, i, ++p);
            }
        }
    }

}
