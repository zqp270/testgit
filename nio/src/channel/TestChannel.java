package channel;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/11 下午 3:56
 */

/**
 *
 * 创建通道的三种方式
 * 1.使用io中普通的类进行创建 FileOutputStream/FileInputStream
 * 2.使用Files中的静态方法
 *
 *
 */
public class TestChannel {

    //采用非直接缓冲区
    @Test
    public void testChannel() throws IOException {
        //创建普通io流
        FileInputStream fileInputStream = new FileInputStream("d:\\1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\2.txt");
        //创建通道
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //开始读写copy
        /**
         * 这个数据为什么要从通道中取
         * 在普通的io中数据通过水流的方式进行 传播，
         * 但是在nio中数据并不是以水流的方式进行传播，而是通过一个中间件，Buffer 缓冲区，但是数据仍然存在同道中
         * 所以我们首先要从管道中得到数据， 然后在把数据放在缓冲区中，让缓冲区进行运行
         *
         */
        while(inChannel.read(buffer) != -1) {
            //读转写
            buffer.flip();
            //为什么不需要写开始下表，和结束下表
            outChannel.write(buffer);
            //清空
            buffer.clear();
        }
        fileInputStream.close();
        fileOutputStream.close();
        inChannel.close();
        outChannel.close();
    }

    /**
     * 1.创建通道
     * 2.创建缓冲区
     *
     * @throws IOException
     */
    //直接缓冲区练习
    @Test
    public void test1() throws IOException {
        /**
         * 1.FileChannel:是一个本地连接通道
         * 2.提供了一个创建通道的方法open("第一个是本地地址"，第二个是对该通道的解释说明，是一个枚举类)
         */
        FileChannel inChannel = FileChannel.open(Paths.get("d:\\1.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("d:\\2.txt"), StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ);
        //创建一个缓冲区
        /**
         * 第一个参数：对该通道的一个说明
         * 第二个参数：是起始位置
         * 第三个参数：是limit 大小
         */
        //把该缓存区和物理内存中的某一快进行绑定，改变了该缓冲区就相当于直接改变该内存中的东西
        MappedByteBuffer map1 = inChannel.map(FileChannel.MapMode.READ_ONLY,0,inChannel.size());
        MappedByteBuffer map = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        System.out.println("map:" + map.capacity() + "   map1:" + map1.capacity());// 120 + 120
        System.out.println("map:" + map.limit() + "   map1:" + map1.limit());
        //开始读写
        byte[] bytes = new byte[map1.limit()];
        System.out.println(map1.limit());
        map1.get(bytes,0,map1.limit());
        System.out.println(bytes.length);
        map.put(bytes,0,bytes.length);
    }
}
