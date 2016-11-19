package JavaMultiThreadProgramming.chapter04;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author NikoBelic
 * @create 17/11/2016 19:58
 */
public class MultiReadWriteLockTest
{
    public static void main(String[] args)
    {
        ReentrantReadWriteLock lockA = new ReentrantReadWriteLock();
        ReentrantReadWriteLock lockB = new ReentrantReadWriteLock();

        ThreadA read1 = new ThreadA(lockA);
        read1.setName("A锁读线程1");
        //ThreadA read2 = new ThreadA(lockA);
        //read2.setName("A锁读线程2");
        //ThreadA read3 = new ThreadA(lockB);
        //read3.setName("B锁读线程1");

        read1.start();
        //read2.start();
        //read3.start();

        ThreadB write1 = new ThreadB(lockA);
        write1.setName("A锁写线程1");
        //ThreadB write2 = new ThreadB(lockB);
        //write2.setName("B锁写线程1");

        write1.start();
        //write2.start();

    }
}


class ThreadA extends Thread
{
    ReentrantReadWriteLock lock;

    public ThreadA(ReentrantReadWriteLock lock)
    {
        this.lock = lock;
    }

    @Override
    public void run()
    {
        try
        {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获取读锁");
            Thread.sleep(1000);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            lock.readLock().unlock();
        }
    }
}

class ThreadB extends Thread
{
    ReentrantReadWriteLock lock;
    public ThreadB(ReentrantReadWriteLock lock)
    {
        this.lock = lock;
    }

    @Override
    public void run()
    {
        try
        {

            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获取写锁");
            Thread.sleep(3000);

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }finally
        {
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            lock.writeLock().unlock();
        }
    }
}