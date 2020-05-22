package JavaBasicAlgorithm.J_BinaryTree;

/**
 * Create By 奇怪君 ON 2020/4/20.
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }


}

//编写一个ArrayBinaryTree，实现顺序存储二叉树
class ArrayBinaryTree{
    private int[] arr;//存储数据节点的数组

    //我们重载一下preOrder
    public void preOrder(){
        this.preOrder(0);
    }


    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index){
        if (index < 0 || arr.length < 0){
            System.out.println("没有数据");
            return;
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if ((2*index+1)<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if ((2*index+2)<arr.length){
            preOrder(2*index+2);
        }
    }
}