# 02 Exception 和 Error
>
## Exception 和 Error 区别？
>
- 都继承了Throwable类。
- 在Java中，只有Throwable类型的实例才可以被抛出（throw）或者捕获（catch），它是异常处理机制的基本组成类型。
>
- Exception 是程序正常运行中，可以预料的意外情况，可能并且应该被捕获，进行相应处理。
- Error 是指在正常情况下，不太可能出现的情况，绝大部分的 Error 都会导致程序（比如JVM自身）处于非正常的、不可恢复状态。
>
## 可检查异常与不检查异常
>
- 可检查异常在源代码里必须显式地进行捕获处理，这是编译器检查的一部分。
- 不检查异常就是运行时异常，类似NullPointerException、ArrayIndexOutOfBoundsException之类，通常是可以编码避免的逻辑错误，根据具体需要来判断是否需要捕获。
>
## Throwable、Exception、Error 的设计和分类
>
-
>
###  NoClassDefFoundError 和 ClassNotFoundException 的区别？
>
-
>
## Java语言中操作 Throwable 的元素和实践
>
-
>
## 性能：Java的异常处理机制，有两个相对昂贵的地方
>
- try-catch 代码段会产生额外的性能开销，或者换个角度说，它往往会影响JVM对代码进行优化。
- 建议仅捕获有必要的代码段，尽量不要一个大的try包住整段的代码；
- 利用异常控制代码流程，也不是一个好主意，远比我们通常意义上的条件语句要低效。
>
- Java 每实例化一个Exception，都会对当时的栈进行快照，这是一个相对比较重的操作。
- 当服务器出现反应变慢、吞吐量下降的时候，检查发生最频繁的Excpetion也是一种思路。
>
