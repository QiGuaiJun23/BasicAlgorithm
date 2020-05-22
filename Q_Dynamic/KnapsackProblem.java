package JavaBasicAlgorithm.Q_Dynamic;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/4/26.
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val = {1500,3000,2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];


        //创建二位数组
        //v[i][j]表示在前i个物品中，能够装入容量为j的背包中的最大值
        int[][] v = new int[n+1][m+1];
        //初始化每一行和每一列，这里也可以不去，因为默认是0
        for (int i = 0; i < v.length ; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i <v[0].length ; i++) {
            v[0][i] = 0;
        }
        //根据前面的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j=1;j<v[0].length;j++){//不处理第一列
                //套用公式
                if (w[i-1] > j){
                    v[i][j]  = v[i-1][j];
                }else {
                    //说明：
                    //因为我们的i 从1开始的，因此公式需要调整
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们不能简单的使用上面的公式，需要使用if-else来体现公式
                    if (v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;//这种情况是最优的
                    }else {
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }




        for (int i = 0; i < v.length; i++) {
            for (int j=0;j<v[0].length;j++){
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }

        //输出最后我们是放入的哪些商品
        //遍历path,这样输出会把所有的放入情况都得到，其实我们只需要最后的放入
//        for (int i = 0; i <path.length ; i++) {
//            for (int j=0;j<path[i].length;j++){
//                System.out.print(path[i][j]+" ");
//            }
//            System.out.println();
//        }

        int i = path.length -1;//行的最大下标
        int j = path[0].length -1;//列的最大下标
        while (i>0 && j>0){
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i-1];
            }
            i--;
        }

    }
}
