package JavaMultiThreadProgramming.chapter02;

/**
 * @author NikoBelic
 * @create 14/11/2016 15:15
 */
public class VolatileTest
{
    public static void main(String[] args) throws InterruptedException
    {
        VolatileTest t = new VolatileTest();

        ThreadA t2 = new ThreadA();
        t2.start();
        Thread.sleep(1000);
        t2.isRunning = false;
        System.out.println("主进程结束");
    }

}
