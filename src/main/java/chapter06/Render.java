package chapter06;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用CompletionService将页面元素下载后立即显示出来
 *
 * @author NikoBelic
 * @create 16/6/12 15:51
 */
public class Render
{
    private static  final  ExecutorService executor = Executors.newFixedThreadPool(200);

    static void  renderPage(CharSequence sequence)
    {
        final List<String> info = new ArrayList<String>();
        for (int j=0;j<100;j++)
            info.add("img" + j);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        // 下载图片
        for (final String img : info)
        {
            completionService.submit(new Callable<String>()
            {
                public String call() throws Exception
                {
                    Thread.sleep(info.indexOf(img) * 10000);
                    System.out.println("完成下载:" + img);
                    return img + ".jpg";
                }
            });
        }
        // 加载文字
        //for(int i=0;i<100;i++)
        //    System.out.println("已完成文字加载" + i);

        try
        {
            for (int t=0;t < info.size();t++)
            {
                System.out.println("take()前");
                Future<String> f = completionService.take();
                System.out.println("take()后");
                String imgName = f.get();
                System.out.println("正在渲染图片" + imgName);
                //Thread.sleep(1000);
            }
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        renderPage(null);
    }
}
