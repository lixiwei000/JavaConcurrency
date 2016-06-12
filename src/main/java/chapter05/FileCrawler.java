package chapter05;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 生产者
 *
 * @author NikoBelic
 * @create 16/6/10 10:39
 */
public class FileCrawler implements Runnable
{
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;
    private final BlockingQueue<File> filePool;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root)
    {
        this.fileQueue = fileQueue;
        this.filePool = new LinkedBlockingDeque<File>();
        this.fileFilter = fileFilter;
        this.root = root;
    }

    public void run()
    {
        try
        {
            while (true)
                crawl(root);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    private void crawl(File root) throws InterruptedException
    {
        File[] entries = root.listFiles(fileFilter);
        //File[] entries = root.listFiles();
        if (entries != null)
        {
            for (File entry : entries)
            {
                if (entry.isDirectory())
                    crawl(entry);
                else
                {
                    if (!filePool.contains(entry))
                    {
                        System.out.println("生产:"+entry.getName());
                        fileQueue.put(entry);
                        filePool.put(entry);
                    }
                    //Thread.sleep(1000);
                }
            }
        }
    }
}
