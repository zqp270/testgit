package createList;

import jdk.nashorn.internal.runtime.UnwarrantedOptimismException;

import java.util.ArrayList;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/6 下午 4:52
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.add(123);
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        java.util.List list1 = new ArrayList();
        list1.add(1);
        System.out.println(list.size());
    }
}
