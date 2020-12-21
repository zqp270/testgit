package fxcommon;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/10/29 12:29
 */

import classFnaxing.Father;
import classFnaxing.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型通配符
 */
public class TestCommon {
    @Test
    public void test1() {
        List<?> list = null;
        List<Person> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        //这两种都不会报错 因为？相同于一个通配符  谁都不会出错
        list = list1;
        list = list2;
        //向list中放值 是不允许的 原因是?相当于是通配符 ,并不知道这个值是什么
        // 编译不通过  list.add("dd");

        list2.add("112");
        //  for (String e: list) 编译不通过
        //在取值的时候只能是 Object类型
        for (Object e: list) {
            System.out.println(e);
        }

    }

    @Test
    public void testGeneric() {
        //这种就相当于 某一个类是 person的子类 或者是person 要求的权限是 （-∞ Person）
        List<? extends Person> list = null;
        //这种是?是person的父类  要求权限是 （person +∞）
        List<? super Person> list1 = null;
        List<Person> list2 = null;
        List<Father> list3 = null;
        List<Object> list4 = null;
        list = list2; //当该类是person的时候 赋给其List<? extend Person 编译通过>
        list = list3; //通过  因为他是person的子类
        // list = list4; 编译不通过 因为她是Person的父类
        //=================================================================================
        list1 = list2; //通过
        // list3 = list1; 编译不通过 原因是：list3中的泛型是person的子类 list1中的泛型是person中的父类
            //父类赋值给zi类 所以报错
        list1 = list4; //通过
        /**
         * 编译不通过 原因是：list3中的泛型是person的子类 list1中的泛型是person中的父类
         *             //父类赋值给zi类 所以报错
         */
        //list4 = list1;
    }
}
