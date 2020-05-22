package JavaBasicAlgorithm.M_BST;


/**
 * Create By 奇怪君 ON 2020/4/23.
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree node = new BinarySortTree();
        //循环添加节点
        for (int i = 0; i < arr.length; i++) {
            node.add(new BinarySortTreeNode(arr[i]));
        }
        System.out.println("中序遍历二叉排序树");
        node.infixOrder();//{1,3,5,7,9,10,12}
        node.delNode(10);
        System.out.println("删除结点");
        node.infixOrder();//{1,3,5,7,9,10,12}

    }
}

//创建二叉排序树
class BinarySortTree {
    private BinarySortTreeNode root;

    //添加节点的方法
    public void add(BinarySortTreeNode node) {
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
    public BinarySortTreeNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public BinarySortTreeNode searchParent(int value) {
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
    public int delRightTreeMin(BinarySortTreeNode node) {
        BinarySortTreeNode target = node;
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
            BinarySortTreeNode targetNode = search(value);
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
            BinarySortTreeNode parent = searchParent(value);
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
class BinarySortTreeNode {
    int value;
    BinarySortTreeNode left;
    BinarySortTreeNode right;

    public BinarySortTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "value=" + value +
                '}';
    }

    //添加节点
    public void add(BinarySortTreeNode node) {
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
        }
    }

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
    public BinarySortTreeNode search(int value) {
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
    public BinarySortTreeNode searchParent(int value) {
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