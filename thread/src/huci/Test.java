package huci;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/11 下午 10:06
 */
class Xhl {
    public Integer count = 1;
    public Runnable[] runnables = new Runnable[10];

}
class Sp implements Runnable {
    private Integer count;

    @Override
    public void run() {
        synchronized(this) {
            if (this.count > 100) {
                return;
            }
            count++;
        }
    }
}
public class Test {
}
