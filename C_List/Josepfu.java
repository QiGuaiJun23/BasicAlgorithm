package JavaBasicAlgorithm.C_List;

/**
 * Create By 奇怪君 ON 2020/4/10.
 */
public class Josepfu {
    public static void main(String[] args) {

        //测试一把看看构建环形链表和遍历是否OK
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试出圈
        circleSingleLinkedList.countBoy(1,2,5);

    }
}


//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    Boy first = new Boy(-1);
    //添加节点构造环形链表
    public void addBoy(int nums){
        Boy temp= null;
        if (nums <1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;//辅助指针，帮助我们构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            //创建节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1){
                first = boy;
                first.setNext(first);  //构成环
                curBoy=first;//让curBoy指向第一个小孩
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
        
    }


    //遍历当前环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("没有任何小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d \n",curBoy.getNo());
            if (curBoy.getNext() == first){
                //遍历完毕
                break;
            }
            curBoy= curBoy.getNext();//后移
        }
    }


    // 根据用户的输入，计算出小孩出圈的顺序
    /***
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if (first==null || startNo<1 || startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针
        Boy helper = first;
        //事先指向环形链表的最后节点
        while (true){
            if (helper.getNext()==first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数，先让first和helper移动k-1次
        for (int i = 0; i < startNo-1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }

        //当小孩报数时，让first和helper指针同时移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            if (helper==first){
                //说明圈中只有一个节点
                break;
            }
            //让first和helper指针同时移动coutNum-1
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
    }
}







//创建一个小孩的对象
class Boy{
    private int no;
    private Boy next;
    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}