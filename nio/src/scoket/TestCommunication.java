package scoket;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/12 下午 2:31
 */

/**
 *
 * ------- -----------------------------  ------  ----------------------------------
 * |客户端|  连接服务器的客户端通道          |选择器|   监听请求的服务端通道                服务端
 * ------  ------------------------------ ------  ----------------------------------
 * 当客户端发送请求的时候， 首先会进行到选择器， 判断当前该客户端的状态，如果客户端准备好，那么久让客户端进去
 *  就和操作系统中的并发一样， 因为不引入并发，当一个进程 产生i/o的时候cpu就会空闲，cpu得不到很好的利用
 *  现在选择器就处于中间状态 很好的解决了这个问题
 */
public class TestCommunication {

    @Test
    public void client() throws IOException {
        //1.创建和服务器进行连接的通道
        SocketChannel clientChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 30000));
        //2.因为阻塞式 不知道资源的情况 可能会造成一定的cpu的浪费，这个时候要把阻塞式改变成非阻塞式
        //  默认为阻塞式
        clientChannel.configureBlocking(false);
        //3.向客户端发送信息
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        while(scanner.hasNext()) {
            System.out.println("连接成功");
            String str = scanner.next();
            byteBuffer.put(str.getBytes());
            clientChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        clientChannel.close();
    }

    @Test
    public void server() throws IOException {
        //获取连接通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        //作为一个服务程序， 应该监听端口 ，绑定端口
        serverChannel.bind(new InetSocketAddress(30000));
        //变为非阻塞式
        serverChannel.configureBlocking(false);
        //创建一个选择器，用来对访问服务程序的 客户端进行判断状态
        Selector selector = Selector.open();
        //服务器端通道绑定选择器
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        /**
         * 动态监测选择器中的每一个客户端的状态， 如果准备好 就让进， 负责就不让进
         * 就和操作系统的互斥中的 testSet（）算法一样 动态的监测临界区的情况 算法一样
         * 这个判断和信号量非常类似
         * 信号量也是 当一个资源进去 那么当前互斥信号量的0 接下来进去的 就是负数那么
         * 这个准备好的 也是标志位变为大于0的一个数
         */
        while(selector.select() > 0) {
            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();//得到当前准备好的 客户端的选择字；
            while (selectionKeys.hasNext()) {
                SelectionKey selectionKey = selectionKeys.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel accept = serverChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    byte[] bytes = new byte[buffer.limit()];
                    System.out.println(new java.lang.String(bytes));
                }
                selectionKeys.remove();
            }

        }
    }
}
