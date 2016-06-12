package chapter03;

/**
 * @author NikoBelic
 * @create 16/6/3 11:57
 */
public class VolatileTest
{
    public static int count = 0;
    public static boolean stop;
    public static void inc()
    {
        while(!stop)
            System.out.println("停不下来啦"+ count++);
    }
    public static void changeStatus()
    {
        stop = true;
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                inc();
            }
        }).start();
        new Thread(new Runnable()
        {
            public void run()
            {
                changeStatus();
            }
        }).start();
    }
}
