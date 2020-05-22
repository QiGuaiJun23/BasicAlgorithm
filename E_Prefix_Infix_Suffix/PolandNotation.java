package JavaBasicAlgorithm.E_Prefix_Infix_Suffix;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Create By 奇怪君 ON 2020/4/13.
 */
public class PolandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式
        // （3+4）* 5 -6 => 3 4 + 5 * 6 - => 29
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String expression = "30 4 + 5 * 6 -";

        // 思路：
        // 1、先将"30 4 + 5 * 6 -" => 放到ArrayList中
        // 2、将ArrayList传递给一个方法，遍历ArrayList配合栈完成运算
//        List<String> list = getListString(expression);
//        System.out.println("list = "+list);
//
//        int res = calculate(list);
//        System.out.println("计算的结果是="+res);


        // 完成将一个中缀表达式转成后缀表达式的功能
        String expression1 = "1+((2+3)*4)-5";
        List<String> strings = toInfixExpressionList(expression1);
        //将得到的中缀表达式对应的List => 后缀表达式的List
        System.out.println(strings);
        List<String> suffixExpressionList = parseSuffixExpressionList(strings);
        System.out.println("后缀表达式对应的List"+suffixExpressionList);
        System.out.printf("expression=%d",calculate(suffixExpressionList));
    }

    public static List<String> getListString(String expression){
        String[] split  = expression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return list;
    }

    // 方法：将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<String>();//符号栈
        //s2栈在整个转换过程中，没有pop操作，而且后面还要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String>直接使用List<String> s2
        List<String> s2 = new ArrayList<String>();

        // 遍历ls
        for (String item:ls){
            //如果是一个数，加入S2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将左括号弹出
            }else {
                //当item的优先级小于等于栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while (s1.size() !=0 && Operation.getValue(s1.peek())>= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2中
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;//按顺序输出就是正常的逆序波兰
    }







    //方法：将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        // 定义一个List,存放中中缀表达式对应的内容
        List<String> ls = new ArrayList<String>();
        int i =0;//定义一个指针，用于遍历中缀表达式字符串
        String str;//对多位数进行拼接
        char c;//每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c=s.charAt(i))<48 || (c=s.charAt(i))>57){
                ls.add(""+c);
                i++;//i需要后移
            }else {//如果是一个数，则需要考虑到多位数
                str = "";
                while (i<s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
                    str +=c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }



    //
    public static int calculate(List<String> list){
        // 创建一个栈，只需要一个栈即可
        Stack<String> stack =new Stack<String>();
        for (String item:list){
            if(item.matches("\\d+")){//匹配多位数
                //入栈
                stack.push(item);
            }else {
                // pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res =0;
                if(item.equals("+")){
                    res = num1+num2;
                }else if(item.equals("-")){
                    res = num1-num2;
                }else if(item.equals("*")){
                    res = num1*num2;
                }else if(item.equals("/")){
                    res = num1/num2;
                }else{
                    throw new RuntimeException("运算符有误!");
                }
                stack.push(""+res);

            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类Operation可以返回一个运算符对应的优先级
class Operation{
    private static int ADD =1;
    private static int SUB =1;
    private static int MUL =2;
    private static int DIV =2;

    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
//                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}