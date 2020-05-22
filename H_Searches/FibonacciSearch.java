package JavaBasicAlgorithm.H_Searches;


import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/18.
 */
public class FibonacciSearch {
    public static int maxSize =20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        System.out.println(fibSearch(arr,1234));

    }
    //该查找算法需要借助斐波那契数列
    //这里我们使用非递归的方式实现
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <20 ; i++) {
            f[i] = f[i-1]+f[i-2];
        }
        return f;
    }


    //使用非递归的方式编写算法
    public static int fibSearch(int[] a,int key){
        int low=0;
        int high= a.length - 1;
        int k= 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int[] f = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high>f[k]-1){
            k++;
        }

        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a,f[k]);
        //实际上需求使用a数组最后的数填充temp
        //举例：
        //temp={1,8,10,0,0,0} => {1,8,10,10,10,10}
        for (int i = high+1; i < temp.length ; i++) {
            temp[i] = a[high];
        }

        //使用while来循环处理，找到我们的数key
        while (low <= high){//只要这个条件满足，就可以找
            mid = low + f[k-1] - 1;
            if (key<temp[mid]) {//向左边查找
                high = mid - 1;
                //为什么是k--
                //说明
                // 1、全部元素= 前面的元素+后边的元素
                // 2、f[k] = f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即在f[k-1]的前面继续查找K--
                //即下次循环mid = f[k-1-1]-1

                k--;
            }else if (key > temp[mid]){//向右查找
                low = mid+1;
                //为什么是k -=2
                //说明
                //1、全部元素 = 前面的元素+后边的元素
                //2、f[k]=f[k-1]+f[k-2]
                //3、因为后面我们有f[k-2]所以可以继续拆分f[k-2] = f[k-3]+f[k-4]
                //4、即在f[k-2]的前面进行查找k-=2
                //5、即下次循环mid=f[k-1-2]-1
                k-=2;
            }else {
                //因为数组有填充的部分，直接返回mid有可能超过数组长度
                if (mid<=high){
                    return mid;
                }else {
                    return high;
                }
            }

        }
        return -1;

    }
}
