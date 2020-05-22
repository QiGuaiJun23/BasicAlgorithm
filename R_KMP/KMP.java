package JavaBasicAlgorithm.R_KMP;

/**
 * Create By 奇怪君 ON 2020/5/1.
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "af ji foivi ifobjjbejrpgje";
        String str2 = "foivi";
        int[] ints = kmpNext(str2);
        int result = kmpSearch(str1, str2, ints);
        System.out.println(result);

    }

    //写出KMP搜索算法

    /**
     *
     * @param s1 源字符串
     * @param s2  子串
     * @param next  部分匹配表，是子串对应的部分匹配表
     * @return
     */
    public static int kmpSearch(String s1,String s2,int[] next){
        //遍历
        for (int i = 0,j=0; i <s1.length() ; i++) {
            //需要处理s1.charAt(i) != s2.charAt(j)
            //KMP核心
            while (j>0 && s1.charAt(i) != s2.charAt(j)){
                j = next[j-1];
            }

            if (s1.charAt(i) == s2.charAt(j)){
                j++;
            }
            if (j == s2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //获取到一个字符串(子串)的部分匹配值
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为1  部分匹配值就是0
        for (int i = 1,j=0; i < next.length ; i++) {
            //当dest.charAt(i) != dest.charAt(j)我们需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出
            //这是kmp算法的核心点
            while (j>0&&dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
