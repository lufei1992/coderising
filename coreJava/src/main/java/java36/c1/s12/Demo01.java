package java36.c1.s12;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Java 拷贝文件方式
 */
public class Demo01 {

    public static void main(String[] args) {

    }

    /**
     * 2. 利用 java.nio 类库提供的 transferTo 或 transferFrom 方法实现。
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFileByChannel(File source,File dest) throws IOException{
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(dest).getChannel();) {
            for (long count = sourceChannel.size(); count>0 ;){
                long transfered = sourceChannel.transferTo(
                        sourceChannel.position(),
                        count,
                        targetChannel);
                sourceChannel.position(sourceChannel.position()+transfered);
                count -= transfered;
            }
        }
    }
    /**
     * 1. 利用 java.io
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFileByStream(File source,File dest) throws IOException{
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while((length = is.read(buffer))>0){
                os.write(buffer,0,length);
            }
        }
    }
}
