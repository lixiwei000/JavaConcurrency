package JavaMultiThreadProgramming.chapter03.producer_comsumer;

import java.util.concurrent.BlockingQueue;

/**
 * @author NikoBelic
 * @create 15/11/2016 14:06
 */
public class Producer extends Thread
{
    @Override
    public void run()
    {
        int i = 0;
        while(true)
        {
            try
            {
                Driver.queue.put(i++);
                System.out.println(Thread.currentThread().getName() + "生产者创建了 " + i);
                //Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
