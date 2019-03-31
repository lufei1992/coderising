# Java并发工具类
>
- 通常所说的并发包也就是 java.util.concurrent 及其子包，集中了 Java 并发的各种基础工具类，具体主要包括几个方面：
>
- 1 提供了比 synchronized 更加高级的各种同步结构，包括 CountDownLatch、CyclicBarrier、Sempahore 等，可以实现更加丰富的多线程操作，比如利用 Semaphore 作为资源控制器，限制同时进行工作的线程数量。
- 2 各种线程安全的容器，比如最常见的 ConcurrentHashMap、有序的 ConcunrrentSkipListMap，或者通过类似快照机制，实现线程安全的动态数组 CopyOnWriteArrayList 等。
- 3 各种并发队列实现，如各种 BlockedQueue 实现，比较典型的 ArrayBlockingQueue、 SynchorousQueue 或针对特定场景的 PriorityBlockingQueue 等。
- 4 强大的 Executor 框架，可以创建各种不同类型的线程池，调度任务运行等，绝大部分情况下，不再需要自己从头实现线程池和任务调度器。
>
- **CountDownLatch**，允许一个或多个线程等待某些操作完成。
>
- **CyclicBarrier**，一种辅助性的同步结构，允许多个线程等待到达某个屏障。
>
- **Semaphore**，Java 版本的信号量实现。
>
- ......