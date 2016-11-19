package JavaMultiThreadProgramming.chapter03.producer_comsumer;

/**
 * @author NikoBelic
 * @create 15/11/2016 14:10
 */
public class Consumer extends Thread
{
    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                System.out.println(Thread.currentThread().getName() + "消费者消费了 " + Driver.queue.take());
                Thread.sleep(100);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
