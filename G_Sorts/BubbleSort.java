package JavaBasicAlgorithm.G_Sorts;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/17.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};
        bubble(arr);

    }
    public static void bubble(int[] arr){
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length - i-1;j++){
                if (arr[j] > arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){// 在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag = false;//重置flag，进行下次判断
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
