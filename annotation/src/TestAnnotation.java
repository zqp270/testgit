
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/9 上午 9:51
 */
public class TestAnnotation {
    public static void main(String[] args) throws NoSuchMethodException {
        Class clazz = Person.class;
        Method get = clazz.getMethod("get", new Class[]{});
        //这个地方不能是 父类 只能是他自己的类型
        MyAnnotation annotation = get.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.hello());
    }
}
