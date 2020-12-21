package proxy;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/8 下午 10:36
 */
public class Buyer implements BuyerRoom{
    @Override
    public Integer buyRoom() {
        System.out.println("我买房花了1000块钱");
        return 1000;
    }
}
