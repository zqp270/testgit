package classFnaxing;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/10/29 12:13
 */

/**
 * 作用从在类上的泛型
 * 泛型：相当于参数，对于不确定的参数 可以用泛型进行代替，
 */
public class Person<E> {
    private Integer id;
    private String name;
    private E temp;

    public Person() {
    }

    public Person(Integer id, String name, E temp) {
        this.id = id;
        this.name = name;
        this.temp = temp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public E getTemp() {
        return temp;
    }

    public void setTemp(E temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", temp=" + temp +
                '}';
    }
}
