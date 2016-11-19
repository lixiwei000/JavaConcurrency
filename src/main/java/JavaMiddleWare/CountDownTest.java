package JavaMiddleWare;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author NikoBelic
 * @create 16/8/10 16:04
 */
public class CountDownTest
{
    public static void main(String[] args) throws InterruptedException
    {
        int count = 100;
        final CountDownLatch latch = new CountDownLatch(count);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,3,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(count));
        final int[] datas = new int[1024000];
        for (int i = 0; i < datas.length; i++)
        {
            datas[i] = new Random().nextInt(10);
        }
        int step = datas.length / count;
        for (int i = 0; i < count; i++)
        {
            final int no = i;
            final int start = i * step;
            int end = (i+1)*step;
            if (i == count - 1)
                end = datas.length;
            final int finalEnd = end;
            threadPool.execute(new Runnable()
            {
                public void run() {
                    try
                    {
                        Thread.sleep(100);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    for (int m = start; m < finalEnd; m++)
                    {
                        for (int n=m+1;n < finalEnd;n++)
                        {
                            int temp = datas[m];
                            if (datas[m] > datas[n])
                            {
                                temp = datas[m];
                                datas[m] = datas[n];
                                datas[n] = temp;
                            }
                        }
                    }
                    for (int k=start;k<finalEnd;k++)
                    {
                        System.out.print(datas[k] + ",");
                    }
                    System.out.println("");
                    latch.countDown();
                }
            });
        }
        // 合并
        latch.await();
        System.out.println("全部完成");
    }
}
