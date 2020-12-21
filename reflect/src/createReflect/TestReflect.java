package createReflect;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/8 下午 9:21
 */
public class TestReflect {
    @Test
    public void testCopy() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person("张三", 10);
        Class clazz = Class.forName("createReflect.CopyObject");
        Object copyObject = clazz.newInstance();
        //报错 原因是 因为 我的copy的参数类型只有Object的 而没有Person的
        //Method copy = clazz.getMethod("copy", new Class[]{Person.class});
        Method copy = clazz.getMethod("copy", new Class[]{Object.class});
        Object invoke = copy.invoke(copyObject, new Object[]{person});
        System.out.println(invoke);
    }
}
