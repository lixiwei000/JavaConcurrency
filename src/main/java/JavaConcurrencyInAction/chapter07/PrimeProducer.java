package JavaConcurrencyInAction.chapter07;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 通过中断来取消任务
 *
 * @author NikoBelic
 * @create 16/6/14 18:58
 */
public class PrimeProducer extends Thread
{
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try
        {
            //while (!Thread.currentThread().isInterrupted())
            //{
            while(true)
            {
                queue.put(p = p.nextProbablePrime());
                Thread.sleep(100);
                System.out.println("生产:" + p);
            }
            //}
            //System.out.println("子线程结束");
        }
        catch (InterruptedException e)
        {
            System.out.println("线程被中断");
            Thread.currentThread().interrupt();
        }

    }
    public static void main(String[] args) throws InterruptedException
    {
        BlockingQueue<BigInteger> queue = new LinkedBlockingDeque<BigInteger>();
        PrimeProducer producer = new PrimeProducer(queue);
        producer.start();
        Thread.sleep(1000);
        System.out.println("主线程准备中断Producer线程");
        producer.interrupt();
        Thread.sleep(100);
        System.out.println("Status:" + producer.isInterrupted());
    }
}
