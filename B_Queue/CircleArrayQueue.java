package JavaBasicAlgorithm.B_Queue;

import java.util.Scanner;

/**
 * Create By 奇怪君 ON 2020/4/8.
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray2Queue queue =new CircleArray2Queue(4);//说明设置4，其队列的有效数据最大是3
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


class CircleArray2Queue{

    private int maxSize;//表示数组的最大容量
    //front变量的含义做一个调整：front就指向队列的第一个元素，也就是说arr[front]就是队列的第一元素 ，front的初始值=0
    private int front;
    //rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置， 因为希望空出一个空间做为约定，rear的初始值=0
    private int rear;

    private int[] arr;//该数据用于存放数据，模拟队列

    // 创建队列的构造器
    public CircleArray2Queue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return  (rear+1)%maxSize == front;
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
        arr[rear] = num;
        //将rear后移
        rear = (rear+1)%maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        if(isNull()){
            throw new RuntimeException("队列为空，没有数据");
        }
        // 需要一个临时变量保存arr[front]
        int value = arr[front];
        //front后移，主要要取模
        front = (front+1)%maxSize;
        return value;
    }
    // 显示队列的所有数据
    public void showQueue(){
        if(isNull()){
            System.out.println(("队列为空，没有数据"));
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i <front + size(); i++) {
            System.out.printf("arr[%d] = %d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
    // 显示队列的头数据 ，注意不是取出数据
    public int headQueue(){
        if(isNull()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}