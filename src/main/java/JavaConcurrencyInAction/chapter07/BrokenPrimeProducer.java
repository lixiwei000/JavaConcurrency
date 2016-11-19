package JavaConcurrencyInAction.chapter07;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 不可靠的取消操作将把生产者置于阻塞的操作中
 *
 * @author NikoBelic
 * @create 16/6/14 14:54
 */
public class BrokenPrimeProducer extends Thread
{
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        try
        {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)
            {
                queue.put(p = p.nextProbablePrime());
                System.out.println("生产:" + p);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("异常" + e.getMessage());
        }
    }
    public void cancel()
    {
        cancelled = true;
    }

    public static void main(String[] args) throws InterruptedException
    {
        BlockingQueue<BigInteger> primes = new LinkedBlockingDeque<BigInteger>(1000);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
        producer.start();
        Thread.sleep(2000);
        int count = 0;
        boolean first = true;
        try
        {
            while(true)
            {
                if (count >= 100)
                {
                    System.out.println("即将关闭生产者");
                    producer.interrupt();
                    //first = false;
                }
                else
                    count++;
                if (producer.isInterrupted())
                {
                    System.out.println("生产者关闭成功");
                    break;
                }
                //if (count < 1)
                //{
                //    BigInteger take = primes.take();
                //    System.out.println("成功获取:" + take);
                //    Thread.sleep(100);
                //    count++;
                //}
                //Thread.sleep(100);
                //if (count > 100)
                //    producer.cancel();
            }
        }
        finally
        {
            //producer.cancel();
        }
    }

}
