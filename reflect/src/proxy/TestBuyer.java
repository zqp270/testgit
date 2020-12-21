package proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/8 下午 10:37
 */

/**
 * 关于 proxy 和invocationHandler的理解
 * proxy 是为我们创建一个代理类
 * 第一个参数  类加载器 指明应该由哪一个类去代理，
 * 第二个类   接口： 指明该代理类应该实现什么接口
 * 第三个参数： invocationHandler：相当于拦截器，而且去执行代理代理方法
 *             当我调用每一个方法的时候 （这里的调用是指代理类的调用）
 *             是这个拦截器就相当于一个监听器（观察者设计模式） 而我的代理类相当于被观察的对象， 当我使用代理类的方法的时候 就会被拦截
 *             然后做相应的事情
 *
 *
 *
 *
 *
 */
public class TestBuyer {
    @Test
    public void buyerTest() {

        BuyerRoom buyerRoom = new Buyer();
        Invacoation invacoation = new Invacoation(buyerRoom);
        BuyerRoom o = (BuyerRoom) Proxy.newProxyInstance(TestBuyer.class.getClassLoader(), buyerRoom.getClass().getInterfaces(), invacoation);
        Integer integer = o.buyRoom();
        System.out.println("========================");
        buyerRoom.buyRoom();
        System.out.println("========================");
        System.out.println(integer);
    }
}
