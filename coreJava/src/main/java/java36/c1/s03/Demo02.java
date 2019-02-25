package java36.c1.s03;

import java.util.ArrayList;
import java.util.List;

public class Demo02 {
    /**
     * final 不是 immutable ！
     * final只能约束 strList 这个引用不可以被赋值，但是 strList 对象行为不被 final 影响。
     * @param args
     */
    public static void main(String[] args) {
        final List<String> strList = new ArrayList<>();
        strList.add("hello");
        strList.add("world");
    }
    /**
     * 实现 immutable 的类：
     * 1.将class自身声明为 final
     * 2.将所有成员变量定义为private和final，并且不要实现setter方法。
     * 3.构造对象时，成员变量使用深度拷贝来初始化。
     */
}
