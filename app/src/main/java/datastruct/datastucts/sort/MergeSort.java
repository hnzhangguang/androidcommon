package datastruct.datastucts.sort;

/**
 * 归并排序
 */

public class MergeSort {

    public static void mergesort(int arr[], int start, int end) {     //主函数
        if (start < end) {
            int mid = (start + end) / 2;                 //关键一步
            mergesort(arr, start, mid);                //递归
            mergesort(arr, mid + 1, end);             //递归
            merge(arr, start, mid, mid + 1, end);     //调用下面的函数
        }
    }

    public static void merge(int arr[], int start1, int end1, int start2, int end2) {        //被调用的函数
        int i, j, k = 0;                                         //临时变量的使用
        i = start1;
        j = start2;
        int temp[] = new int[end2 - start1 + 1];    //临时数组的大小规格
        while (i <= end1 && j <= end2) {
            if (arr[i] > arr[j]) {
                temp[k++] = arr[j++];
            } else {
                temp[k++] = arr[i++];
            }
        }
        //把剩下的元素依次放入临时数组中
        while (i <= end1) {
            temp[k++] = arr[i++];
        }
        while (j <= end2) {
            temp[k++] = arr[j++];
        }
        //归还到原来的数组中去
        k = start1;
        for (int element : temp) {
            arr[k++] = element;
        }
    }


}
