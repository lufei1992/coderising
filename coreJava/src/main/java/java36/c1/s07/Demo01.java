package java36.c1.s07;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class Demo01 {
    public static void main(String[] args) {
        Integer integer = 1;
        int unboxing = integer ++;
    }
}

/**
 * 线程安全计数器
 */
class Counter{
    private final AtomicLong counter = new AtomicLong();
    public void increase(){
        counter.incrementAndGet();
    }
}

/**
 * 原始类型的线程安全计数器
 */
class CompactCounter{
    private volatile long counter;
    private static final AtomicLongFieldUpdater<CompactCounter> updater =
            AtomicLongFieldUpdater.newUpdater(CompactCounter.class,"counter");
    public void increase(){
        updater.incrementAndGet(this);
    }
}
