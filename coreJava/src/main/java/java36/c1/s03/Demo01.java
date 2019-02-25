package java36.c1.s03;

public class Demo01 {
    /**
     * finally 里面的代码不一定会执行
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.exit(1);
        }finally {
            System.out.println("Print from finally");
        }
    }
}
