package createReflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/8 下午 9:02
 */

/**
 * 可变长度： 是指该参数是可变的 可以又，也可以没有
 * 如果是有： 可以是一个组，可以是几个具体的类型
 *              具体类型：是几个都没有关系
 *              如果是数组：则这个类型只能是一个
 *
 * 在反射中如果想要获取某一个方法 ： 必须知道这个方法的名字 以及这个方法中的参数类型  所谓的参数类型  就是防止 以为重载而不知道调用的方法是什么
 *  使用反射在调用某一个方法的时候，必须知道该方法所在类的实体，以及要传的值
 * @author zqp
 */
public class CopyObject {

    public Object copy(Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = obj.getClass();
        //获得constructor
        Constructor constructor = clazz.getConstructor(new Class[]{});
        //根据Constructor创建对象
        Object newObj = constructor.newInstance();
        //获得属性
        Field[] fields = clazz.getDeclaredFields();
        //获得所有的field属性
        //根据属性 获得所有的get 和set方法
        for(Field field : fields) {
            //获得该属性的名字
            String parameterName = field.getName();
            //获得被copy的get方法
            String getMethod = "get" + parameterName.substring(0,1).toUpperCase() + parameterName.substring(1);
            //获得新对象的Set方法
            String setMethod = "set" + parameterName.substring(0,1).toUpperCase() + parameterName.substring(1);
            //获取get的值
            Method method = clazz.getMethod(getMethod,new Class[]{});
            Object result = method.invoke(obj, new Object[]{});
            //为新对象赋值
            Method method1 = clazz.getMethod(setMethod, new Class[]{field.getType()});
            method1.invoke(newObj, new Object[]{result});
        }
        System.out.println(newObj);
        return  newObj;
    }
}
