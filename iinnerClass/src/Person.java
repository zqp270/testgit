/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/6 下午 2:04
 */

/**
 * 在静态内部类和外部类访问的时候，主需要注意加载顺序问题，
 * 访问修饰符这个问题可以忽略不计，因为在一个类中 所有的属性都相当于共享
 *
 */
public class Person {
    private String name;

    public void getName() {
        //外部类可以访问内部的私有静态成员
        System.out.println(Dog.name);
    }

    public void getName1() {
        /**
         * 原因是 ：当我的外部类创建对象的时候，内部类不一定创建对象，所以不能访问
         * 如果想要访问，就需要在外部类中创建内部的对象
         */
        //System.out.println(Dog.age); 报错
        //当创建完对象之后 外部类可以访问内部类的私有非静态成员变量
        Dog dog = new Dog();
        String age = dog.age;
        System.out.println(age);
    }
    private static class Dog {
        private static String name = "狗王";
        private String age = "hehe";
    }
}
