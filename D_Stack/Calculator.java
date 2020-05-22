package JavaBasicAlgorithm.D_Stack;

/**
 * Create By 奇怪君 ON 2020/4/12.
 */
public class Calculator {

    public static void main(String[] args) {
        //定义一个表达式
        String expression = "710+6*2-5";
        Array2Stack2 numStack = new Array2Stack2(10);
        Array2Stack2 operStack = new Array2Stack2(10);
        //定义辅助变量
        int index = 0;   //索引，来遍历表达式
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到char保存到ch
        String keepNum ="";//用于拼接
        while (true){
            //依次得到表达式的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operStack.isOper(ch)){//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        //然后将当前的操作符压入符号栈
                        operStack.push(ch);
                    }else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }

                }else {
                    //如果当前的符号栈为空
                    operStack.push(ch);
                }
            }else {
                //如果是数，则直接入数栈

                //思路分析
                // 1、当处理多位数时，不能发现是一个数就立即入栈，因为它可能是多位数
                // 2、在处理数，需要向expression的表示的index后再看一位，如果是数就进行扫描，如果是符号位才入栈
                //3、因此我们需要定义一个变量字符串，用于拼接

                keepNum += ch;

                //考虑到数组越界问题
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));

                        //重要！！！keepNum需要清空
                        keepNum ="";
                    }
                }

            }
            index +=1;
            if (index >= expression.length()){
                break;
            }
        }


        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);//入栈

        }
        //将数栈的最后数，pop出
        System.out.printf("表达式 %s = %d",expression,numStack.pop());
    }
}



class Array2Stack2{
    private int maxSize;
    private int top = -1;
    private int[] stack;
    //构造器
    public Array2Stack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }


    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int num){
        if (isFull()){
            System.out.println("栈满，不能存放");
            return;
        }
        top++;
        stack[top] = num;
    }
    //栈顶元素
    public int peek(){
        return stack[top];
    }

    // 出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，不能出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的情况[遍历栈]

    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }


    //设置优先级
    //优先级是由程序员确定的，数字越大，优先级越高
    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }
        else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是否是运算符
    public boolean isOper(char val){
        return val=='+' || val=='-'|| val=='*' || val=='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0; //用于存放计算结果
        switch (oper){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num1 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}