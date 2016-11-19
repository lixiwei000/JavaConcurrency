package JavaMultiThreadProgramming.chapter03;

/**
 * @author NikoBelic
 * @create 15/11/2016 08:33
 */
public class ThreadCommunicationTest
{
    public static void main(String[] args) throws InterruptedException
    {
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        MyThread t1 = new MyThread(lock1);
        //MyThread t2 = new MyThread(lock2);
        t1.start();
        //t2.start();
        Thread.sleep(8000);
        synchronized (lock1)
        {
            lock1.notifyAll();
        }
    }
}

