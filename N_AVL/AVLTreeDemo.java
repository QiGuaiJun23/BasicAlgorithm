package JavaBasicAlgorithm.N_AVL;



/**
 * Create By 奇怪君 ON 2020/4/24.
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7,8};
//        int[] arr={10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        //创建一个AVL树
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length ; i++) {
            avlTree.add(new AVLTreeNode(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println("树的高度");
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}

//创建AVL树
class AVLTree {
    private AVLTreeNode root;
    //获得根节点
    public  AVLTreeNode getRoot(){
        return root;
    }

    //添加节点的方法
    public void add(AVLTreeNode node) {
        if (root == null) {
            root = node;//如果root为空，则直接让root指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }


    //查找要删除的结点
    public AVLTreeNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public AVLTreeNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //编写方法：
    //1. 返回的以node为根节点的二叉排序树的最小节点的值
    //2. 删除node为根节点的二叉排序树的最小结点

    /**
     * @param node 传入的结点(当做二叉排序树的根节点)
     * @return 返回的以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(AVLTreeNode node) {
        AVLTreeNode target = node;
        //循环查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除叶子结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1. 需要先找到删除的节点，targetNode
            AVLTreeNode targetNode = search(value);
            //如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这棵二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            //找到targetNode的父结点
            AVLTreeNode parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父结点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两棵子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一棵子树的结点
                //如果要删除的结点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }

                } else {//如果要删除的节点有右子节点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }
        }
    }

}




//创建结点
class AVLTreeNode {
    int value;
    AVLTreeNode left;
    AVLTreeNode right;

    public AVLTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +
                '}';
    }

    //添加节点
    public void add(AVLTreeNode node) {
        if (node == null) {
            return;
        }
        //判断传入的节点与当前结点的值的关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                //递归左子树
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                //递归右子树
                this.right.add(node);
            }
            return;//必须要
        }

        //当添加完一个节点后，如果右子树的高度 - 左子树的高度 > 1
        if (rightHeight() - leftHeight() >1){
            //如果它的右子树的左子树的高度大于右子树的右子树的高度
            if (right!=null && right.rightHeight() < right.leftHeight()){
                //先对右子树进行右旋转
                right.rightRotate();
                //然后再对当前结点进行左旋转
                leftRotate();
            }else{
                leftRotate();//左旋转
            }

        }


        //当添加完一个节点后，如果左子树的高度- 右子树的高度 > 1，右旋转
        if (leftHeight() -rightHeight() >1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left!=null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左子树--->左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            }else {
                //直接进行右旋转即可
                rightRotate();
            }
        }
    }


    //返回当前结点的高度,以该节点为根节点的树的高度
    public int height(){
        return Math.max(left==null?0:left.height(),right==null?0:right.height())+1;
    }
    //返回左子树的高度
    public int leftHeight(){
        if(left==null){
            return 0;
        }
        return left.height();
    }
    //返回右子树的高度
    public int rightHeight(){
        if (right==null){
            return 0;
        }
        return right.height();
    }

    //左旋转方法
    private void leftRotate(){
        //创建新的节点，以当前根节点的值
        AVLTreeNode newNode = new AVLTreeNode(value);
        //把新节点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树设置成新节点
        left = newNode;

    }

    // 右旋转
    private void rightRotate(){
        AVLTreeNode newNode = new AVLTreeNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left=left.left;
        right = newNode;
    }

    //


    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    //查找要删除的结点

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public AVLTreeNode search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除节点的父结点

    /**
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没哟就返回null
     */
    public AVLTreeNode searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左递归
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右递归
            } else {
                return null;//没有找到父结点
            }
        }
    }

}