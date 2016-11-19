package JavaMultiThreadProgramming.chapter02;

/**
 * 对象锁
 * @author NikoBelic
 * @create 10/11/2016 09:33
 */
public class SynchronizedTest2
{
    public static void main(String[] args)
    {
        final SynObject o = new SynObject();
        new Thread(new Runnable()
        {
            public void run()
            {
                synchronized (o)
                {
                    try
                    {
                        o.methodA("线程1-");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable()
        {
            public void run()
            {
                synchronized (o)
                {
                    try
                    {
                        o.methodB("线程2-");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
