package datastruct.datastucts.sort;

/**
 * 快速排序算法
 */

public class QuickSort {


    public int data[];


    private int partition(int sortArray[], int low, int hight) {
        int key = sortArray[low];
        while (low < hight) {
            while (low < hight && sortArray[hight] >= key)
                hight--;
            sortArray[low] = sortArray[hight];

            while (low < hight && sortArray[low] <= key)
                low++;
            sortArray[hight] = sortArray[low];
        }
        sortArray[low] = key;
        return low;
    }


    public void sort(int array[], int low, int hight) {
        if (low < hight) {
            int result = partition(array, low, hight);
            sort(array, low, result - 1);
            sort(array, result + 1, hight);
        }
    }


    public void display() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            System.out.print(" ");
        }
    }


    /**
     * 测试函数
     *
     * @param args
     */
    public void main(String[] args) {

        int data[] = {44, 22, 2, 32, 54, 23, 88, 77, 99, 11};
        sort(data, 0, data.length - 1);
        display();


    }


}
