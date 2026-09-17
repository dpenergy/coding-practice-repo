import java.util.Arrays;

// 大根堆和小根堆的实现
// 堆排序
// k范围有序问题
public class Main10 {
    public static void main(String[] args) {
        /*int[] arr = {2,3,1,2,54,2,1321,3,5454,54}; // 9个数
        bigHeapSort(arr,3); // 8
        System.out.println(Arrays.toString(arr));*/

        int[] arr = {3,4,2,21,54,2,36534,243,132};
        smallHeapSort(arr,arr.length-3);
        System.out.println(Arrays.toString(arr));
    }



    // 大根堆实现

    // 添加单个元素到大根堆里面
    public static void bigHeapInsert(int[] arr, int num, int heapSize) {
        // arr:整个堆空间 heapSize:已经使用了的堆空间 num:新添加的值
        // heapSize可能为arr.length,这个时候就会出现数组索引越界
        int newIndex = heapSize;    // 新来的值的初始位置（heapSize由外界来维护）
        while(arr[(newIndex-1)/2] < num) {
            swap(arr, newIndex, (newIndex-1)/2);
            newIndex = (newIndex-1)/2;
        }
    }

    /**
     * 在堆空间里面的index位置上面的值发生了变化，然后调用该方法维护堆结构
     * @param arr 整个堆空间
     * @param index 值出现变化的位置
     * @param heapSize 当前堆的使用空间(由外界来维护)
     */
    public static void bigHeapify(int[] arr, int index, int heapSize) {
        // 1. 判断当前值变大了就需要heapInsert
        // 如果是插入，那么先在的堆已用空间大小就是该位置的下标值
        bigHeapInsert(arr, arr[index], index);

        // 2. 如果当前值变小了就需要向下调整
        // 2.1 判断是否有子节点
        while(index * 2 +1 < heapSize) { // 如果已经没有子节点说明heapify已经完成
            int left  = index * 2 +1;
            int largest = index;

            largest = left +1 < heapSize && arr[left+1] > arr[left] ? left +1 : left;
            largest = arr[index] > arr[largest] ? index : largest;

            if(largest == index) break; // 说明当前数在当前位置已经是正确位置了，heapify完成

            swap(arr, index, largest);
            index = largest;
        }
    }

    /**
     * 因为基于数组的堆结构必须从下标0开始，所以不支持选定范围进行有序化
     * @param arr 需要排序的整个数组
     * @param R 支持指定右边界下标(0~R内将会被排序)
     */
    public static void bigHeapSort(int[] arr,int R) {
        // 1. 将范围内的数据变成大根堆的结构
        int heapSize = R+1;
        for (int i = Math.min(arr.length-1,R); i >= 0; i--) {
            System.out.println(i);
            bigHeapify(arr, i, heapSize);
        }

        // 2. 进行堆排序
        swap(arr, 0, heapSize-1); // 将大根堆的第一个数换到末尾
        heapSize--;
        while(heapSize > 0) {
            bigHeapify(arr, 0, heapSize); // 维护大根堆的堆结构
            swap(arr, 0, heapSize-1);
            heapSize--;
        }
    }



    // 小根堆实现

    /**
     * 小根堆插入值
     * @param arr 堆空间
     * @param num 新数值
     * @param heapSize 已用堆大小，同时是新数的下标位置（由外部维护heapSize）
     */
    public static void smallHeapInsert(int[] arr, int num, int heapSize) {
        int index = heapSize;
        while(arr[(index-1)/2] > num) {
            swap(arr, index, (index-1)/2);
            index = (index-1)/2;
        }
    }

    /**
     *
     * @param arr 堆空间
     * @param index 值出现变化的位置
     * @param heapSize 已用堆空间
     */
    public static void smallHeapify(int[] arr, int index, int heapSize) {
        // 1. index位置的值可能变小，所以先尝试smallHeapInsert
        smallHeapInsert(arr,arr[index],index); // index位置上的值作为新值插入那么已用堆空间就是index

        // 2. index位置的值变得比原来大
        // 2.1 判断是否有子节点
        while(2*index+1 < heapSize) { // heapSize-1就是堆已用空间中最大的下表位置了
            int left = 2*index+1;

            int smallest = left+1 < heapSize && arr[left+1] < arr[left] ? left + 1 : left;
            smallest = arr[index] < arr[smallest] ? index : smallest;

            if(smallest == index) break; // 如果最小值的下标就是新值本身位置下标那么就已经是正确的小根堆结构了

            swap(arr, index, smallest);

            index = smallest;
        }
    }

    /**
     * 小根堆倒序排序
     * @param arr 堆的空用空间
     * @param R 支持规定右边范围
     */
    public static void smallHeapSort(int[] arr, int R) {
        // 1. 小根堆化
        int heapSize = R+1;
        // 错误原因：如果默认已经的堆结构那么heapSize就不能改变了
        /*while(heapSize > 0) {
            smallHeapify(arr,heapSize-1,heapSize);
            heapSize--;
        }*/
        // 重最后一位下标开始
        for (int i = heapSize-1; i >= 0; i--) {
            smallHeapify(arr,i,heapSize);
        }

        // 2. 堆排序

        heapSize = R+1;
        do {
            swap(arr, 0, heapSize-1); // 交换首位位置值
            heapSize--;
            smallHeapify(arr,0,heapSize);
        } while(heapSize > 0);
    }




    public static void swap(int[] arr, int i, int j) {
        if(i == j || arr.length == 0) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
