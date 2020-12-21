package fileProtices;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/10 上午 10:39
 */
public class FileTest {
    /**
     * file中的基本方法
     * 绝对路径
     * 相对路径
     * 名字
     * 长度
     * 最后修改时间
     * 下一级目录
     *
     */
    @Test
    public void testFileMethod() {
        File file = new File("file.iml");
        //绝对路径
        System.out.println(file.getAbsolutePath());
        //相对路径
        System.out.println(file.getPath());
        //文件名字
        System.out.println(file.getName());
        //长度
        System.out.println(file.length());
        //最后修改时间
        System.out.println(file.lastModified());
        System.out.println("==============================");
    }
    @Test
    public void TestList() {
        File file = new File("d:\\javaJC","java");
        //得到该文件的下的目录，并以字符串的方式进行返回
        String[] list = file.list();
        System.out.println(Arrays.toString(list));
        //得到该文件的所有目录并以file类型返回 发如果没有则返回为null
        File[] files = file.listFiles();
        System.out.println(Arrays.toString(files));
    }
    @Test
    public void TestIsMethod() {
        File file = new File("file.iml");
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());
    }
    @Test
    public void TestCreateMethod() throws IOException {
        File file = new File("d:\\testFile");
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            System.out.println(mkdirs?"创建成功":"创建失败");
        }
    }

}
