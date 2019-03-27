package java36.c1.s14;

/**
 * 存在问题 ？？？
 * 适用场景
 *
 * 懒汉式？？？
 * 饿汉式？？？
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2(){}
    public static Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
