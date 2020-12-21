/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/6 下午 2:12
 */

/**
 * 无论是静态内部类还是非静态内部类   外部类都不可以直接访问非静态的成员变量
 *而静态内部类可以访问外部类的静态成员变量，不可以访问外部类的非静态成员变量
 *
 * 非静态内部类可以直接访问外部类的非静态成员变量和静态的成员变量
 *
 *
 *
 *
 *
 */
public class Dog {
    private String name = "周速度";
    private static Integer sex = 1;

    public  void getAge() {
        //System.out.println(age); 报错外部类不能直接访问内部类的成员变量
        Person person = new Person();
        System.out.println(person.age);
    }
    private class Person {
        private String age = "sds";
        /**
         * 原因：当内部类为非静态的时候 就不能随着类的加载而加载 ，要随着new的加载而加载
         * 如果直接定义成static 那么类随着new的加载而加载， static随着修饰的方法和属性 就在一开始就会被
         * 加载， 那么就会非常的矛盾
         */
        //private static String name = "张胜男"; 报错
        //=============================================================================
        //内部类可以直接访问外部类的变量，无论是静态还是非静态
        private void getName() {
            System.out.println(Dog.this.name);
            System.out.println(Dog.sex);
        }
    }
}
