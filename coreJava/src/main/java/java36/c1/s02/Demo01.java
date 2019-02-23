package java36.c1.s02;

public class Demo01 {
    public static void main(String[] args) {
        exception1();
        exception2();
    }

    /**
     * ClassNotFoundException
     */
    private static void exception1(){
        try {
            Class.forName("1");
        } catch (ClassNotFoundException e) {
            System.out.println("exception1:---ClassNotFoundException---");
            e.printStackTrace();
        }
    }

    /**
     * java.lang.NoClassDefFoundError
     */
    private static void exception2(){
        A a = new A();
        System.out.println("exception2:----");
    }
}
class A{

}
