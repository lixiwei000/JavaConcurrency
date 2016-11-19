package JavaMultiThreadProgramming.chapter04;

import JavaMultiThreadProgramming.chapter02.ThreadA;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 16/11/2016 17:03
 */
public class ConditionTest
{
    public static void main(String[] args) throws InterruptedException
    {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionThread t1 = new ConditionThread(lock, condition);
        ConditionThread t2 = new ConditionThread(lock, condition);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        lock.lock();
        condition.signal();
        lock.unlock();
        System.out.println("Main Thread End....");
    }
}

class ConditionThread extends Thread
{
    private Lock lock;
    private Condition condition;

    public ConditionThread(Lock lock, Condition conditon)
    {
        this.lock = lock;
        this.condition = conditon;
    }
    @Override
    public void run()
    {
        try
        {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":Before await..");
            condition.await();
            System.out.println(Thread.currentThread().getName() + ":After await..");
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }finally
        {
            lock.unlock();
            System.out.println("锁被释放了");
        }



    }
}


