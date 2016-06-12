package chapter03;

/**
 * 在没有同步的情况下共享变量
 *
 * @author NikoBelic
 * @create 16/6/2 19:29
 */
public class NoVisibitlity
{
    private static volatile boolean ready = false;
    private static volatile int number = 0;

    private static class ReaderThread extends Thread
    {
        @Override
        public void run()
        {
            while(!ready)
            {
                Thread.yield();
                System.out.println(number);
            }

        }
    }
    // DWCR中,这个相当于我的主线程Controller,其中加载一条下载线程,下载能不能完成并不影响我的主线程给客户返回信息.
    public static String beginThread()
    {
        new ReaderThread().start();
        return ("beginThread任务完成");
    }
    public static void main(String[] args) throws InterruptedException
    {
        new ReaderThread().start();
        Thread.sleep(1);
        number = 42;
        Thread.sleep(1);
        ready = true;
    }
}
