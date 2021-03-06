# 动态代理
>
- 编程语言通常有各种不同的分类角度。
- 动态类型和静态类型：简单区分就是语言类型信息是在运行时检查，还是编译期检查。
- 强类型和弱类型：不同类型变量赋值时，是否需要显式地（强制）进行类型转换。
>
- 通常认为，Java是静态的强类型语言，但是因为提供了类似反射等机制，也具备了部分动态语言类型的能力。
>
## 反射机制
- 是Java语言提供的一种基础功能，赋予程序在运行时自省（introspect）的能力。
- 通过反射可以直接操作类或者对象，比如获取某个对象的类定义，获取类声明的属性和方法，调用方法或者构造对象，甚至可以运行时修改类定义。
>
## 动态代理
- 是一种方便运行时动态构建代理、动态处理代理方法调用的机制。
- 很多场景都是利用类似机制做到的，比如用来包装RPC调用、面向切面的编程（AOP）。
>
- 实现动态代理的方式很多，比如JDK自身提供的动态代理，就是主要利用了上面提到的反射机制。
- 还有其他的实现方式，比如利用传说中更高性能的字节码操作机制，类似ASM、cjlib（基于ASM）、javassist等。
>
## 反射机制及其演进
>
- https://docs.oracle.com/javase/tutorial/reflect/index.html
>
- 操作类和对象的元数据：Class 、Field、Method、Constructor
>
- AccessibleObject.setAccessible(boolean flag) ，可以在运行时修改成员访问限制。
- setAccessible 的应用场景非常普遍：在 O/R Mapping 框架中，为 Java实体对象，运行时自动生成 setter、getter的逻辑，这是加载或者持久化数据非常必要的，框架通常可以利用反射做这个事情。
- 另一个典型成场景就是绕过API访问控制。比如，自定义的高性能NIO框架需要显式地释放DirectBuffer，使用反射绕开限制是一种常见办法。
>
## 动态代理
>
- 是一个代理机制。可以看做是对调用目标的一个包装，这样我们对目标代码的调用不是直接发生的，而是通过代理完成。
>
- 通过代理可以让调用者与实现者之间解耦。比如进行RPC调用、框架内部的寻址、序列号、反序列号等。
>
- JDK Proxy例子： MyDynamicProxy.java ，非常简单地实现了动态代理的构件和代理操作。
- 首先，实现对应的InvocationHandler；
- 然后，以接口Hello为纽带，为被调用目标构建代理对象，进而应用程序就可以使用代理对象间接调用目标的逻辑，代理为应用插入额外逻辑（这里是println）提供了便利的入口。
>
- 从API设计和实现的角度，这种实现仍然有局限性，因为它是以接口为中心的，相当于添加了一种对于被调用者没有太大意义的限制。我们实例化的是Proxy对象，而不是真正的被调用类型。
- 如果被调用者没有实现接口，而我们还是希望利用动态代理机制，那么可以考虑其他方式。
- Spring AOP支持两种模式的动态代理，JDK Proxy 或者 cglib，
- 如果选择cglib方式，对接口的依赖被克服了。
>
- cglib动态代理采取的是创建目标类的子类的方式，因为是子类化，我们可以达到近似使用被调用者本身的效果。
>
### JDK Proxy的优势
- 最小化依赖关系，减少依赖意味着简化开发和维护
- 平滑进行JDK版本升级
- 代码实现简单
>
### 类似cglib的优势
- 有时候调用目标可能不便实现额外接口，类似cglib动态代理就没有这种侵入性
- 只操作我们关心的类，而不必为其他相关类增加工作量
- 高性能
>
- 动态代理应用非常广泛，完美符合Spring AOP等切面编程。
- AOP可以看作是对OOP的一个补充，因为OOP对于跨越不同对象或类的分散、纠缠逻辑变现力不够，
- 比如在不同模块的特定阶段做一些事情，类似日志、用户鉴权、全局性异常处理、性能监控，甚至事务处理等。
>
- AOP通过（动态）代理机制可以让开发者从这些繁琐事项中抽身出来，大幅度提供了代码的抽象程度和复用度。
