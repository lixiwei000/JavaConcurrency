package chapter04;

/**
 * @author NikoBelic
 * @create 16/6/7 20:39
 */
public class Singleton
{
    private static Singleton instance = null;

    public static synchronized Singleton getInstance()
    {
        if (instance == null)
        {
            instance = new Singleton();
            System.out.println("创建新实例");
        }
        return instance;
    }
    public static synchronized void show()
    {
        System.out.println("Singleton开始");
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Singleton结束");
    }

}
