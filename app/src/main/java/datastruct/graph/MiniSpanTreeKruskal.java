package datastruct.graph;

import java.util.ArrayList;
import java.util.List;


/**
 * 克鲁斯卡尔（Kruskal）算法
 * 判断是否为回路的机制没有理解
 */
public class MiniSpanTreeKruskal {

    /**
     * 邻接矩阵
     */
    private int[][] matrix;
    /**
     * 表示正无穷
     */
    private int MAX_WEIGHT = Integer.MAX_VALUE;
    /**
     * 边集数组
     */
    private List<Edge> edgeList = new ArrayList<Edge>();

    /**
     * 创建图
     */
    private void createGraph(int index) {
        matrix = new int[index][index];
        int[] v0 = {0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT,
                MAX_WEIGHT};
        int[] v1 = {10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12};
        int[] v2 = {MAX_WEIGHT, 18, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8};
        int[] v3 = {MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21};
        int[] v4 = {MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT};
        int[] v5 = {11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT};
        int[] v6 = {MAX_WEIGHT, 16, MAX_WEIGHT, 24, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT};
        int[] v7 = {MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT};
        int[] v8 = {MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0};
        matrix[0] = v0;
        matrix[1] = v1;
        matrix[2] = v2;
        matrix[3] = v3;
        matrix[4] = v4;
        matrix[5] = v5;
        matrix[6] = v6;
        matrix[7] = v7;
        matrix[8] = v8;
    }

    /**
     * 创建边集数组，并且对他们按权值从小到大排序（顺序存储结构也可以认为是数组吧）
     */
    public void createEdages() {

        Edge v0 = new Edge(4, 7, 7);
        Edge v1 = new Edge(2, 8, 8);
        Edge v2 = new Edge(0, 1, 10);
        Edge v3 = new Edge(0, 5, 11);
        Edge v4 = new Edge(1, 8, 12);
        Edge v5 = new Edge(3, 7, 16);
        Edge v6 = new Edge(1, 6, 16);
        Edge v7 = new Edge(5, 6, 17);
        Edge v8 = new Edge(1, 2, 18);
        Edge v9 = new Edge(6, 7, 19);
        Edge v10 = new Edge(3, 4, 20);
        Edge v11 = new Edge(3, 8, 21);
        Edge v12 = new Edge(2, 3, 22);
        Edge v13 = new Edge(3, 6, 24);
        Edge v14 = new Edge(4, 5, 26);

        edgeList.add(v0);
        edgeList.add(v1);
        edgeList.add(v2);
        edgeList.add(v3);
        edgeList.add(v4);
        edgeList.add(v5);
        edgeList.add(v6);
        edgeList.add(v7);
        edgeList.add(v8);
        edgeList.add(v9);
        edgeList.add(v10);
        edgeList.add(v11);
        edgeList.add(v12);
        edgeList.add(v13);
        edgeList.add(v14);
    }

    // 克鲁斯卡尔算法  
    public void kruskal() {
        //创建图和边集数组  
        createGraph(9);
        //可以由图转出边集数组并按权从小到大排序，这里为了方便观察直接写出来了  
        createEdages();

        //定义一个数组用来判断边与边是否形成环路  
        int[] parent = new int[9];

        /**权值总和*/
        int sum = 0;

        int n, m;

        //遍历边  
        for (int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            n = find(parent, edge.getBegin());
            m = find(parent, edge.getEnd());

            //说明形成了环路或者两个结点都在一棵树上  
            //注：书上没有讲解为什么这种机制可以保证形成环路，思考了半天，百度了也没有什么好的答案，研究的时间不多，就暂时就放一放吧  
            if (n != m) {
                parent[n] = m;
                System.out.println("(" + edge.getBegin() + "," + edge.getEnd() + ")" + edge
                        .getWeight());

                sum += edge.getWeight();
            }
        }

        System.out.println("权值总和为：" + sum);
    }

    public int find(int[] parent, int index) {
        while (parent[index] > 0) {
            index = parent[index];
        }
        return index;
    }

    public void main(String[] args) {
        MiniSpanTreeKruskal graph = new MiniSpanTreeKruskal();
        graph.kruskal();
    }

}

class Edge {

    private int begin;
    private int end;
    private int weight;

    public Edge(int begin, int end, int weight) {
        super();
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge [begin=" + begin + ", end=" + end + ", weight=" + weight + "]";
    }
}  