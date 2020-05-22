package JavaBasicAlgorithm.L_Huffman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Create By 奇怪君 ON 2020/4/21.
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node node = createHuffmanTree(arr);
        preOrder(node);

    }
    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    //创建哈夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //1. 遍历arr的数组
        //2. 将arr的每一个元素构成一个Node
        //3.  将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出根节点权值最小的两颗二叉树
            //1.取出权值最小的两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //2.构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //3. ArrayList中删除处理过得二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //4.将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼的root节点
        return nodes.get(0);
    }


}


//创建结点类
//为了让Node 对象持续排序Collections集合排序
//让Node实现Compare接口
class Node implements Comparable<Node> {
    int value;//结点权值
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {//实现从小到大排序
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
