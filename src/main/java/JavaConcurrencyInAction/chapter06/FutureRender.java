package JavaConcurrencyInAction.chapter06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用Future等待图像下载
 *
 * @author NikoBelic
 * @create 16/6/12 15:09
 */
public class FutureRender
{
    private static final ExecutorService executor = Executors.newFixedThreadPool(100);
    static void rederPage(CharSequence sequence) throws InterruptedException, TimeoutException
    {
        final List<String> imageInfos = new ArrayList<String>();
        for (int i = 0; i < 50; i++)
        {
            imageInfos.add("image" + i);
        }
        Callable<List<String>> task = new Callable<List<String>>()
        {
            public List<String> call() throws Exception
            {
                List<String> result = new ArrayList<String>();
                for (String info : imageInfos)
                {
                    System.out.println("已完成下载" + info);
                    result.add(info + ".jpg");
                    Thread.sleep(100);
                }
                return result;
            }
        };

        Future<List<String>> future = executor.submit(task);
        // 下载文字
        for (int i = 0; i < 10; i++)
        {
            System.out.println("已成功加载文字" + i);
        }

        try
        {
            System.out.println("准备渲染...");
            List<String> imageData = future.get(1,TimeUnit.SECONDS);
            for (String data:imageData)
            {
                System.out.println("成功渲染图片:" + data);
            }
        }
        catch (InterruptedException e)
        {
            // 重新设置线程中断状态
            Thread.currentThread().interrupt();
            // 不需要结果,取消任务
            future.cancel(true);
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException, TimeoutException
    {
        rederPage(null);
    }

}
