package JavaBasicAlgorithm.H_Searches;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By 奇怪君 ON 2020/4/18.
 */

// 注意：使用二分查找的前提是  该数组是有序的
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] ={1,9,11,-1,34,43,43,43,89,90,99};
//        int result = binarySearch(arr, 0, arr.length-1, 43);
//        if (result==-1){
//            System.out.println("没有找到该值！");
//        }else {
//            System.out.println(result);
//        }
        List<Integer> results = binarySearch2(arr, 0, arr.length - 1, 43);
        System.out.println(results);

    }
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        if (left>right)return -1;
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal > midVal)
            return binarySearch(arr,mid+1,right,findVal);
        else if (findVal < midVal)
            return binarySearch(arr,left,mid-1,findVal);
        else return mid;
    }


    public static ArrayList<Integer> binarySearch2(int[] arr,int left,int right,int findVal){
        if (left>right)return new ArrayList<>();
        int mid = (left+right)/2;
        int midVal = arr[mid];
        if (findVal > midVal)
            return binarySearch2(arr,mid+1,right,findVal);
        else if (findVal < midVal)
            return binarySearch2(arr,left,mid-1,findVal);
        else {
            ArrayList<Integer> resIndexlist = new ArrayList<Integer>();
            int temp = mid -1;
            while (true){
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp-=1;
            }
            resIndexlist.add(mid);
            temp = mid+1;
            while (true){
                if (temp>arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp+=1;
            }
        return resIndexlist;
        }
    }
}
