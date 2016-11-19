package JavaMultiThreadProgramming.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NikoBelic
 * @create 09/11/2016 13:06
 */
public class InterruptedTest2
{
    public static void main(String[] args)
    {
        List<Thread> threadList = new ArrayList();
        for (int i = 0; i < 100; i++)
        {
            Thread t = new Thread(new Runnable()
            {
                public void run()
                {
                    for (int i = 0 ; i < 5000 ; i++)
                    {
                        if (Thread.currentThread().isInterrupted())
                        {
                            System.out.println(Thread.currentThread().getName() + " 被停止了");
                            break;
                        }
                        else
                        {
                            System.out.println(Thread.currentThread().getName() + ",timer=" + System.currentTimeMillis());
                        }
                    }
                }
            },"线程-" + i);
            threadList.add(t);
            t.start();
        }
        for (Thread thread : threadList)
        {
            thread.interrupt();
        }
    }
}
