package JavaBasicAlgorithm.S_Greedy;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/5/2.
 */
public class Prim {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,1000,6},
                {2,3,10000,10000,4,6,10000}};
        //创建MGraph对象
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,verxs,data,weight);
        minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);
    }
}

//创建最小生成树->村庄的图
class MinTree{
    //创建图的邻接矩阵

    /**
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char data[],int[][] weight){
        int i,j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j=0;j<verxs;j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的方法
    public void showGraph(MGraph graph){
        for (int[] link:graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，得到最小生成树

    /**
     *
     * @param graph  图
     * @param v   表示从图的第几个顶点开始生成'A'->0 'B'->1...
     */
    public void prim(MGraph graph,int v){
        //visited[]标记结点(顶点)是否被访问过
        int visited[] = new int[graph.verxs];
        //visited[]默认元素的值都是0，表示没有被访问过
        //当前结点设置为1
        visited[v] = 1;

        //把当前这个结点标记为已访问
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs顶点，普利姆算法结束后，有graph.verxs-1边

            for (int i = 0; i< graph.verxs; i++) {//i结点表示被访问过得结点
                for (int j = 0; j <graph.verxs ; j++) {//j结点表示没有被访问的节点
                    if (visited[i]==1 && visited[j]==0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }

            }
            //找到一条边最小
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2] +">权值:"+minWeight);

            //将当前这个结点标记为已访问
            visited[h2] = 1;
            //minWeight重新设置为最大值10000
            minWeight=10000;
        }

    }
}




class MGraph{
    int verxs;  //表示图的节点个数
    char[] data;//存放结点数据
    int[][] weight;//存放边，就是我们的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}