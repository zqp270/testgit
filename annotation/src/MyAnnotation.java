import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/9 上午 9:31
 */

@SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String hello() default "";
}
