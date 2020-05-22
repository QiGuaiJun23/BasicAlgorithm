package JavaBasicAlgorithm.H_Searches;

/**
 * Create By 奇怪君 ON 2020/4/18.
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] ={1,9,11,-1,34,89};
        int result = seqSearch(arr, 9);
        if (result == -1){
            System.out.println("未找到");
        }else {
            System.out.println(result);
        }


    }

    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr,int value){
        //线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
