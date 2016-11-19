package JavaMultiThreadProgramming.chapter02.syn;

import JavaMultiThreadProgramming.chapter01.MyThread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author NikoBelic
 * @create 14/11/2016 15:45
 */
public class UnSafeCountTest
{
    public static void main(String[] args) throws InterruptedException
    {
        Object o = new Object();
        for (int i = 0; i < 100; i++)
        {
            new MyThread(o).start();
        }
    }

    static class MyThread extends Thread
    {
        volatile static AtomicInteger count = new AtomicInteger(0);
        private Object o;
        public MyThread (Object o)
        {
            this.o = o;
        }
        public void run()
        {
            //synchronized (MyThread.class)
            //{
                for (int j = 0; j < 100000; j++)
                {
                    count.incrementAndGet();
                }
                System.out.println(Thread.currentThread().getName() + ".count = " + count.get());
            //}
        }
    }
}
