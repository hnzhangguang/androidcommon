package datastruct.graph;


/**
 * 最短路径树
 * <p>
 * 普里姆（Prim）算法
 * <p>
 * 个人认为此算法遍历顺序的决定条件：
 * <p>
 * 1.确定第一个顶点
 * <p>
 * 2.下一个顶点可到（小于正无穷）
 * <p>
 * 3.取可到顶点中最小权值的一个
 */
public class MinSpanTree {
    /**
     * 邻接矩阵
     */
    int[][] matrix;
    /**
     * 表示正无穷
     */
    int MAX_WEIGHT = Integer.MAX_VALUE;
    /**
     * 顶点个数
     */
    int size;

    /**
     * 普里姆算法实现最小生成树：先初始化拿到第一个顶点相关联的权值元素放到数组中－》找到其中权值最小的顶点下标－》再根据该下标，将该下标顶点相关联的权值加入到数组中－》循环遍历处理
     */
    public void prim() {
        /**存放当前到全部顶点最小权值的数组，如果已经遍历过的顶点权值为0，无法到达的为正无穷*/
        int[] tempWeight = new int[size];
        /**当前到下一个最小权值顶点的最小权值*/
        int minWeight;
        /**当前到下一个最小权值的顶点*/
        int minId;
        /**权值总和*/
        int sum = 0;

        //第一个顶点时，到其他顶点的权值即为邻接矩阵的第一行  
        for (int i = 0; i < size; i++) {
            tempWeight[i] = matrix[0][i];
        }

        System.out.println("从顶点v0开始查找");
        for (int i = 1; i < size; i++) {
            // 每次循环找出当前到下一个最小权值的顶点极其最小权值   
            minWeight = MAX_WEIGHT;
            minId = 0;
            for (int j = 1; j < size; j++) {
                //权值为0的顶点已经遍历过，不再计入  
                if (tempWeight[j] > 0 && tempWeight[j] < minWeight) {
                    minWeight = tempWeight[j];
                    minId = j;
                }
            }

            // 找到目标顶点minId,他的权值为minweight。  
            System.out.println("找到顶点:v" + minId + " 权值为：" + minWeight);
            sum += minWeight;


            // 算法核心所在：将目标顶点到各个顶点的权值与当前tempWeight数组中的权值做比较，如果前者比后者到某个顶点的权值更小，将前者到这个顶点的权值更新入后者。  
            tempWeight[minId] = 0;
            for (int j = 1; j < size; j++) {
                if (tempWeight[j] != 0 && matrix[minId][j] < tempWeight[j]) {
                    tempWeight[j] = matrix[minId][j];
                }
            }
        }
        System.out.println("最小权值总和为：" + sum);
    }

    private void createGraph(int index) {
        size = index;
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

    public void main(String[] args) {
        MinSpanTree graph = new MinSpanTree();
        graph.createGraph(9);
        graph.prim();
    }

}  