package JavaBasicAlgorithm.P_DivideConquer;

/**
 * Create By 奇怪君 ON 2020/4/26.
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }
    public static void hanoiTower(int num,char a,char b,char c){
        if (num == 1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else {
            //1. 先把最上面的所有盘A->B，移动过程会使用到C
            hanoiTower(num -1,a,c,b);
            //2. 把最下面的盘A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3. 把B塔的所有盘从B->C,移动过程使用到a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}
