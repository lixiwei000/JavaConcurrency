package JavaMultiThreadProgramming.chapter07;

/**
 * @author NikoBelic
 * @create 18/11/2016 17:08
 */
public class GroupStopTest
{
    public static void main(String[] args) throws InterruptedException
    {
        ThreadGroup myGroup = new ThreadGroup("我的线程组");
        for (int i = 0; i < 5; i++)
        {
            new MyThread(myGroup,"线程" + i).start();
        }
        Thread.sleep(2000);
        myGroup.interrupt(); // 并不会引发子线程的异常
    }
}

class MyThread extends Thread
{
    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + "准备死循环...");
        while (!Thread.currentThread().isInterrupted())
        {}
        System.out.println(Thread.currentThread().getName() + "结束啦....");
    }
}
