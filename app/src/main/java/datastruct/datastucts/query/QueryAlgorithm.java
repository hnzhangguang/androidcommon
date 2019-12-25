package datastruct.datastucts.query;

/**
 * 1. 二分查找
 * 2. 插值查找
 * 4. 斐波那契查找
 * 5. 树表查找
 * 6. 分块查找
 * 7. 哈希查找
 */

public class QueryAlgorithm {


    //二分查找（折半查找），版本1
    int BinarySearch1(int a[], int value, int n) {
        int low, high, mid;
        low = 0;
        high = n - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] == value)
                return mid;
            if (a[mid] > value)
                high = mid - 1;
            if (a[mid] < value)
                low = mid + 1;
        }
        return -1;
    }

    //二分查找，递归版本 ,版本2
    int BinarySearch2(int a[], int value, int low, int high) {
        int mid = low + (high - low) / 2;

        if (a[mid] == value) {
            return mid;
        }

        if (a[mid] > value) {
            return BinarySearch2(a, value, low, mid - 1);
        }

        if (a[mid] < value) {
            return BinarySearch2(a, value, mid + 1, high);
        }

        // 没有找到的时候就返回-1;
        return -1;

    }


    //在介绍插值查找之前，首先考虑一个新问题，为什么上述算法一定要是折半，而不是折四分之一或者折更多呢？
    //　　打个比方，在英文字典里面查“apple”，你下意识翻开字典是翻前面的书页还是后面的书页呢？
    //                  如果再让你查“zoo”，你又怎么查？很显然，这里你绝对不会是从中间开始查起，而是有一定目的的往前或往后翻。
    //            　　同样的，比如要在取值范围1 ~ 10000 之间 100 个元素从小到大均匀分布的数组中查找5，
    //                  我们自然会考虑从数组下标较小的开始查找。
    //            　　经过以上分析，折半查找这种查找方式，不是自适应的（也就是说是傻瓜式的）。二分查找中查找点计算如下：
    //            　　mid=(low+high)/2, 即mid=low+1/2*(high-low);
    //　　通过类比，我们可以将查找的点改进为如下：
    //            　　mid=low+(key-a[low])/(a[high]-a[low])*(high-low)，
    //            　　也就是将上述的比例参数1/2改进为自适应的，根据关键字在整个有序表中所处的位置，
    //                  让mid值的变化更靠近关键字key，这样也就间接地减少了比较次数。
    //            　　基本思想：基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。
    //                  当然，差值查找也属于有序查找。
    //            　　注：对于表长较大，而关键字分布又比较均匀的查找表来说，插值查找算法的平均性能比折半查找要好的多。
    //                  反之，数组中如果分布非常不均匀，那么插值查找未必是很合适的选择。
    //            　　复杂度分析：查找成功或者失败的时间复杂度均为O(log2(log2n))。

    /**
     * 插值查找(优化的二分查找算法,或者叫自适应的优化的折半查找算法: 有规律的均匀分布的时候效率较高)
     *
     * @param a
     * @param value
     * @param low
     * @param high
     * @return
     */
    int InsertionSearch(int a[], int value, int low, int high) {
        int mid = low + (value - a[low]) / (a[high] - a[low]) * (high - low);
        if (a[mid] == value) {
            return mid;
        }
        if (a[mid] > value) {
            return InsertionSearch(a, value, low, mid - 1);
        }
        if (a[mid] < value) {
            return InsertionSearch(a, value, mid + 1, high);
        }
        return -1;
    }


}
