package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/8 下午 10:40
 */
public class Invacoation implements InvocationHandler {
    private Object obj;

    public Invacoation(Object obj) {
        this.obj = obj;
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy == obj);
        Object invoke = method.invoke(obj, args);
        System.out.println("一共给我了" + invoke);
        return (Integer)invoke - 300;
    }
}
