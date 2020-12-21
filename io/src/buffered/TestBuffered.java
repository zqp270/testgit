package buffered;

import org.junit.Test;

import java.io.*;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/10 下午 3:13
 */
public class TestBuffered {
    @Test
    public void test() throws IOException {
        File file = new File("C:\\Users\\zqp\\Desktop\\反射\\Java SE 第六十八讲 Java动态代理总结.avi");
        File file1 = new File("C:\\Users\\zqp\\Desktop\\反射","yyy.avi");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
        byte[] bytes = new byte[1024];
        int len = -1;
        while((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        bos.close();
        bis.close();
    }
}
