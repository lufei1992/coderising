package java36.c1.s05;

public class Demo01 {
    public static void main(String[] args) {
        String str = "hello,world!";
        char[] chars = new char[4];
        byte[] bytes = new byte[4];
        chars[0] = 'h';
        chars[1] = 'l';
        chars[2] = 'l';
        chars[3] = 'l';
        bytes[0] = 'h';
        bytes[1] = 'l';
        bytes[2] = 'l';
        bytes[3] = 'l';
        System.out.println(new String(chars));
        System.out.println(new String(bytes));

    }
}
