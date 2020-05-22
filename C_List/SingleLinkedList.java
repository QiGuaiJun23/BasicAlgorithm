package JavaBasicAlgorithm.C_List;

import java.security.PublicKey;

/**
 * Create By 奇怪君 ON 2020/4/9.
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        //添加到链表
        SingleLinkedListHero singleLinkedList = new SingleLinkedListHero();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.list();

        //测试修改节点的代码
//        HeroNode newHeroNode = new HeroNode(2,"小卢","玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改后的节点信息：");
//        singleLinkedList.list();

        //测试删除节点的代码
        singleLinkedList.deletHeroNode(1);
        System.out.println("删除后的节点信息：");
        singleLinkedList.list();
    }
}

//定义SingleLinkedList管理我们的英雄

class SingleLinkedListHero{
    HeroNode head =new HeroNode(0,"","");//定义一个头结点

    //添加节点到单项链表中
    public void add(HeroNode heroNode){
        HeroNode temp = head;
        while (true){
            //找到链表最后
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //退出循环时，temp指向链表的最后
        temp.next = heroNode;
    }

    //修改节点信息，根据no编号来修改，即no编号不能改
    //说明：根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        HeroNode temp = head;
        boolean flag = false;
        if (head.next == null){
            System.out.println("链表为空~");
        }
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHeroNode.no){
                flag =true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号 %d 的节点,不能修改\n",newHeroNode.no);
        }

    }

    // 删除节点
    //思路
    public void deletHeroNode(int no){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                break;
            }
            if(temp.next.no == no){
                flag = true;
                break;
            }
            temp =temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("没有找到待删除的节点！");
        }
    }




    //显示链表
    public void list(){
        if(head.next == null){
            return;
        }
        //通过辅助变量temp进行遍历
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            //后移
            temp =temp.next;
        }
    }


    // 第二种方式在添加英雄时，根据排名将英雄插入到指定位置中
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag  = false;//标志添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                flag =true;
                break;
            }
            temp = temp.next;
        }
        if (flag == true){
            System.out.println("编号已经存在！");
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }
}




// 定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为了显示方法，我们重新toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}