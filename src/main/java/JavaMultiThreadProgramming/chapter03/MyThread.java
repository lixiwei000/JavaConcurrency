package JavaMultiThreadProgramming.chapter03;

/**
 * @author NikoBelic
 * @create 15/11/2016 08:41
 */
public class MyThread extends Thread
{
    Object lock;

    public MyThread(Object lock)
    {
        this.lock = lock;
    }

    public void run()
    {
        int count = 0;
        synchronized (lock)
        {
            while (true)
            {

                System.out.println(Thread.currentThread().getName() + "正在执行..." + count++);
                try
                {
                    Thread.sleep(400);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (count == 10)
                {
                    try
                    {
                        lock.wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
