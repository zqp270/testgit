package fanxingmethod;

import org.junit.Test;

import java.util.Date;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/10/29 12:27
 */
public class TestMethod {
    @Test
    public void Test() {
        Person person = new Person();
        person.test(133);
        person.test("567");
        person.test(new Date());
    }
}
