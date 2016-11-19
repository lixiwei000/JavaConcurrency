package JavaConcurrencyInAction.chapter05;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 *
 * @author NikoBelic
 * @create 16/6/10 16:17
 */
public class TestHarness
{
    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException
    {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++)
        {
            Thread t = new Thread()
            {
                public void run()
                {
                    try
                    {
                        synchronized (startGate)
                        {
                            System.out.println("在起始门等待" + startGate.getCount());
                        }
                        startGate.await();
                        Thread.sleep(1000);
                        try
                        {
                            task.run();
                        }
                        finally
                        {
                            synchronized (endGate)
                            {
                                System.out.println("线程执行完毕" + endGate.getCount());
                            }
                            endGate.countDown();
                        }
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            System.out.println("任务计划完毕,启动线程");
            t.start();
        }
        long start = System.nanoTime();
        System.out.println("启动完毕,准备开始执行任务");
        startGate.countDown();
        endGate.await();
        System.out.println("结束门count=0,继续后续任务");
        long end = System.nanoTime();
        return end - start;
    }
    public static void main(String[] args) throws InterruptedException
    {
        long time = new TestHarness().timeTasks(3, new Runnable()
        {
            public void run() {
                System.out.println("Fuck");
            }
        });
        System.out.println("总共耗时" + time);
    }
}
