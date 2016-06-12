package chapter05;

/**
 * 线程中断测试
 * @author NikoBelic
 * @create 16/6/10 14:07
 */
public class InterruptedTest extends Thread
{
    @Override
    public void run()
    {
        try
        {


            while (true)
            {
                if (isInterrupted() == true)
                    System.out.println("I was interrupted...");
                else
                    System.out.println("Going...");
                long now = System.currentTimeMillis();
                //while (System.currentTimeMillis() - now <= 1000)
                //{
                //     空转1秒
                //}
                Thread.sleep(10000);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println("子线程被中断了,恢复中断状态");
            Thread.currentThread().interrupt();
            System.out.println("子线程当前是否中断?" + Thread.currentThread().isInterrupted());
        }
    }
    public static void main(String[] args)
    {
        InterruptedTest t = new InterruptedTest();
        t.start();
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            System.out.println("父线程被中断了");
        }
        t.interrupt();
        t.interrupt();
    }
}
