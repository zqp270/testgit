/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/9 上午 9:31
 */
public class Person {
    @MyAnnotation(hello = "haha")
    public boolean get(){
        System.out.println("hello world");
        return true;
    }
}
