package util;
import java.util.Arrays;

public class MyUtils {
    //swap是程序员
    public static void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j]) return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 随机数组生成器
     * @param size 数组大小
     * @param min,max 数组值的范围
     * @return 返回一个数组
     */
    public static int[] randomArrayGenerator(int size, int min, int max) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (max - min + 1) + min);
        }

        return arr;
    }


    /**
     * 回文数组生成器(如果是奇数大小的数组，那么最中间的值就设置为0)
     * @return 返回一个回文数组
     */
    public static int[] palindromeArrayGenerator(int size, int min, int max) {
        int halfSize = size/2; // 避免魔法数字，赋予变量语义
        int[] res = new int[size];

        int[] halfArray = MyUtils.randomArrayGenerator(halfSize, min, max);

        System.arraycopy(halfArray, 0, res, 0, halfSize);

        halfArray = arrInversion(halfArray);

        int inversionStartPlace = halfSize;
        inversionStartPlace = (size % 2 == 0) ? inversionStartPlace : inversionStartPlace + 1;

        System.arraycopy(halfArray, 0, res, inversionStartPlace, halfSize);

        return res;
    }


    /**
     * 数组反转方法
     * @return 返回反转后的新数组
     */
    public static int[] arrInversion(int[] arr) {
        int size = arr.length;
        int[] copyArr = new int[size];

        // 首位交换，奇偶都正确处理中间值
        for (int i = 0; i <= size / 2; i++) {
            copyArr[i] = arr[size - 1 -i];
            copyArr[size - 1 -i] = arr[i];
        }

        return copyArr;
    }
}
