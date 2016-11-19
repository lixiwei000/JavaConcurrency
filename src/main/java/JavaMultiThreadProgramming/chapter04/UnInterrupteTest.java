package JavaMultiThreadProgramming.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 17/11/2016 16:45
 */
public class UnInterrupteTest
{
    public static void main(String[] args)
    {
        final ReentrantLock lock = new ReentrantLock();
        final Condition conditon = lock.newCondition();
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                try
                {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 即将await");
                    conditon.awaitUninterruptibly();
                    System.out.println(Thread.currentThread().getName() + " await结束");
                } finally
                {
                    System.out.println(Thread.currentThread().getName() + " 释放所");
                    lock.unlock();
                }

            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();
        t1.interrupt();

    }
}
