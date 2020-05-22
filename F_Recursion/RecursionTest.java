package JavaBasicAlgorithm.F_Recursion;

/**
 * Create By 奇怪君 ON 2020/4/16.
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        System.out.println(factorial(6));
    }

    public static void test(int n){
        if (n>2){
            test(n-1);
        }
        System.out.println("n="+n);
    }

    public static int factorial(int n){
        if (n==1){
            return 1;
        }else{
            return factorial(n-1)*n;
        }
    }
}
