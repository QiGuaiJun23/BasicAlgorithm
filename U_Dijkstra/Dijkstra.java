package JavaBasicAlgorithm.U_Dijkstra;

import java.util.Arrays;

/**
 * Create By 奇怪君 ON 2020/5/15.
 */
public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDijkstra();

    }
}


//定义图结构
class Graph{
    char[] vertex;  //顶点数组
    int[][] matrix; //邻接矩阵
    private VisitedVertex vv;//已经访问的节点的坐标

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图结构
    public void showGraph(){
        for (int[] link:matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
    //迪杰斯特拉算法
    /**
     *
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 0; i < vertex.length ; i++) {
            index = vv.updateArr();//返回新的访问顶点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
    private void update(int index){
        int len = 0;
        //根据遍历我们的邻接矩阵matrix[index]行
        for (int i = 0; i < matrix[index].length ; i++) {
            len = vv.getDis(index)+ matrix[index][i];
            //如果顶点i没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(i)&&len<vv.getDis(i)){
                vv.updatePre(i,index);
                vv.updateDis(i,len);
            }
        }
    }

    //显示最终的结果
    public void showDijkstra(){
        vv.show();
    }
}


//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过  1表示访问过，0表示未访问  会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，会动态更新，就的最短距离就会存放到到dis中
    public int[] dis;


    /**
     *
     * @param length 表示顶点的个数
     * @param index   表示顶点的下标
     */
    public VisitedVertex(int length,int index){
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        //初始化dis
        Arrays.fill(dis,65535);
        dis[index] = 0;
        //设置出发顶点被访问过
        this.pre_visited[index] = 1;
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index){
        return already_arr[index]==1;
    }

    /**
     * 更新出发点到index顶点的距离
     * @param index 顶点的下标
     * @param len  值
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新pre这个顶点的前驱顶点为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }


    //继续选择并返回新的访问结点，比如这里的G完后，就是A点作为新的访问顶点(注意不是出发顶点)
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length ; i++) {
            if (already_arr[i] == 0&&dis[i]<min){
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }


    //显示最后的结果
    //即将三个数组的结果进行输出
    public void show(){
        System.out.println("================================");
        //输出already_arr
        for (int i:already_arr){
            System.out.print(i+" ");
        }
        System.out.println();
        //输出pre_visited
        for (int i:pre_visited){
            System.out.print(i+" ");
        }
        System.out.println();
        //输出dis
        for (int i:dis){
            System.out.print(i+" ");
        }
    }
}