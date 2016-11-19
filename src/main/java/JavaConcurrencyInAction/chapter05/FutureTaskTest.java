package JavaConcurrencyInAction.chapter05;


import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask来提前加载稍后需要的数据
 *
 * @author NikoBelic
 * @create 16/6/10 20:23
 */
public class FutureTaskTest
{

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        Callable pAccount = new PrivateAccount();
        FutureTask futureTask = new FutureTask(pAccount);
        Thread pAccountThread = new Thread(futureTask);
        System.out.println("FutureTask线程开始启动,启动时间为" + System.nanoTime());
        pAccountThread.start();
        System.out.println("主线程开始执行任务");
        int totalMoney = new Random().nextInt(100000);
        System.out.println("现在你在其他账户中的总金额为" + totalMoney);
        System.out.println("等待私有账户总金额统计完毕...");

        while (!futureTask.isDone())
        {
            Thread.sleep(500);
            System.out.println("私有账户计算未完成,继续等待");
        }
        System.out.println("futureTask线程计算完毕，此时时间为" + System.nanoTime());
        Integer privateAccountMoney = null;
        privateAccountMoney = (Integer) futureTask.get();
        System.out.println("您现在的总金额为：" + totalMoney + privateAccountMoney.intValue());
    }

    private static class PrivateAccount implements Callable
    {
        Integer totalMoney;

        public Object call() throws Exception
        {
            totalMoney = new Random().nextInt(10000);
            try
            {
                Thread.sleep(5000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("您当前有" + totalMoney + "美元在小金库中");
            return totalMoney;
        }
    }
}
