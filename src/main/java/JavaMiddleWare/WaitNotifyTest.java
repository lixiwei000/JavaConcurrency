package JavaMiddleWare;

/**
 * @author NikoBelic
 * @create 16/8/10 15:43
 */
public class WaitNotifyTest
{
    public void testWait() throws InterruptedException
    {
        synchronized (this)
        {
            System.out.println("即将wait");
            this.wait();
            System.out.println("wait完毕");
        }
    }
    public void testNotify()
    {
        synchronized (this)
        {
            System.out.println("即将notify");
            this.notify();
            System.out.println("notify完毕");
        }
    }
    public void testNotifyAll()
    {
        synchronized (this)
        {
            System.out.println("即将全部唤醒");
            this.notifyAll();
            System.out.println("全部唤醒完毕");
        }
    }
    public static void main(String[] args) throws InterruptedException
    {
        final WaitNotifyTest t1 = new WaitNotifyTest();
        for (int i=0;i<5;i++)
        {
            final WaitNotifyTest t = new WaitNotifyTest();
            new Thread(new Runnable()
            {
                public void run() {
                    try
                    {
                        t.testWait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
            if (i == 4)
                t.testNotifyAll();
        }
        System.out.println("-----睡眠3秒------");
        Thread.sleep(3000);

    }
}
