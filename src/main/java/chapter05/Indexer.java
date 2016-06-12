package chapter05;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消费者
 *
 * @author NikoBelic
 * @create 16/6/10 10:44
 */
public class Indexer implements Runnable
{
    private final BlockingQueue<File> queue;
    private final int threadNo;
    public Indexer(BlockingQueue<File> queue,int threadNo)
    {
        this.queue = queue;
        this.threadNo = threadNo;
    }
    public void run()
    {
        while(true)
            try
            {
                System.out.println(threadNo+"消费:"+queue.take().getName());
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
    }




    public static void main(String[] args)
    {
        File root = new File("/Users/lixiwei-mac/Downloads");
        BlockingQueue<File> queue = new LinkedBlockingQueue<File>();
        FileCrawler producer = new FileCrawler(queue,null,root);
        new Thread(producer).start();
        for (int i=0;i<5;i++)
        {
            Indexer consumer = new Indexer(queue,i);
            new Thread(consumer).start();
        }
    }
}

