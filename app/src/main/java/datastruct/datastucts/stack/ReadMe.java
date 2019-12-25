package datastruct.datastucts.stack;

/**
 * Created by zhangg on 2018/6/2.
 */

public class ReadMe {

    //    ArrayList 和 LinkedList 的区别
    //    ArrayList 是 List 接口的一种实现，它是使用数组来实现的。
    //    LinkedList 是 List 接口的一种实现，它是使用链表来实现的。
    //    ArrayList 遍历和查找元素比较快。LinkedList 遍历和查找元素比较慢。
    //    ArrayList 添加、删除元素比较慢。LinkedList 添加、删除元素比较快。

    //    有关于 map.entrySet() 和 keySet():
    //            1、如果遍历 hashMap() 时 entrySet() 方法是将 key 和 value 全部取出来,所以性能开销是可以预计的,
    //                而 keySet() 方法进行遍历的时候是根据取出的 key 值去查询对应的 value 值,
    //                所以如果 key 值是比较简单的结构(如 1,2,3...)的话性能消耗上是比 entrySet() 方法低,
    //                但随着 key 值得复杂度提高 entrySet() 的优势就会显露出来。
    //            2、综合比较在只遍历 key 的时候使用 keySet(), 在只遍历 value 的是使用 values() 方法,
    //                在遍历 key-value 的时候使用 entrySet() 是比较合理的选择。
    //            3、如果遍历 TreeMap 的时候, 不同于 HashMap 在遍历 ThreeMap 的 key-value 时候务必使用 entrySet()
    //                它要远远高于其他两个的性能, 同样只遍历 key 的时候使用 keySet(),
    //                在只遍历 value 的是使用 values() 方法对于 TreeMap 也同样适用。


}
