package JavaBasicAlgorithm.H_Searches;

/**
 * Create By 奇怪君 ON 2020/4/18.
 */

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i;
        }
        int result = inserValueSearch(arr, 0, 99, 1);
        if (result==-1){
            System.out.println("没有找到该值");
        }else {
            System.out.println(result);
        }
    }

    public static int inserValueSearch(int[] arr,int left,int right,int findVal){
        //findVal< arr[0] || findVal > arr[arr.length-1]必须需要，否则mid可能越界
        if (left>right || findVal< arr[0] || findVal > arr[arr.length-1])return -1;
        int mid = left+(right - left)*(findVal-arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal)
            return inserValueSearch(arr,mid+1,right,findVal);
        else if (findVal < midVal)
            return inserValueSearch(arr,left,mid-1,findVal);
        else return mid;
    }
}
