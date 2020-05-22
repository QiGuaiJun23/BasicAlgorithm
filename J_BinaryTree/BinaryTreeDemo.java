package JavaBasicAlgorithm.J_BinaryTree;


/**
 * Create By 奇怪君 ON 2020/4/19.
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        //测试
//        System.out.println("前序遍历");
//        binaryTree.perOrder();

//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOrder();

        //前序查找
//        System.out.println("前序查找方式");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        if (resNode!= null){
//            System.out.printf("找到了，信息为no = %d name = %s",resNode.getNo(),resNode.getName());
//        }else{
//            System.out.printf("没有找到no = %d的英雄",5);
//        }
        //删除
        System.out.println("删除前，前序遍历");
        binaryTree.perOrder();
        binaryTree.delNode(4);
        System.out.println("删除后，前序遍历");
        binaryTree.perOrder();


    }

}


//定义BinaryTree
class BinaryTree{
    private HeroNode root;
    public void setRoot(HeroNode root){
        this.root = root;
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
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrdersearch(no);
        }else {
            return null;
        }
    }
    //前序查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrdersearch(no);
        }else {
            return null;
        }
    }
    //前序查找
    public HeroNode postOrderSearch(int no){
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



class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认为空
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
    public HeroNode preOrdersearch(int no){
        //比较当前结点是不是
        if (this.no == no){
            return this;
        }
        HeroNode resNode = null;
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
    public HeroNode infixOrdersearch(int no){
        HeroNode resNode = null;
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
    public HeroNode postOrdersearch(int no){
        HeroNode resNode = null;
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