package JavaMiddleWare;

/**
 * @author NikoBelic
 * @create 16/8/10 14:42
 */
public class SynTest
{
    public void foo1() throws InterruptedException
    {
        synchronized (this)
        {
            System.out.println("Foo1-sync");
            Thread.sleep(10000);
        }
    }

    public synchronized void foo2()
    {
        System.out.println("Foo2");
    }

    public static void main(String[] args)
    {
        final SynTest t = new SynTest();
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    System.out.println("线程1启动");
                    t.foo1();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable()
        {
            public void run()
            {
                System.out.println("线程2启动");
                t.foo2();
            }
        }).start();
    }
}
