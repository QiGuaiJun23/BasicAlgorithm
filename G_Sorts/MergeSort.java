package JavaBasicAlgorithm.G_Sorts;
import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/17.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr=/*{8,4,5,7,1,3,6,2}*/{2,5,7,9,4,1,3,6,10,18,15};
        int[] temp = new int[arr.length];

        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left<right){
            int mid =(left+right)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //到合并
            merge(arr,left,mid,right,temp);
        }
    }


    //合并方法
    //mid表示右边有序的前面一个位置

    /**
     *
     * @param arr  排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right  右边索引
     * @param temp   做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid+1;//初始化J,右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引
        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i<=mid && j<=right){
            if (arr[i] <= arr[j]){
                temp[t] = arr[i];
                t+=1;
                i+=1;
            }else{
                temp[t] = arr[j];
                t+=1;
                j+=1;
            }
        }

        //(二)
        //把有剩余数据的一边数据依次全部填充到temp
        while (i<=mid){
            temp[t]=arr[i];
            t+=1;
            i+=1;
        }
        while (j<=right){
            temp[t] =arr[j];
            t+=1;
            j+=1;
        }

        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t=0;
        int tempLeft = left;
        while (tempLeft<=right){  //第一次合并tempLeft=0,right=1
            arr[tempLeft] = temp[t];
            t+=1;
            tempLeft+=1;
        }
    }
}
