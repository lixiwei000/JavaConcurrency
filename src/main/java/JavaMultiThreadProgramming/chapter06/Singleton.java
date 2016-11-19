package JavaMultiThreadProgramming.chapter06;

/**
 * @author NikoBelic
 * @create 18/11/2016 10:03
 */
public class Singleton
{
    private static Object instance;

    public synchronized static Object getInstance() throws InterruptedException
    {
        if (instance == null)
        {
            Thread.sleep(1000);
            instance = new Object();
        }
        return instance;
    }

    public static void main(String[] args)
    {
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                try
                {
                    System.out.println(Singleton.getInstance());
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
