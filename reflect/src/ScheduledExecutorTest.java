import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Created by niugang on 2019/10/17/14:40
 */
public class ScheduledExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable beeper = new Runnable() {
            @Override
            public void run() {
                System.out.println("beep");
            }
        };
        /**
         * initialDelay：首次执行延迟的时间
         * period：连续执行之间的一段时间
         */
        final ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 10, SECONDS);

        scheduler.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("任务取消了");
                beeperHandle.cancel(true);
            }
        }, 60, SECONDS);
    }
}