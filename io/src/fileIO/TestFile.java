package fileIO;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/10 上午 11:09
 */
public class TestFile {
    @Test
    //fileReader
    public void testFileReader() throws IOException {
        File file = new File("d:\\javaJC\\java\\java.iml");
        FileReader fileReader = new FileReader(file);
        int data = 0;
        //这样写有问题 在判断的时候读出来的流并没有进行打印 而是直接打印的下一个
        while((data = fileReader.read()) != -1) {
            System.out.print((char)data);
        }
        while(fileReader.read() != -1) {
            System.out.println((char)fileReader.read());
        }
    }

    @Test
    //fileReader
    public void testFileReader1() throws IOException {
        File file = new File("d:\\javaJC\\java\\java.iml");
        FileReader fileReader = new FileReader(file);
        int data = 0;
        //这样是一次读很多 然后把读出来的数据都放在数组中，而返回值则是一次读了多少
        char[] chars = new char[100];
        while((data = fileReader.read(chars)) != -1) {
            System.out.println(data);
            for (int  i = 0; i < data; i++) {
                System.out.print(chars[i]);
            }
        }
        while(fileReader.read() != -1) {
            System.out.println((char)fileReader.read());
        }
    }

    //复制文件
    @Test
    public void copyFile()  throws Exception {
        FileReader fileReader = new FileReader("io.iml");
        File file = new File("C:\\Users\\zqp\\Desktop", "io.iml");
        FileWriter fileWriter = new FileWriter(file);
        //提高效率 采用读写的方式
        char[] chars = new char[50];
        int len = - 1;
        /**
         * fileReader.read(chars) :这句话相当于把读出的内容放入了 chars 这个数组中
         */
        while ((len = fileReader.read(chars)) != -1) {
            fileWriter.write(chars, 0, len);
        }
        fileWriter.flush();
        fileReader.close();
        fileWriter.close();
    }

    @Test
    //copy 采用字节流
    public void copyStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zqp\\Desktop\\反射\\Java SE 第六十八讲 Java动态代理总结.avi");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\zqp\\Desktop\\xx.avi");
        byte[] bytes = new byte[1024];
        int len = - 1;
        while((len = fileInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes,0,len);
        }
        fileInputStream.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
