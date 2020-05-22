package JavaBasicAlgorithm.R_KMP;

/**
 * Create By 奇怪君 ON 2020/4/27.
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "afjifoivjifobjjbejrpgje";
        String str2 = "foivi";
        int index = violenceMatch(str1,str2);
        System.out.println(index);
    }

    //暴力匹配法
    public static int violenceMatch(String s1,String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();

        int len1 = str1.length;
        int len2 = str2.length;

        int i=0;
        int j =0;
        while (i<len1 && j<len2){
            if (str1[i] == str2[j]){
                i++;
                j++;
            }else{
                i = i -(j-1);
                j=0;
            }
        }
        if (j == len2){
            return (i-j);
        }else {
            return -1;
        }
    }
}
