package JavaBasicAlgorithm.C_List;



/**
 * Create By 奇怪君 ON 2020/4/10.
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        //测试
        System.out.println("双向链表的测试");

        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");


        DoubleLinkedListHero doubleLinkedList = new DoubleLinkedListHero();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改之后的链表");
        doubleLinkedList.list();

        doubleLinkedList.deletHeroNode(3);
        System.out.println("删除之后的链表");
        doubleLinkedList.list();
    }
}




class DoubleLinkedListHero {
    HeroNode2 head = new HeroNode2(0, "", "");//定义一个头结点



    //添加节点到双项链表尾
    public void add(HeroNode2 heroNode){
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }


    //修改一个节点的内容
    public void update(HeroNode2 newHeroNode){
        HeroNode2 temp = head;
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



    //删除一个结点
    //1、对于双向链表可以直接找到要删除的点
    public void deletHeroNode(int no){
        if(head.next ==null){
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp =temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //这里的代码有问题？如果是最后一个节点，否则会出现空指针
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }

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
        HeroNode2 temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            //后移
            temp =temp.next;
        }
    }
}




// 定义HeroNode2,每个HeroNode2对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为了显示方法，我们重新toString

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}