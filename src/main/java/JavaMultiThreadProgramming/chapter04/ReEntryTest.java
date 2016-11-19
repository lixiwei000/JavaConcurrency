package JavaMultiThreadProgramming.chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 16/11/2016 20:21
 */
public class ReEntryTest
{
    public static void main(String[] args)
    {
        // 首先测试synchronized是否是可重入锁
        Object o = new Object();
        synchronized (o)
        {
            System.out.println("This is out lock");
            synchronized (o)
            {
                System.out.println("This is in lock");
            }
        }

        // 测试reentrantLock是否是可重入锁
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                lock.lock();
                try
                {
                    condition.await();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get the lock...");
                lock.unlock();
            }
        });
        System.out.println("current locksize  : " + lock.getHoldCount());
        lock.lock();
        t.start();
        System.out.println("current locksize  : " + lock.getHoldCount());
        lock.lock();
        System.out.println("current locksize  : " + lock.getHoldCount());
        System.out.println("wait thread : " + lock.getQueueLength());
        lock.unlock();
        System.out.println("current locksize  : " + lock.getHoldCount());
        System.out.println("current waiting lock : " + lock.getQueueLength());
        System.out.println("weather some threads is waiting for the lock : " + lock.hasQueuedThreads());
        System.out.println("weather some threads is waiting for the lock : " + lock.hasQueuedThread(t));
        System.out.println("weather some thread are waiting for condition : " + lock.hasWaiters(condition));
        lock.unlock();
        lock.lock();
        System.out.println("current locksize  : " + lock.getHoldCount());
        System.out.println("current waiting lock : " + lock.getQueueLength());
        System.out.println("weather some threads is waiting for the lock : " + lock.hasQueuedThreads());
        System.out.println("weather some threads is waiting for the lock : " + lock.hasQueuedThread(t));
        System.out.println("weather some thread are waiting for condition : " + lock.hasWaiters(condition));
        condition.signal();
        System.out.println("is fair : " + lock.isFair());
        lock.unlock();


        System.out.println("----------------------------------------------");


    }
}
