package JavaBasicAlgorithm.G_Sorts;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/18.
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //基数排序
    public static void radixSort(int[] arr){
        //得到数组的最大值，确定循环的次数
        int max = arr[0];
        for (int i = 0; i < arr.length ; i++) {
            if(max < arr[i]){
                max = arr[i];
            }
        }
        int maxLength = (max+"").length();

        //定义桶的大小
        int[][] bucket = new int[10][arr.length];

        //获取桶中的数据量
        int[] index = new int[10];

        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            for (int j = 0;j<arr.length;j++){
                int bucketcout = arr[j] / n % 10;
                bucket[bucketcout][index[bucketcout]] = arr[j];
                index[bucketcout]++;
            }

            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int bucketindex = 0;
            //遍历每个桶取出数据放回到原数组中
            for (int j = 0;j<bucket.length;j++){
                if (index[j] != 0){
                    for (int k=0;k<index[j];k++){
                        arr[bucketindex++] = bucket[j][k];
                    }
                }

                //第i轮处理完，需要将每个桶中的数据数置为0；
                index[j] = 0;
            }
        }
    }
}
