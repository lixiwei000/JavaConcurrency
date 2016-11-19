package JavaMultiThreadProgramming.chapter04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author NikoBelic
 * @create 17/11/2016 13:47
 */
public class FairLockTest
{
    public static void main(String[] args)
    {
        final ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        List<Thread> threadList = new ArrayList();
        for (int i = 0; i < 10; i++)
        {
            final int finalI = i;
            threadList.add(new Thread(new Runnable()
            {
                public void run()
                {
                    lock.lock();
                    for (int j = 0; j < 10; j++)
                    {
                        System.out.println(Thread.currentThread().getName() + "-" + j);
                    }
                    lock.unlock();
                }
            }));
        }
        for (Thread thread : threadList)
        {
            thread.start();
        }
    }
}
