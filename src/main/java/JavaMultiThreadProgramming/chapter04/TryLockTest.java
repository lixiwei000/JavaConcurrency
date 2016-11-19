package JavaMultiThreadProgramming.chapter04;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 17/11/2016 15:58
 */
public class TryLockTest
{
    public static void main(String[] args)
    {
        final ReentrantLock lock = new ReentrantLock();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                try
                {
                    if (lock.tryLock(3, TimeUnit.SECONDS))
                    {
                        System.out.println(Thread.currentThread().getName() + " 获得了锁");
                        Thread.sleep(5000);
                    }
                    else
                        System.out.println(Thread.currentThread().getName() + " 没获得锁");
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };


        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();

    }
}
