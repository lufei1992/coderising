package java36.c1.s03;

/**
 * 使用 java.lang.ref.Cleaner 替换原有的 finalize 实现
 */
public class CleaningExample implements AutoCloseable {
    @Override
    public void close() throws Exception {

    }
}
