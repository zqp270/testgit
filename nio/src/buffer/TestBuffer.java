package buffer;


import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/11 下午 2:25
 */

/**
 *一. nio是jdk4提出来的一种比io更加强大的一种存储结构
 *    nio = 通道 + 缓冲区
 * 二.缓冲区：
 *      缓冲区是用来存储数据的，他底层采用数组结构，不同的数据有不同的缓冲区，但是没有boolean缓冲区
 *      缓冲区的类型：
 *          IntBuffer
 *          ByteBuffer
 *          CharBuffer
 *          ShortBuffer
 *          LongBuffer
 *          DoubleBuffer
 *          FloatBuffer
 *      缓冲区主要是通过allocate来进行被管理的
 *  ===================================================
 *  二.Buffer类的几个重要属性
 *  position：当前读数据的指针位置
 *  limit：可以操作的缓冲区位置（主要在读数据的时候被用）
 *  capacity：缓冲区容量大小
 *  mark：可以用来标记一次position的位置，然后让position回到该位置
 */
public class TestBuffer {
    @Test
    public void testIO() {
        //1.创建缓冲区  此时创建了一个大小为1024的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("=============allocate===================");
        System.out.println(buffer.position() + " 指针位置");
        System.out.println(buffer.limit() + "可操作性的大小");
        System.out.println(buffer.capacity() + "容量大小");
        //2.向缓冲区内存数据
        String str = "abcdef";
        buffer.put(str.getBytes());
        System.out.println("=============put===================");
        System.out.println(buffer.position() + " 指针位置");
        System.out.println(buffer.limit() + "可操作性的大小");
        System.out.println(buffer.capacity() + "容量大小");
        //3.切换到读模式
        System.out.println("==============flip===================");
        buffer.flip();
        System.out.println(buffer.position() + " 指针位置");
        System.out.println(buffer.limit() + "可操作性的大小");
        System.out.println(buffer.capacity() + "容量大小");
        //4.开始用put（）进行读数据
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes); //==  buffer.get(a, 0, a.length)
        System.out.println("=============get===================");
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(buffer.position() + " 指针位置");
        System.out.println(buffer.limit() + "可操作性的大小");
        System.out.println(buffer.capacity() + "容量大小");

        //5.还可以重复读  把当前position置位1
        buffer.rewind();
        buffer.get(bytes);
        System.out.println("=============rewind===================");
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(buffer.position() + " 指针位置");
        System.out.println(buffer.limit() + "可操作性的大小");
        System.out.println(buffer.capacity() + "容量大小");
    }

    @Test
    public void test2() {
        //记录position指针就，从记录的位置开始读
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        String str = "adsad";
        allocate.put(str.getBytes());
        allocate.flip();
        byte[] bytes = new byte[allocate.limit()];
        allocate.get(bytes);
        System.out.println(new String(bytes, 0 , bytes.length));
        allocate.mark();
        allocate.get(bytes, 2 , 2);
        System.out.println(new String(bytes, 2 , bytes.length));
        allocate.reset();
    }

    //======================================================================================
    /***
     * 直接缓冲区和非直接缓冲区
     * 使用 allocate创建的缓冲区属于非直接缓冲区 他是存在java虚拟机的内存中 而不是存在物理内存中
     * allocateDirect()创建的缓冲区是直接缓冲区，他直接在计算机的物理内存上开辟空间，能提高io效率
     * 但是会存在一定的风险
     */

    @Test
    public void testDirect() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());//true 判断缓冲区的类型
    }


}
