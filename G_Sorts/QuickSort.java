package JavaBasicAlgorithm.G_Sorts;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/17.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70,-1,900,4561};
        quickSort(arr,0,8);
        System.out.println(Arrays.toString(arr));
    }


    public static void quickSort(int[] arr,int L,int R){
        if(L>R)return;
        int left = L;
        int right = R;
        int pivot = arr[left];
        while (left<right){
            while (left<right && arr[right] >= pivot){
                right--;
            }
            arr[left] = arr[right];
            while (left<right && arr[left]<= pivot){
                left++;
            }
            arr[right] = arr[left];
            if (left==right){
                arr[left] = pivot;
            }
        }
        quickSort(arr,L,right-1);
        quickSort(arr,right+1,R);
    }
}
