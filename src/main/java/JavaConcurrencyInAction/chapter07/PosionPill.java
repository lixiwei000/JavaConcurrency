package JavaConcurrencyInAction.chapter07;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 通过毒丸对象来关闭服务
 *
 * @author NikoBelic
 * @create 16/6/16 16:16
 */
public class PosionPill
{
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final File root;


    public PosionPill(BlockingQueue<File> queue, File root) {
        this.queue = queue;
        this.root = root;
    }


    public void start()
    {
        producer.start();
        consumer.start();
    }

    public void stop()
    {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException
    {
        consumer.join();
    }

    /**
     * 生产者
     */
    private class CrawlerThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {
                crawl(root);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    System.out.println("插入毒丸对象");
                    queue.put(POISON);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void crawl(File root) throws InterruptedException
    {
        File[] files = root.listFiles();
        if (files != null)
        {
            for (File f : files)
            {
                if (f.isDirectory())
                    crawl(f);
                else
                {
                    System.out.println("生产:" + f.getPath());
                    queue.put(f);
                }
            }
        }
    }

/**
 * 消费者
 */
private class IndexerThread extends Thread
{
    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                File file = queue.take();
                if (file == POISON)
                    break;
                else
                    System.out.println("消费:" + file.getPath());
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}

    public static void main(String[] args) throws InterruptedException
    {
        File root = new File("/Users/lixiwei-mac/Downloads");
        BlockingQueue<File> queue = new LinkedBlockingDeque<File>();
        PosionPill t = new PosionPill(queue, root);
        t.start();
        Thread.sleep(100);
        t.stop();

    }
}
