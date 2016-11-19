package JavaMultiThreadProgramming.chapter04;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 16/11/2016 15:35
 */
public class ReentrantLockTest
{
    public static void main(String[] args)
    {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 5; i++)
        {
            new MyThread(lock).start();
        }
    }
}


class MyThread extends Thread
{
    Lock lock;
    public MyThread(Lock lock)
    {
        this.lock = lock;
    }
    @Override
    public void run()
    {
        lock.lock();
        for (int i = 0; i < 5; i++)
        {
            try
            {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);

        }
        lock.unlock();
    }
}