package linkedlist;
import java.util.Arrays;

// 桶排序的优化实现
public class Main11 {
    public static void main(String[] args) {
        int arraySize = 100000000; // int 最大值是21亿多
        int[] arr = new int[arraySize];
        randomGenerateArray(arr,0,100000);
//        System.out.println("原始数组："+Arrays.toString(arr));
//        int[] copy = Arrays.copyOf(arr,arr.length);
//        Arrays.sort(copy);
//        System.out.println("目标结果："+Arrays.toString(copy)+"\n\n");

        long start = System.currentTimeMillis();
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copyArr);
        long end = System.currentTimeMillis();
        System.out.println("Arrays.sort()排序结果："+verifyArray(arr,copyArr)+"，使用时间："+(end-start)+"ms");

        // test bucketSort (只能测试正整数)
        long start1 = System.currentTimeMillis();
        int[] copyArr1 = Arrays.copyOf(arr, arr.length);
        bucketSort(copyArr1,0,arr.length-1);
        long end1 = System.currentTimeMillis();
        System.out.println("bucketSort排序结果："+verifyArray(arr,copyArr1)+"，使用时间："+(end1-start1)+"ms");

        // test mergeSort
        long start2 = System.currentTimeMillis();
        int[] copyArr2 = Arrays.copyOf(arr, arr.length);
        mergeSort(copyArr2,0,arr.length-1);
        long end2 = System.currentTimeMillis();
        System.out.println("mergeSort排序结果："+verifyArray(arr,copyArr2)+"，使用时间："+(end2-start2)+"ms");

        // test bubblingSort
        /*long start3 = System.currentTimeMillis();
        int[] copyArr3 = Arrays.copyOf(arr, arr.length);
        bubblingSort(copyArr3,0,arr.length-1);
        long end3 = System.currentTimeMillis();
        System.out.println("bubblingSort排序结果："+verifyArray(arr,copyArr3)+"，使用时间："+(end3-start3)+"ms");*/


        // test choiceSort
        /*long start4 = System.currentTimeMillis();
        int[] copyArr4 = Arrays.copyOf(arr, arr.length);
        choiceSort(copyArr4,0,arr.length-1);
        long end4 = System.currentTimeMillis();
        System.out.println("choiceSort排序结果："+verifyArray(arr,copyArr4)+"，使用时间："+(end4-start4)+"ms");*/

        // test insertSort
        /*long start5 = System.currentTimeMillis();
        int[] copyArr5 = Arrays.copyOf(arr, arr.length);
        insertSort(copyArr5,0,arr.length-1);
        long end5 = System.currentTimeMillis();
        System.out.println("insertSort排序结果："+verifyArray(arr,copyArr5)+"，使用时间："+(end5-start5)+"ms");*/

        // test heapSort
        long start6 = System.currentTimeMillis();
        int[] copyArr6 = Arrays.copyOf(arr, arr.length);
        heapSort(copyArr6,copyArr6.length-1);
        long end6 = System.currentTimeMillis();
        System.out.println("heapSort排序结果："+verifyArray(arr,copyArr6)+"，使用时间："+(end6-start6)+"ms");

        // test quickSort
        long start7 = System.currentTimeMillis();
        int[] copyArr7 = Arrays.copyOf(arr, arr.length);
        quickSort(copyArr7,0,arr.length-1);
        long end7 = System.currentTimeMillis();
        System.out.println("quickSort排序结果："+verifyArray(arr,copyArr7)+"，使用时间："+(end7-start7)+"ms");


