package JavaConcurrencyInAction.chapter07;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 不支持关闭的生产者-消费者日志服务
 *
 * @author NikoBelic
 * @create 16/6/15 11:20
 */
public class LogWriter
{
    private final BlockingQueue<String> queue;

    private final LoggerThread logger;

    public LogWriter(PrintWriter writer)
    {
        this.queue = new LinkedBlockingQueue<String>(100);
        this.logger = new LoggerThread(writer);
    }

    public void start()
    {
        logger.start();
    }

    public void log(String msg) throws InterruptedException
    {
        queue.put(msg);
    }

    private class LoggerThread extends Thread
    {
        private final PrintWriter writer;

        LoggerThread(PrintWriter writer)
        {
            this.writer = writer;
        }

        @Override
        public void run()
        {
            try
            {
                while (true)
                {
                    String m = queue.take();
                    writer.println(m);
                    System.out.println("队列:" + m);
                }
            }catch (InterruptedException e)
            {}
            finally
            {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        LogWriter logger = new LogWriter(new PrintWriter(System.out));
        logger.start();
        for (int i = 0; i < 100; i++)
        {
            logger.log("日志记录" + i);
            //System.out.println("?");
        }
    }
}
