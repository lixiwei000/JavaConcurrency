package chapter04;

/**
 * @author NikoBelic
 * @create 16/6/7 20:41
 */
public class Multiple
{
    public synchronized static void show()
    {
        System.out.println("Multiple开始");
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Multiple结束");
    }
}
