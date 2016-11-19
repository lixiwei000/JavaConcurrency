package JavaMultiThreadProgramming.chapter03;

import java.util.Random;

/**
 * @author NikoBelic
 * @create 16/11/2016 13:55
 */
public class InterruptTest
{

    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("主线程开始执行");
        ThreadA t = new ThreadA();
        ThreadB tb = new ThreadB(t);
        t.start();
        Thread.sleep(2000);
        tb.start();
        t.join();
        System.out.println("主线程结束");
    }
}

class ThreadA extends Thread
{
    @Override
    public void run()
    {
        System.out.println("线程A正在执行");
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            System.out.println("线程A出现了中断异常");
            e.printStackTrace();
        }
        System.out.println("线程A即将结束");
    }
}

class ThreadB extends Thread
{
    private ThreadA t;
    public ThreadB(ThreadA t)
    {
        this.t = t;
    }
    @Override
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + "即将中断线程A");
        t.interrupt();
    }
}