        // System.out.println(Arrays.toString(arr));
    }

    // 1. 桶排序（bucketSort）
    // 最终时间复杂度是：几百O(N) = O(N)
    public static void bucketSort(int[] arr, int L, int R){
        final int size = 10;


        int[] bucket = new int[R-L+1];

        // 1. 记录最大位数
        int max = Integer.MIN_VALUE;
        for (int i = L; i < R-L+1; i++) {
            max = Math.max(max, arr[i]); // 获取最大值O(N)
        }

        int maxDegree = getDegree(max);

        // 最多循环30次
        for (int d = 1; d <= maxDegree; d++) { // 进行入桶出桶的次数，i表示此次循环入桶所依据的数的位数
            // 统计count
            int[] count = new int[size]; // 每一次的count都是重新统计的

            for (int j = L; j < R - L + 1; j++) {
                count[getDigit(arr[j],d)]++; // O(N)
            }

            // 前缀和count
            for (int c = 0; c < count.length-1; c++) {
                count[c+1] += count[c]; //O(N)
            }

            // 入桶(从后向前遍历数组，然后根据count找到对应的位置)
            for (int j = R; j >= L ; j--) {
                bucket[--count[getDigit(arr[j],d)]] = arr[j]; // O(N)
            }

            // 出桶
            System.arraycopy(bucket,0,arr,L,bucket.length); //O(N)
        }
    }
    public static int getDegree(int num) {
        int degree = 0;
        while(num > 0) {
            num /= 10;
            degree++;
        }

        return degree;
    }
    public static int getDigit(int num,int degree) {
        for (int i = 0; i < degree-1; i++) {
            num /= 10;
        }

        return num % 10;
    }


    // 2. 归并排序（mergeSort）
    public static void mergeSort(int[] arr, int L, int R) {
        if(L == R) return;
        int mid =  L + (R - L)/2;
        mergeSort(arr,L,mid); // 使左边有序
        mergeSort(arr,mid+1,R); // 使右边有序
        merge(arr,L,mid,R);
    }
    public static void merge(int[] arr, int L, int M, int R) {
        // 辅助空间
        int[] help = new int[R-L+1];
        int i = 0; // 用来维护辅助空间

        int p1 = L; // 维护左区域
        int p2 = M+1; // 维护右区域
        while(p1 <= M && p2 <= R) {
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

    // 3. 快速排序（quickSort）（三段的快排< = >）
    public static void quickSort(int[] arr, int L, int R) {
        // 在范围内随机选择一个值作为num,然后依据num堆数组进行<=>的三段划分，最后将num填回正确的位置，递归重复这个过程直到全有序

        swap(arr,R,(int)(Math.random()*(R-L+1))+L);
        int num = arr[R];
        int[] res = partition(arr,L,R,num);
        if(res[0] > L) quickSort(arr,L,res[0]);
        if(res[1] < R) quickSort(arr,res[1],R);
    }
    public static int[] partition(int[] arr, int L, int R, int num) {
        // 根据小于等于大于进行三段划分，最终返回内边界位置
        int p1 = L-1; // 维护左内边界
        int p2 = R; // 维护右内边界，因为最后一个值是num,这是我们的判断依据，所以不属于处理范围
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

        swap(arr,p2++,R);

        return new int[]{p1,p2};
    }

    // 快速排序两段的快排：<= >

    // 综合排序，工程改进


    // 4. 冒泡排序（bubblingSort）
    public static void bubblingSort(int[] arr, int L, int R) {
        for (int i = 0; i < R-L+1; i++) { // R-L+1表示需要处理为有序的数的数量
            for (int j = L; j <= R-i-1; j++) { // 每次循环可以使得最后一个位置的数是有序的
                if(arr[j] > arr[j+1]) {
                    swap(arr,j,j+1);
                }
            }
        }
    }

    // 5. 选择排序（choiceSort）
    public static void choiceSort(int[] arr, int L, int R) {
        // 每次在无序范围内找出最大值然后和无序范围内的最后一个位置交换，然后有序范围+1无序范围-1，重复此过程直到所有数有序
        for (int i = 0; i < R - L + 1; i++) { // 无序数数量
            int maxIndex = L;
            for (int j = L; j <= R-i; j++) {
                maxIndex = arr[j] > arr[maxIndex] ? j : maxIndex;
            }
            swap(arr,maxIndex,R-i);
        }
    }

    // 6. 插入排序（insertSort）
    public static void insertSort(int[] arr, int L, int R) {
        // 依次将无序范围内的数插入有序范围内，维护有序范围内的数有序
        for (int i = 1; i < R-L+1; i++) {
            // 找到i~R范围内的最小值,并将最小值放到i位置
            int minIndex = i;
            for(int j = i; j <= R; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr,i,minIndex);

            // 将i位置并入有序范围内
            for(int j = i; j > L; j--) {
                if(arr[j] >= arr[j-1]) break; // 已经有序
                swap(arr,j,j-1);
            }
        }
    }

    // 7. 堆排序（只支持左边界从0开始）
    public static void heapSort(int[] arr, int R) {
        // 先对范围内的数组进行大/小根堆化，然后进行头尾位置交换、缩小堆范围和堆有序的维护，重复直到范围内数组有序
        int heapSize = 0;
        for (int i = 0; i <= R; i++) {
            heapInsert(arr,heapSize,arr[i]);
            heapSize++;
        }

        while(heapSize > 0) {
            swap(arr,0,--heapSize);
            heapify(arr,0,heapSize);
        }
    }
    public static void heapify(int[] arr, int index, int heapSize) {
        // 有序的堆上在index位置上的值发生变化，需要维护堆使其保持有序
        // 1. 首先尝试堆插入
        heapInsert(arr,index,arr[index]); // index值要作为新值插入，那么当前的堆已经空间就是index大小

        // 2. 当前值变小，向下移动（这里我们使用的是小根堆所以这样操作）
        while(index *2+1 < heapSize) { // 判断是否有子节点
            // 找出父左右三点中的最大点
            int left = index*2+1;
            int largest = left+1 < heapSize && arr[left+1] > arr[left] ? left+1 : left;
            largest = arr[largest] > arr[index] ? largest : index;

            if(largest == index) break; // 对于大根堆，如果index位置已经是父左右中的最大值那么就已经有序

            swap(arr,index,largest);
            index = largest;
        }
    }
    public static void heapInsert(int[] arr, int heapSize, int num) {
        // heapSize 即代表则当前堆的已用空间，也代表则新插入值的初始位置，但要注意heapSize的值需要外部来维护
        arr[heapSize] = num;
        int index = heapSize;

        while(arr[(index-1)/2] < num) {
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }


    // 8. 对数器，用来测试排序方法的性能
    public static void randomGenerateArray(int[] arr, int min, int max) { // 随机生成正整数
        int arraySize = arr.length;
        for (int i = 0; i < arraySize; i++) {
            arr[i] = (int) (Math.random()*(max-min+1)+min);
        }
    }

    public static boolean verifyArray(int[] srcArr, int[] resArr) {
        int[] arr = Arrays.copyOf(srcArr, srcArr.length);

        Arrays.sort(arr);
        return Arrays.equals(arr,resArr);
    }


    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
