package swapStream;

import org.junit.Test;

import java.io.*;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/10 下午 3:41
 */
public class TestSwapStream {
    /**
     * 转换流的作用就是把字节流转变成为字符流，其作用 就是在再次存储的时候改变其编码方式
     */
    @Test
    public void test() throws IOException {
        File file = new File("f:\\xxx.txt");
        File file1 = new File("c:\\Users\\zqp\\Desktop","xxx.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file1),"gbk");
        char[] chars= new char[10];
        int len  = -1;
        while((len = isr.read(chars)) != -1) {
            System.out.println(chars);
            osw.write(chars, 0, len);
        }
        //为什么不显示 因为没有进行缓冲
         osw.flush();
    }
}
