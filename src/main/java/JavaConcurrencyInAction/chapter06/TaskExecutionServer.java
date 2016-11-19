package JavaConcurrencyInAction.chapter06;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于线程池的Web服务器
 *
 * @author NikoBelic
 * @create 16/6/12 11:12
 */
public class TaskExecutionServer
{
    private static final int NTHREADS = 50;
    private static final Executor exec = Executors.newFixedThreadPool(NTHREADS);
    public static void main(String[] args) throws IOException
    {
        //ServerSocket socket = new ServerSocket(80);
        //while (true)
        //{
        //    final Socket connection = socket.accept();
        //    Runnable task = new Runnable()
        //    {
        //        public void run()
        //        {
        //            System.out.println("s");
        //        }
        //    };
        //}
        for (int i=0;i<500;i++)
        {
            final int j = i;
            exec.execute(new Runnable()
            {
                public void run() {
                    try
                    {
                        System.out.println("即将睡眠" +  j/10.0 + "秒");
                        Thread.sleep(j*100);
                        System.out.println(j/10.0 + "秒睡眠完毕");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
