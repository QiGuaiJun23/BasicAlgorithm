package JavaBasicAlgorithm.J_BinaryTree;

/**
 * Create By 奇怪君 ON 2020/4/20.
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedHeroNode root = new ThreadedHeroNode(1, "tom");
        ThreadedHeroNode node2 = new ThreadedHeroNode(3, "jack");
        ThreadedHeroNode node3 = new ThreadedHeroNode(6, "smith");
        ThreadedHeroNode node4 = new ThreadedHeroNode(8, "mary");
        ThreadedHeroNode node5 = new ThreadedHeroNode(10, "king");
        ThreadedHeroNode node6 = new ThreadedHeroNode(14, "dim");
        //二叉树，手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNode();
        //测试，以10号节点测试
        ThreadedHeroNode left = node5.getLeft();
        ThreadedHeroNode right = node5.getRight();
//        System.out.println("10号节点的前驱结点："+left);//3
//        System.out.println("10号节点的后继节点："+right);//1

        threadedBinaryTree.threadedList();//输出结果：{8,3,10,1,14,6}
    }
}
//定义ThreadedBinaryTree实现了线索化功能的二叉树
class ThreadedBinaryTree{
    private ThreadedHeroNode root;
    //为了实现线索化，需要创建指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre总是保留前一个结点
    private ThreadedHeroNode pre=null;

    public void setRoot(ThreadedHeroNode root){
        this.root = root;
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点，从root开始
        ThreadedHeroNode node = root;
        while (node != null){
            //循环的找到leftType==1的结点，第一个找到的就是8节点
            //后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化
            //处理后的有效节点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继节点，就一直输出
            while (node.getRightType()==1){
                //获取到当前结点的后继节点
                node= node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }


    //编写对二叉树进行中序线索化的方法

    //重载一下threadedNode
    public void threadedNode(){
        this.threadedNode(root);
    }

    /**
     *
     * @param node  就是当前需要线索化的节点
     */
    public void threadedNode(ThreadedHeroNode node){
        //如果node==null，不能线索化
        if (node==null){
            return;
        }

        //（一）先线索化左子树
        threadedNode(node.getLeft());
        //（二）线索化当前节点
        //处理当前结点的前驱结点
        if (node.getLeft()==null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型，指向前驱结点
            node.setLeftType(1);
        }

        //处理当前结点的右指针指向后继节点
        if (pre!=null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //!!! 每处理一个节点后，让当前结点指向下一个节点的前驱结点
        pre = node;
        //（三）线索化右子树
        threadedNode(node.getRight());

    }

    //前序遍历
    public void perOrder(){
        if (this.root!= null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root!= null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root!= null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public ThreadedHeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrdersearch(no);
        }else {
            return null;
        }
    }
    //前序查找
    public ThreadedHeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrdersearch(no);
        }else {
            return null;
        }
    }
    //前序查找
    public ThreadedHeroNode postOrderSearch(int no){
        if (root != null){
            return root.postOrdersearch(no);
        }else {
            return null;
        }
    }
    //删除结点
    public void delNode(int no){
        if (root!= null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.delNode(no);
            }
        }else {
            System.out.println("空树，不能删除");
        }
    }

}


class ThreadedHeroNode{
    private int no;
    private String name;
    private ThreadedHeroNode left;//默认为空
    private ThreadedHeroNode right;

    //说明
    //1. 如果leftType==0 表示指向的是左子树，如果1，则表示指向前驱节点
    //2. 如果rightType==0 表示指向的是右子树，如果1，则表示后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public ThreadedHeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadedHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedHeroNode left) {
        this.left = left;
    }

    public ThreadedHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadedHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父结点
        //递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找

    /**
     *
     * @param no  查找no
     * @return    如果找到就返回该Node，如果没有找到返回null
     */
    public ThreadedHeroNode preOrdersearch(int no){
        //比较当前结点是不是
        if (this.no == no){
            return this;
        }
        ThreadedHeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.preOrdersearch(no);
        }
        if (resNode != null){//说明我们左子树找到
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.preOrdersearch(no);//有可能为null,有可能不为null，不需要再对resNode进行判断，直接输出就可以
        }
        return resNode;
    }


    // 中序遍历查找

    /**
     *
     * @param no  查找no
     * @return    如果找到就返回该Node，如果没有找到返回null
     */
    public ThreadedHeroNode infixOrdersearch(int no){
        ThreadedHeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrdersearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        //比较当前结点是不是
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrdersearch(no);
        }
        return resNode;
    }

    // 后序遍历查找

    /**
     *
     * @param no  查找no
     * @return    如果找到就返回该Node，如果没有找到返回null
     */
    public ThreadedHeroNode postOrdersearch(int no){
        ThreadedHeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrdersearch(no);
        }
        if (resNode != null){
            return resNode;
        }

        if (this.right != null){
            resNode = this.right.postOrdersearch(no);
        }
        if (resNode != null){
            return resNode;
        }

        //比较当前结点是不是
        if (this.no == no){
            return this;
        }
        return resNode;
    }

    //递归删除结点
    //1. 如果删除的节点是叶子结点，则删除该节点
    //2. 如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){

        if (this.left != null &&this.left.no == no){
            this.left = null;
            return;
        }

        if (this.right != null &&this.right.no == no){
            this.right = null;
            return;
        }

        if (this.left != null){
            this.left.delNode(no);
        }
        if (this.right != null){
            this.right.delNode(no);
        }

    }


}