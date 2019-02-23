# 01-对Java平台的理解，Java是解释执行的？
>
## Java的两个特性
>
- 1 编写一次，到处运行。（Write once,run anywhere）
>
- 2 垃圾收集。（GC, Garbage Collection）
>
## Java是解释执行的？
>
- 不太准确。
>
- Java源代码，首先通过Javac编译成为字节码（bytecode）,
- 在运行时，通过Java虚拟机（JVM）内嵌的解释器将字节码转换成最终的机器码。
>
- 但是常见的JVM，都提供了JIT（Just-In-Time）编译器，即动态编译器，能够在运行时将热点代码编译成机器码。
- 这种情况下部分热点代码就属于编译执行，而不是解释执行了。
>
## JVM参数
>
- 混合模式： "-Xmixed"
- 解释模式： "-Xint"
- 编译模式： "-Xcomp"
