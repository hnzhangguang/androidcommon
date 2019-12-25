package datastruct.datastucts.sort;

/**
 * 冒泡排序
 */

public class BubbleSort {

    public static void bubblesort(int arr[]) {
        boolean b = false;
        int temp;
        for (int i = 1; i < arr.length; i++) {       //下标从第二个数开始，所以是1
            b = false;
            for (int j = arr.length - 1; j >= i; j--) {
                if (arr[j] < arr[j - 1]) {                //满足条件时，交换
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    b = true;
                }
            }
            if (!b) {
                break;
            }
        }

    }


}
