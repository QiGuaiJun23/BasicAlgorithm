package JavaBasicAlgorithm.F_Recursion;

/**
 * Create By 奇怪君 ON 2020/4/16.
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i=0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i=0;i<8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
//        for (int i=0;i<8;i++){
//            for (int j=0;j<7;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }


        if (setWay(map,1,1)){
            for (int i=0;i<8;i++){
                for (int j=0;j<7;j++){
                    System.out.print(map[i][j]+" ");
                }
                System.out.println();
            }
        }
    }

    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5] == 2){
            return true;
        }else {
            if (map[i][j] == 0){//如果当前这个点没有走过
                map[i][j] = 2; //假定该点可以走通
                if (setWay(map,i+1,j)){//向下走
                    return true;
                }else if (setWay(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i-1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else{
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
