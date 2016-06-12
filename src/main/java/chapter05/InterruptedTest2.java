package chapter05;

/**
 * 中断测试2,子线程通知父线程别等我了
 *
 * @author NikoBelic
 * @create 16/6/10 14:12
 */
public class InterruptedTest2 extends Thread
{
    private final Thread parent;
    InterruptedTest2(Thread parent)
    {
        this.parent = parent;
    }

    @Override
    public void run()
    {
        System.out.println("Sub Thread is Running");
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() - now <= 2000)
        {}
        parent.interrupt(); // 通知父线程 别等我了
        while (System.currentTimeMillis() - now <= 10000)
        {}
        System.out.println("子线程执行完毕");
    }

    public static void main(String[] args)
    {
        InterruptedTest2 subThread = new InterruptedTest2(Thread.currentThread());
        subThread.start();
        try
        {
            subThread.join(); // 等待子线程执行完毕
            //System.out.println("子线程执行完毕");
        } catch (InterruptedException e)
        {
            System.out.println("Parent Thread is going to die...");
        }
        System.out.println("父线程继续执行其他操作");
    }
}
