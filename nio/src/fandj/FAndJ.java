package fandj;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/12 上午 9:23
 */

import org.junit.Test;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 分散 聚集写出
 *
 * 就是有好多的缓冲区，把管道中的数据依次写出到缓冲区 这个过程就是分散读取
 * 然后把缓冲区的东西在依次写入到管道中 这个过程就是 聚集写入
 */
public class FAndJ {
    @Test
    public void test1() throws IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("C:\\Users\\zqp\\Desktop\\哔哩哔哩动画\\1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.png"),StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(100);
        ByteBuffer buffer1 = ByteBuffer.allocate(300);
        ByteBuffer[] buffers = {buffer,buffer1};
        while(inChannel.read(buffers) != -1) {
            for (int i = 0; i < buffers.length; i++) {
                buffers[i].flip();
            }
            outChannel.write(buffers);
            for (int i = 0; i < buffers.length; i++) {
                buffers[i].clear();
            }
        }
    }

}
