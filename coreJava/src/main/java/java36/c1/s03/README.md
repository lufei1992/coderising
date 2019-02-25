# final、finally、finalize
>
## final
>
- final修饰的class代表不可以继承扩展
- final修饰的变量不可以修改
- final修饰的方法不可以重写
>
## finally
>
- finally是Java保证重点代码一定要被执行的一种机制。
- 类似关闭JDBC连接、保证unlock锁等动作
>
## finalize
>
- finalize是java.lang.Object的一个方法。
- 保证对象在被垃圾收集前完成特定资源的回收。已经不推荐使用。
>
### final
>
- 推荐使用final关键字来明确表示代码的语义、逻辑意图。
- 将类或方法声明为final，可以明确告知别人，这些行为是不许修改的。
- 使用final修饰参数或者变量，可以清楚地避免意外赋值导致的编程错误。
- final变量产生了某种程度的不可变(immutable)的效果，可以用户保护只读数据，减少额外的同步开销。
- final可能有助于JVM将方法进行内联，可以改善编译器进行条件编译的能力。
>
### finally
>
- 有一些finally里面的代码不会被执行。
>
### finalize
>
- 无法保证finalize什么时候执行，执行的是否符合预期。使用不当会影响性能，导致程序死锁、挂起等。
>