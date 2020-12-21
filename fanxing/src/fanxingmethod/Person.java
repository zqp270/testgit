package fanxingmethod;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/10/29 12:25
 */

import org.junit.Test;

/**
 * 泛型方法 和父类有没有泛型没有关系， 有关系的就是子类是否有泛型
 * @param <E>
 */
public class Person<E> {


    /**
     * 泛型方法可以声明为静态的
     * @param e
     * @param <T>
     * @return
     */
    public <T> T test(T e) {
        System.out.println(e);
        return e;
    }
}
