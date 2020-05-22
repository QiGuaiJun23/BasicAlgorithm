package JavaBasicAlgorithm.K_Heap;


import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/21.
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4,6,8,5,9,13,10};
        heapSort(arr);
    }


    //编写一个堆排序的方法
    public static void heapSort(int arr[]){
        int temp = 0;
        System.out.println("堆排序！");
        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));

        //完成最终的代码
        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length/2 -1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);//{9,6,8,5,4}
        }
        //将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int i = arr.length-1; i >0 ; i--) {
            //交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
        System.out.println(Arrays.toString(arr));
    }


    //将一个数组(二叉树)，调整成一个大顶堆

    /**
     *功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * @param arr  待调整的数组
     * @param i    表示非叶子节点在数组中索引
     * @param length  表示对多少个元素继续调整，length在逐渐减少
     */
    public static void adjustHeap(int arr[],int i,int length){
        int temp = arr[i];
        for (int k = 2*i+1; k < length ; k = 2*k+1) {
            if (k+1 <length && arr[k]<arr[k+1]){ //说明左子节点小于右子节点
                k++;//k 指向右子节点
            }
            if (arr[k]>temp){//如果子节点大于父结点
                arr[i] =arr[k];//把较大的值赋给当前结点
                i = k;//!!!i指向k，继续循环遍历

            }else {
                break;
            }

        }
        //当for循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶(局部)
        arr[i] = temp;
    }
}
