package JavaBasicAlgorithm.D_Stack;

import java.util.Scanner;

/**
 * Create By 奇怪君 ON 2020/4/11.
 */
public class ArrayStack {
    public static void main(String[] args) {
        //测试
        Array2Stack array2Stack = new Array2Stack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop){
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop：表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    array2Stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字：");
                    array2Stack.push(scanner.nextInt());
                    break;
                case "pop":
                    try {
                        int value = array2Stack.pop();
                        System.out.println("出栈的数据是："+value);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

//定义一个Array2Stack表示栈
class Array2Stack{
    private int maxSize;
    private int top = -1;
    private int[] stack;
    //构造器
    public Array2Stack(int maxSize){
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
}