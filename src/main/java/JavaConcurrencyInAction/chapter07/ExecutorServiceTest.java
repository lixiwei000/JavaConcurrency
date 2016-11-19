package JavaConcurrencyInAction.chapter07;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试线程池两种shutdown方法
 *
 * @author NikoBelic
 * @create 16/6/16 18:26
 */
public class ExecutorServiceTest
{
    private final ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private AtomicInteger threadNo = new AtomicInteger(0);
    public void start(int count)
    {
        for (int i = 0; i < count; i++)
        {
            threadPool.execute(new Runnable()
            {
                public void run() {
                    int tno = threadNo.getAndAdd(1);
                    while (true)
                    {
                        System.out.println("Thread " + tno + " is running...");
                        try
                        {
                            Thread.sleep(1000);
                        } catch (InterruptedException e)
                        {
                            break;
                        }
                    }
                }
            });
        }
    }
    public void stop() throws InterruptedException
    {
        List<Runnable> cancelledTasks = threadPool.shutdownNow();
        System.out.println("已提交但未开始的任务:" + cancelledTasks.size());
         //threadPool.shutdown();
    }
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorServiceTest t = new ExecutorServiceTest();
        t.start(5);
        Thread.sleep(2000);
        t.stop();
    }
}
