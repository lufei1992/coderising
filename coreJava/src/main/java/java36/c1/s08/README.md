# Vector、ArrayList、LinkedList
>
- 这三者都是实现集合框架中的List，有序集合。都提供按照位置进行定位、添加或者删除的操作，都提供迭代器以遍历其内容。
>
- Vector是Java早期提供的线程安全的动态数组，如果不需要线程安全，并不建议选择，毕竟同步是有额外开销的。
- ArrayList是应用更加广泛的动态数组实现，它本身不是线程安全的。与Vector近似，ArrayList也是可以根据需要调整容量，Vector在扩容时会提高1倍，而ArrayList则是增加50%。
- LinkedList是Java提供的**双向链表**，不需要调整容量，也不是线程安全的。
>
## 不同容器类型的适合场景
- Vector和ArrayList作为动态数组，其内部元素以数组形式顺序存储的，非常适合随机访问的场合。除了尾部插入和删除元素，往往性能会相对较差。
- LinkedList进行节点插入、删除却要高效得多，但是随机访问性能则要比动态数组慢。
>
## 数据结构和算法
>
### 内部排序
- 归并排序、交换排序（冒泡、快排）、选择排序、插入排序等
### 外部排序
- 利用内存和外部存储处理超大数据集的过程和思路
>
- 哪些是排序不稳定的？（快排、堆排）稳定意味着什么？
- 对不同数据集，各种排序的最好或最差情况？从某个角度优化（空间占用，假设业务场景需要最小辅助空间）？
>
## Java集合框架
>
- Collection接口是所有集合的根，然后扩展开提供了三大类集合，分别是
>
- List，有序集合，提供了方便的访问、插入、删除等操作
- Set，不允许重复元素的，不存在两个对象equals返回true。保证元素唯一性的场合使用。
- Queue/Deque，java提供的标准队列结构的实现，支持类似FIFO、LIFO等特定行为
>
- 每种集合的通用逻辑，都被抽象到相应的抽象类之中，比如AbstractList就集中了各种List操作的通用部分。这些集合不是完全孤立的，LinkedList本身，既是List，也是Deque
- 源码中，TreeSet代码里实际默认是利用TreeMap实现的，Java类库创建了一个Dummy对象“PRESENT”作为value，然后所有插入的元素其实是以键的形式放入TreeMap里面；同理，HashSet其实也是以HashMap为基础实现的，原来他们只是Map类的马甲！
>
### 具体集合实现的基本特征和典型使用场景
>
- TreeSet
- HashSet
- LinkedHashSet
- 在遍历元素时，HashSet性能受自身容量影响
>
- 线程安全的集合容器：java.util.concurrent
>
- Collections工具类中，提供了一系列的synchronized方法
```
    static <T> List<T> synchronizedList(List<T> list)
    List list = Collections.synchronizedList(new ArrayList())
```
- 它的实现，基本就是将每个基本方法，通过synchronized添加基本的同步支持，非常简单粗暴。
>
## Java提供的默认排序算法，排序方式以及设计思路
>
- 区分是Arrays.sort()还是Collections.sort()（底层是调用Arrays.sort()）；
- 什么数据类型；多大的数据集（太小的数据集，复杂排序是没必要的，Java会直接进行二分插入排序）
>
- 对于原始数据类型，Java 8使用的是双轴快速排序（Dual-Pivot QuickSort），早期版本是相对传统的快速排序。
- 对于对象数据类型，使用TimSort，思想上也是一种归并和二分插入排序（binary Sort）结合的优化排序算法。
- Java 8 引入了并行排序算法。