package JavaConcurrencyInAction.chapter08;

import java.util.concurrent.*;

/**
 * 使用Semaphore来控制任务的提交速率
 *
 * @author NikoBelic
 * @create 16/6/17 13:25
 */
public class BoundedExecutor
{
    private final Executor exec;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor exec,int bound)
    {
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException
    {
        semaphore.acquire();
        System.out.println("获取Semaphore,剩余:" + semaphore.availablePermits());
        //Thread.sleep(2000);
        try
        {
            exec.execute(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        command.run();
                    }
                    finally
                    {
                        semaphore.release();
                        System.out.println("释放Semaphore,剩余:" + semaphore.availablePermits());
                    }
                }
            });
        }
        catch (RejectedExecutionException e)
        {
            semaphore.release();
            System.out.println("出现异常释放Semaphore,剩余:" + semaphore.availablePermits());
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        final ExecutorService exec = Executors.newFixedThreadPool(1000);
        final BoundedExecutor ex = new BoundedExecutor(exec,1);
        for (int i = 0; i < 100; i++)
        {
            final int j = i;
            new Thread(new Runnable()
            {
                public void run() {
                    try
                    {
                        ex.submitTask(new Runnable()
                        {
                            public void run() {
                                System.out.println("正在执行任务" + j);
                                try
                                {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
