package JavaBasicAlgorithm.G_Sorts;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/17.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
//        shellSort(arr);
        shellSort2(arr);
    }

    //交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        for (int gap = arr.length/2;gap>0;gap/=2){
            for (int i=gap;i<arr.length;i++){
                //遍历各组中所有的元素
                for (int j=i-gap;j>=0;j-=gap){
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    // 移位法
    public static void shellSort2(int[] arr){
        //增量gap,并逐步的缩小增量
        for (int gap=arr.length/2;gap>0;gap/=2){
            for (int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if (arr[j]< arr[j-gap]){
                    while (j-gap>=0&&temp<arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
