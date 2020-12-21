package createList;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/6 下午 4:34
 */
public class List<T> {
    //创建一个初始值得数组
    private Object[] initList;
    private Integer size = 0;
    private Integer initCapacity;

    public  List() {
        this(10);
    }
    public List(Integer initCapacity) {
        if (initCapacity < 0) {
            throw new RuntimeException("不能为负数");
        }
        this.initList = new Object[initCapacity];
        this.initCapacity = initCapacity;
    }

    /**
     * 添加操作
     * @param e
     */
    public void add(T e) {
        compare(this.size() + 1);
        this.initList[size++] = e;
    }

    public void compare(Integer size) {
        if (size - initList.length <= 0) {
            addLength();
        }
    }

    public void addLength() {
        Object[] temp = this.initList;
        initCapacity = initCapacity + 10;
        initList = new Object[initCapacity];
        for (int i = 0; i < temp.length; i++) {
            this.initList[i] = temp[i];
        }
    }
    /**
     * 获得当前数组中的一共用的大小
     * @return
     */
    public Integer size() {
        return this.size;
    }
    public T get(Integer index) {
        return (T)this.initList[index];
    }
}
