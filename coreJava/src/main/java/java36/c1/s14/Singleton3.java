package java36.c1.s14;

/**
 *
 */
public class Singleton3 {
    private static volatile Singleton3 instance = null;
    private Singleton3(){}
    public static Singleton3 getInstance(){
        if (instance == null){ // 尽量避免进入同步块
            synchronized (Singleton3.class){    // 同步
                if(instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }

}
