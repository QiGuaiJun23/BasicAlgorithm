package JavaBasicAlgorithm.G_Sorts;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/17.
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        selectSort(arr);
    }


    //选择排序
    public static void selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int min = arr[i];
            int minIndex = i;
            for (int j=i+1;j<arr.length;j++){
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
