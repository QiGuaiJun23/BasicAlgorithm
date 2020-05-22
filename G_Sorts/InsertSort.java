package JavaBasicAlgorithm.G_Sorts;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/17.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr={101,34,119,1};
        insertSort(arr);
    }


    //插入排序
    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for (int i=1;i<arr.length;i++){
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i-1;
            //给insertVal找到插入的位置
            //1、insertIndex>=0 保证在给insertVal找插入的位置，不越界
            //2、insertVal< arr[insertIndex] 待插入的数，还没有找到插入的位置
            //3、arr[insertIndex +1]后移
            while (insertIndex>=0 && insertVal< arr[insertIndex]){
                arr[insertIndex +1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex+1
            if (insertIndex +1 !=i){
                arr[insertIndex+1] = insertVal;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
