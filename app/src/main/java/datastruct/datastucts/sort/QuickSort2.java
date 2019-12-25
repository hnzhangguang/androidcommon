package datastruct.datastucts.sort;

/**
 * 快速排序算法的第二种写法
 */

public class QuickSort2 {


    public static void quicksort(int arr[], int low, int high) {
        int i, j, k;                          //三剑客的约定
        if (low < high) {                  //先决条件
            i = low;
            j = high;
            k = arr[i];                   //找到适合位置
            while (i < j) {
                while (i < j && arr[j] > k) {
                    j--;
                }
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }
                while (i < j && arr[i] < k) {
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = k;                          //装到适合的位置
            quicksort(arr, low, i - 1);      //递归
            quicksort(arr, i + 1, high);   //递归
        }
    }


    public static void main(String ag[]) {
        int a[] = {23, 6, 7, 88, 76, 65, 44, 338, 59};
        quicksort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }


}
