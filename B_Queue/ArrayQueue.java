package JavaBasicAlgorithm.B_Queue;

import java.util.Scanner;

/**
 * Create By 奇怪君 ON 2020/4/8.
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Array2Queue queue =new Array2Queue(3);
        char key = ' '; //接收用户的输入
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        while (loop){

            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = in.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    queue.addQueue(in.nextInt());
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据是："+ queue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("头部数据是："+ queue.headQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    in.close();
                    loop = false;
                    break;

                default:break;
            }

        }
        System.out.println("程序退出！");
    }
}

class Array2Queue{
    private int maxSize;//表示数组的最大容量
    private int front; //队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列


    // 创建队列的构造器
    public Array2Queue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;   // 指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;    // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }


    //判断队列是否满
    public boolean isFull(){
        return  rear == maxSize - 1;
    }

    // 判断队列是否空
    public boolean isNull(){
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int num){
        if(isFull()){
            System.out.println("队列已满，不能添加数据");
            return;
        }
        rear++;
        arr[rear] = num;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        if(isNull()){
            throw new RuntimeException("队列为空，没有数据");
        }
        front++;
        return arr[front];
    }


    // 显示队列的所有数据
    public void showQueue(){
        if(isNull()){
            System.out.println(("队列为空，没有数据"));
            return;
        }
        System.out.println();
        for (int i = 0; i <arr.length ; i++) {
            System.out.printf("arr[%d] = %d\t",i,arr[i]);
        }
    }

    // 显示队列的头数据 ，注意不是取出数据
    public int headQueue(){
        if(isNull()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];  //注意+1，front指向队列头部的前一个位置
    }
}
