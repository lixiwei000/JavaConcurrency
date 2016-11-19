package JavaMultiThreadProgramming.chapter04;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 17/11/2016 15:41
 */
public class InterruptTest
{
    public static void main(String[] args) throws InterruptedException
    {
        final MyService service = new MyService(new ReentrantLock());
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                service.fuck();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        Thread.sleep(1000);
        t2.start();
        t2.interrupt(); // 并不能成功的中断lock.lock线程 可以中断lock.interruptibly
        System.out.println("Main end...");
    }
}

class MyService
{
    ReentrantLock lock ;
    public MyService(ReentrantLock lock)
    {
        this.lock = lock;
    }
    public void fuck()
    {
        try
        {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + "lock Begin..");
            for (int i = 0; i < Integer.MAX_VALUE; i++)
            {
                String s = new String();
                Math.random();
            }
            System.out.println(Thread.currentThread().getName() + " End..");
        }catch (Exception e)
        {
            System.out.println(Thread.currentThread().getName() + " Have an exception..");
            e.printStackTrace();
        }finally
        {
            lock.unlock();
            System.out.println("lock released");
        }
    }
}