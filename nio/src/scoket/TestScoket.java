package scoket;

import org.junit.Test;

import javax.xml.ws.Service;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/12 下午 12:04
 */

/**
 * 1.网络通信的感想
 *   非阻塞式：是指当客户端的数据没有到达之前，该线程不需要要阻塞自己，这是因为增加了一个选择器 通过选择器来监听客户端的情况
 *
 * 选择器：主要式用来监听客户端的情况
 *
 *
 * 2.关于网络通信之间的过程
 *      1.使用TCP建立三次连接
 *      然后双方分别建立网络管道，注意 这个网络管道默认式阻塞式的，因此我们要把他变成非阻塞式的
 * 关于网络管道的建立
 *      网络管道的建立和普通管道的建立其实本质上是一致的 本地管道只需要写上自己的资源的路径即可
 *      而网络管道的建立，他们是用来通信的。他们使用的通信方式就是套接字， 套接字 = ip + 端口 （见）
 *   1.客户端
 *      建立连接 SocketChannel.opn(new InetSocketAddress("port"));
 *      此时的通断是堵塞的
 *      要让他变成非阻塞的
 *  2.服务端
 *      建立通道: 说明该通道连接的端口 + 变成非阻塞的
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class TestScoket {
    @Test
    public void client() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.READ);
        SocketChannel open = SocketChannel.open(new InetSocketAddress("127.0.0.1",8989));
        open.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(fileChannel.read(buffer) != -1) {
            buffer.flip();
            open.write(buffer);
            buffer.clear();
        }
        open.close();
    }

    @Test
    public void server() throws IOException {
        //打开通道
        ServerSocketChannel open = ServerSocketChannel.open();
        //1.绑定连接
        open.bind(new InetSocketAddress(8989));
        //设置为非阻塞模式
        open.configureBlocking(false);
        FileChannel fileChannel = FileChannel.open(Paths.get("3.png"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        Selector selector = Selector.open();
        //为这个通道进行注册，这个通道只进行接收服务端的请求
        open.register(selector, SelectionKey.OP_ACCEPT);
        //条件代表当前选择器中已经准备好的客户端
        while(selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    //这个代表客户端的通道
                    SocketChannel accept = open.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    //获取“读就绪的选择器”
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while(channel.read(buffer) != -1) {
                        buffer.flip();
                        fileChannel.write(buffer);
                        buffer.clear();
                    }
                }
                iterator.remove();
            }
        }
    }
}
