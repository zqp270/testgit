package classFnaxing;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/10/29 12:17
 */
public class TestClassFx {
    /**
     * 测试泛型
     * 在创建对象的时候 需要指定泛型 也可以不指定泛型  和List的用法相同
     */
    @Test
    public void test1() {
        Person<Date> person = new Person();
        person.setId(10);
        person.setName("123");
        person.setTemp(new Date());
        System.out.println(person);
    }


}
