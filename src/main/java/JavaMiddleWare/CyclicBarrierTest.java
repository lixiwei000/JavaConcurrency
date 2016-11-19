package JavaMiddleWare;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author NikoBelic
 * @create 16/8/10 19:10
 */
public class CyclicBarrierTest
{
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException
    {
        int count = 5;
        final CyclicBarrier barrier = new CyclicBarrier(count+1);
        for (int i = 0; i < count; i++)
        {
            final int finalI = i;
            new Thread(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        Thread.sleep(finalI*1000);
                        barrier.await();
                        System.out.println("线程" + (finalI + 1) + "执行完毕");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        System.out.println("线程全部结束");
    }
}
