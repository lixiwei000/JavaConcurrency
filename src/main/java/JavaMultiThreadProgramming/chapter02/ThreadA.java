package JavaMultiThreadProgramming.chapter02;

/**
 * \
 *
 * @author NikoBelic
 * @create 14/11/2016 15:23
 */
public class ThreadA extends Thread
{
    boolean isRunning = true;

    @Override
    public void run()
    {

        while (isRunning)
        {
            System.out.println("程序正在运行...");
        }
        System.out.println("线程被停止了");
    }
}
