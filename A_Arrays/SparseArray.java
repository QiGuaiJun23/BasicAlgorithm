package JavaBasicAlgorithm.A_Arrays;

/**
 * Create By 奇怪君 ON 2020/4/5.
 */
public class SparseArray {
    public static void main(String[] args) {
        //先创建一个原始的二维数组11*11
        // 0:表示没有棋子，1表示黑子 2表示白子
        int cherrArr1[][] = new int[11][11];
        //初始化
        cherrArr1[1][2] = 1;
        cherrArr1[2][3] = 2;
        cherrArr1[4][3] = 2;
        //输出原始数组
        for(int[] row : cherrArr1){
            for(int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组
        // 1、先遍历二维数组，得到非0数据的个数
        int sum=0;

        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (cherrArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组将非0值存放到sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0;j<11;j++){
                if (cherrArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = cherrArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组如下：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组恢复成原始的二维数组
        //1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        //2、再读取稀疏数组后几行的数据，并赋给原始的二维数据

        //1、先读取稀疏数组的第一行
        int cherrArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2、再读取稀疏数组后几行的数据
        for (int i = 1; i < sparseArr.length; i++) {
            cherrArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //3、输出恢复后的数据
        System.out.println("稀疏数组转换成二维数组：");
        for(int[] row : cherrArr2){
            for(int data: row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
