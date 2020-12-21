package createEnum;

import javax.jws.Oneway;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/6 上午 10:52
 */

/**
 * 枚举类  代表当前对象是有限多个
 */

/**
 * 枚举类是简单类型的
 */
public enum Season {
    //相当于  public static final Season Spring  = new Season("春天");
    SPRING("春天"),

    SUMMER("夏天"),
    QT("秋天");


    private final String seasonName;

    private Season(String seasonName) {
        this.seasonName = seasonName;
    }
}
