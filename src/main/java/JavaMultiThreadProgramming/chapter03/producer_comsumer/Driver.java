package JavaMultiThreadProgramming.chapter03.producer_comsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author NikoBelic
 * @create 15/11/2016 14:08
 */
public class Driver
{
    public static BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);

    public static void main(String[] args)
    {
        // 创建生产者
        new Producer().start();
        for (int i = 0; i < 10; i++)
        {
            new Consumer().start();
        }
    }
}
