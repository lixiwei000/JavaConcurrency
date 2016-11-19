package JavaMiddleWare;

import java.util.concurrent.Semaphore;

/**
 * @author NikoBelic
 * @create 16/8/10 19:17
 */
public class SemaphoreTest
{
    public static void main(String[] args)
    {
        final Semaphore semaphore = new Semaphore(2);

        int count = 5;
        for (int i = 0; i < count; i++)
        {
            final int finalI = i;
            new Thread(new Runnable()
            {
                public void run() {
                    try
                    {
                        semaphore.acquire();
                        System.out.println("执行" + (finalI +1) + "线程");
                        Thread.sleep(4000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        semaphore.release();
                    }
                }
            }).start();
        }
    }
}
