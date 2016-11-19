package JavaMultiThreadProgramming.chapter04;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 * @author NikoBelic
 * @create 17/11/2016 19:25
 */
public class ReadWriteLockTest
{
    public static void main(String[] args)
    {
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable runnable1 = new Runnable()
        {
            public void run() {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "-获取读锁 "  + System.currentTimeMillis());
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                lock.readLock().unlock();
            }
        };

        Runnable runnable2 = new Runnable()
        {
            public void run()
            {
                lock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + "-获取写锁" + System.currentTimeMillis());
                try
                {
                    Thread.sleep(5000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                lock.writeLock().unlock();
            }
        };

        Thread t1 = new Thread(runnable1);
        t1.setName("线程A");
        Thread t2 = new Thread(runnable1);
        t2.setName("线程B");
        Thread t3 = new Thread(runnable2);
        t1.start();
        t3.start();
        t2.start();
    }
}
