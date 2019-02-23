package java36.c1.s02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Exception 实践选择
 */
public class Demo02 {
    public static void main(String[] args) {
        readPreferences1(null);
        readPreferences2(null);
    }
    /**
     * 1. 尽量不要捕获类似Exception这样的通用异常，而是应该捕获特定异常。
     *      Thread.sleep() 抛出 InterruptedException
     * 2. 不要生吞（swallow）异常
     */
    public static void demo1(){
        try {
            // 业务代码
            // ...
            Thread.sleep(1000);
        } catch (Exception e) {
            // ignore it
        }
    }

    /**
     * 产品代码中，通常都不允许这样处理。
     * 在稍微复杂一点的生产系统中，标准出错不是个合适的输出选项，因为你很难判断到底输出到哪里去了。
     * 尤其是对于分布式系统，如果发生异常，但是无法找到堆栈轨迹（stacktrace），这纯属为诊断设置障碍。
     * 所以，最好使用产品日志，详细地输出到日志系统里。
     */
    public static void demo2(){
        try {
            // 业务代码
            // ...

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  Throw early, catch late 原则
     *  如果fileName 是null，那么程序就会抛出 NullPointerException，但是由于没有第一时间暴露
     *  出问题，堆栈信息可能非常令人费解，往往需要相对复杂的定位。
     *  实际产品代码中，可能是各种情况，比如获取配置失败之类的。在发现问题的时候，
     *  第一时间抛出去，能够更加清晰地反映问题。
     * @param fileName fileName
     */
    private static void readPreferences1(String fileName){
        try {
            InputStream in = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改一下，让问题“throw early”
     * @param fileName
     */
    private static void readPreferences2(String fileName){
        Objects.requireNonNull(fileName);
        try {
            InputStream in = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * “catch late ”
     * 捕获异常后，需要怎么处理？
     * 最差的方式：生吞异常
     * 如果实在不知道如何处理，可以选择保留原有异常的cause信息，直接再抛出或者构建新的异常抛出去。
     * 在更高层面，因为有了清晰的业务逻辑，往往会更清楚合适的处理方式是什么。
     */

    /**
     *
     */
}
