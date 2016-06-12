package chapter05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试
 *
 * @author NikoBelic
 * @create 16/6/10 23:31
 */
public class SemaphoreTest<T>
{
    private final Set<T> set;
    private final Semaphore sem;

    public SemaphoreTest(int bound)
    {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException
    {
        sem.acquire();
        boolean wasAdded = false;
        try
        {
            wasAdded = set.add(o);
            return wasAdded;
        }
        finally
        {
            if (!wasAdded)
                sem.release();
        }
    }
    public boolean remove(Object o )
    {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }

    public static <T> void main(String[] args) throws InterruptedException
    {
        final SemaphoreTest<String> sem = new SemaphoreTest<String>(5);
        new Thread(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i <7 ; i++)
                {
                    try
                    {
                        sem.add("hey" + i);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(1000);
        sem.remove("hey3");
        Thread.sleep(1000);
        System.out.println(sem.set.toString());
    }
}
