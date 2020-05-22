package JavaBasicAlgorithm.O_DFSandBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Create By 奇怪君 ON 2020/4/25.
 */
public class Graph {

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numofEdges;//表示边的数目
    private boolean[] isVisted; //定义数组boolean[] 记录某个节点是否被访问

    public static void main(String[] args) {
        //测试图是否创建OK
        int n=5; // 结点的个数
        String Vertexs[] = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        for (String vertex:Vertexs){
            graph.insertVertext(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示
        graph.showGraph();

        //dfs遍历
//        System.out.println("深度遍历");
//        graph.dfs();

        //bfs遍历
        System.out.println("广度遍历");
        graph.bfs();

    }

    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numofEdges = 0;
        isVisted = new boolean[5];
    }



    //插入结点
    public void insertVertext(String vertex){
        vertexList.add(vertex);
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到边的数目
    public int getNumofEdges(){
        return numofEdges;
    }
    //返回结点i对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link:edges){
            System.err.println(Arrays.toString(link));
        }
    }


    //添加边
    /**
     *
     * @param v1  点的下标
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numofEdges++;
    }

    //得到第一个邻接节点的下标w

    /**
     *
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for (int j = 0;j<vertexList.size();j++){
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标，获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for (int j = v2+1;j<vertexList.size();j++){
            if (edges[v1][j]>0 ){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历方法
    private void dfs(boolean[] isVisted,int i){
        //i 第一次就是0
        System.out.print(getValueByIndex(i)+"->");
        //将节点设置为已访问
        isVisted[i] = true;
        //查找节点的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1){//说明有邻接节点
            if (!isVisted[w]){
                dfs(isVisted,w);
            }
            //如果w被访问过
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs进行一个重载,遍历所有的节点
    public void dfs(){
        //遍历所有节点
        for (int j = 0; j < getNumOfVertex(); j++) {
            if (!isVisted[j]){
                dfs(isVisted,j);
            }
        }
    }


    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisted,int i){
        int u;//表示队列的头结点对应的下标
        int w;//邻接节点w
        //队列，记录结点的顺序
        LinkedList queue = new LinkedList();
        //访问结点，输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisted[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()){
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w!= -1){
                //是否访问过
                if (!isVisted[w]){
                    System.out.print(getValueByIndex(w) +"=>");
                    //标记已经访问
                    isVisted[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                //以u为前一个结点，找w后面的下一个邻接点
                w = getNextNeighbor(u,w);//体现出广度优先
            }
        }
    }
    //遍历所有的节点，进行广度优先遍历
    public void bfs(){
        for (int i = 0; i < getNumOfVertex() ; i++) {
            if (!isVisted[i]){
                bfs(isVisted,i);
            }

        }
    }


}
