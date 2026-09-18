package linkedlist;
// 复习Java基础

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.IntFunction;

public class Main08 {
    // 声明一个函数式接口的实现类对象
    public static IntFunction<int[]> lambdaFunc = int[]::new;
    public static void main(String[] args) {
        int[] arr = lambdaFunc.apply(10);
        System.out.println(Arrays.toString(arr));

        Set<Integer> set = new HashSet<>();
    }
    // 创建一维数组
    public int[] createIntArray(int n) {
        return new int[n];
    }

    // 简化为lambda表达式：n -> new int[n]

    // 简化为方法引用int[]::new
}
