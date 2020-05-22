package JavaBasicAlgorithm.H_Searches;


/**
 * Create By 奇怪君 ON 2020/4/26.
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,1);
        System.out.println(index);
    }

    /**
     *
     * @param arr
     * @param target
     * @return 返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length -1;
        while (left <= right){
            int mid = (left+right)/2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        return -1;
    }
}


