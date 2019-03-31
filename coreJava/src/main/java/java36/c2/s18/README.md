# 死锁？定位修复
>
- 死锁是一种特定的程序状态，在实体之间，由于循环依赖导致彼此一直处于等待之中，没有任何个体可以继续前进。死锁不仅仅是在线程之间会发生，存在资源独占的进程之间同样也可能出现死锁。通常来说，我们大多是聚焦在多线程场景中的死锁，指两个或多个线程之间，由于互相持有对方需要的锁，而永久处于阻塞的状态。
>
### 可以利用下面的示例图理解基本的死锁问题
>
```
public class DeadLockSample extends Thread {
    private String first;
    private String second;
    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    public  void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second) {
                    System.out.println(this.getName() + " obtained: " + second);
                }
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
```
>
```
"C:\Program Files\Java\jdk1.8.0_181\bin\java.exe" "-javaagent:D:\software\idea\IntelliJ IDEA Community Edition 2018.2.4\lib\idea_rt.jar=54516:D:\software\idea\IntelliJ IDEA Community Edition 2018.2.4\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_181\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\access-bridge.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_181\jre\lib\rt.jar;F:\idea\coderising\coreJava\target\classes" java36.c2.s18.DeadLockSample
Thread1 obtained first:lockA
Thread2 obtained first:lockB
```
>
- 定位死锁最常见的方式就是利用 jstack 等工具获取线程栈，然后定位互相之间的依赖关系，进而找到死锁。如果是比较明显的死锁，往往 jstack 等就能直接定位，类似 JConsole 甚至可以在图形界面进行有限的死锁检测。
```
F:\idea\coderising>jps
1556 RemoteMavenServer
420 DeadLockSample
1928
3640 Jps
6024 Launcher

F:\idea\coderising>jstack 420
2019-03-31 20:03:37
Full thread dump Java HotSpot(TM) Client VM (25.181-b13 mixed mode, sharing):

"Thread2" #10 prio=5 os_prio=0 tid=0x15045800 nid=0x570 waiting for monitor entry [0x1523f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at java36.c2.s18.DeadLockSample.run(DeadLockSample.java:21)
        - waiting to lock <0x03d16400> (a java.lang.String)
        - locked <0x03d16428> (a java.lang.String)

"Thread1" #9 prio=5 os_prio=0 tid=0x15045000 nid=0x81c waiting for monitor entry [0x152bf000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at java36.c2.s18.DeadLockSample.run(DeadLockSample.java:21)
        - waiting to lock <0x03d16428> (a java.lang.String)
        - locked <0x03d16400> (a java.lang.String)

```
>
- 最后，结合代码分析线程栈信息。上面这个输出非常明显，找到处于 BLOCKED 状态的线程，按照试图获取（waiting）的锁 ID 查找，很快就定位问题。 jstack 本身也会把类似的简单死锁抽取出来，直接打印出来。
- 在实际应用中，类死锁情况未必有如此清晰的输出，但是总体上可以理解为：
### 区分线程状态 -> 查看等待目标 -> 对比 Monitor 等持有状态
- 如果程序运行时发生了死锁，绝大多数情况下都是无法在线解决的，只能重启、修正程序本身问题。所以，代码开发阶段互相审查，或者利用工具进行预防性排查，往往也是很重要的。
>
## 如何在编程中尽量预防死锁呢？
>
- 首先，我们来总结一下前面例子中死锁的产生包含哪些基本元素。基本上死锁的发生是因为：
- 1 互斥条件，类似 Java 中 Monitor 都是独占的，要么是我用，要么是你用。
- 2 互斥条件是长期持有的，在使用结束之前，自己不会释放，也不能被其他线程抢占。
- 3 循环依赖关系，两个或者多个个体之间出现了锁的链条环。
- 所以，我们可以据此分析可能的避免死锁的思路和方法。
>
### 第一种方法
- 如果可能的话，尽量避免使用多个锁，并且只有需要时才持有锁。否则，即使是非常精通并发编程的工程师，也难免会掉进坑里，嵌套的 synchronized 或者 lock 非常容易出问题。
- 举个例子， Java NIO 的实现代码向来以锁多著称，一个原因是，其本身模型就非常复杂，某种程度上是不得不如此；另外是在设计时，考虑到既要支持阻塞模式，又要支持非阻塞模式。直接结果就是，一些基本操作如 connect，需要操作三个锁以上，在最近的一个 JDK 改进中，就发生了死锁现象。
- 将其简化为下面的伪代码，问题是暴露在 HTTP/2 客户端中，这是个非常现代的反应式风格的 API，非常推荐学习使用。
```
/// Thread HttpClient-6-SelectorManager:
readLock.lock();
writeLock.lock();
// 持有 readLock/writeLock，调用 close（）需要获得 closeLock
close();
// Thread HttpClient-6-Worker-2 持有 closeLock
implCloseSelectableChannel (); // 想获得 readLock
```
- 在 close 发生时， HttpClient-6-SelectorManager 线程持有 readLock/writeLock，试图获得 closeLock；与此同时，另一个 HttpClient-6-Worker-2 线程，持有 closeLock，试图获得 readLock，这就不可避免地进入了死锁。
- 这里比较难懂的地方在于，closeLock 的持有状态（就是标记为绿色的部分）并没有在线程栈中显示出来
>
### 第二种方法
- 如果必须使用多个锁，尽量设计好锁的获取顺序，这个说起来简单，做起来可不容易，你可以参看著名的银行家算法。
- 一般的情况，建议可以采取些简单的辅助手段，比如：
- 将对象（方法）和锁之间的关系，用图形化的方式表示分别抽取出来，以今天最初讲的死锁为例，因为是调用了同一个线程所以更加简单。
- 然后根据对象之间组合、调用的关系对比和组合，考虑可能调用时序。
- 按照可能时序合并，发现可能死锁的场景。
>
### 第三种方法
- 使用带超时的方法，为程序带来更多可控性。
- 类似 Object.wait(…) 或者 CountDownLatch.await(…)，都支持所谓的 timed_wait，我们完全可以就不假定该锁一定会获得，指定超时时间，并为无法得到锁时准备退出逻辑。
- 并发 Lock 实现，如 ReentrantLock 还支持非阻塞式的获取锁操作 tryLock()，这是一个插队行为（barging），并不在乎等待的公平性，如果执行时对象恰好没有被独占，则直接获取锁。有时，我们希望条件允许就尝试插队，不然就按照现有公平性规则等待，一般采用下面的方法：
```
if (lock.tryLock() || lock.tryLock(timeout, unit)) {
      // ...
   }
```
>
### 有时候并不是阻塞导致的死锁，只是某个线程进入了死循环，导致其他线程一直等待，这种问题如何诊断呢？