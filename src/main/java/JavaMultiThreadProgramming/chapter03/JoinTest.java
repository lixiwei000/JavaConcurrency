package JavaMultiThreadProgramming.chapter03;

/**
 * @author NikoBelic
 * @create 15/11/2016 22:22
 */
public class JoinTest
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("Main Begin..." + System.currentTimeMillis());
        MyThread t = new MyThread();
        t.start();
        t.join();
        //Thread.currentThread().join(); // 死锁
        System.out.println("Main End..." + System.currentTimeMillis());
    }
    static class MyThread extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 20; i++)
            {
                System.out.println(i);
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
