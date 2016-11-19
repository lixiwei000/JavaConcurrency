package JavaConcurrencyInAction.chapter08;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * 增加了日志和计时等功能的线程池
 * @author NikoBelic
 * @create 16/6/17 15:36
 */
public class TimingThreadPool extends ThreadPoolExecutor
{
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
    private final Logger log = Logger.getLogger("TimingThreadPool");
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info(String.format("Thread %s: start %s",t,r));
        //System.out.println((String.format("Thread %s: start %s",t,r)));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t)
    {
        try
        {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.info(String.format("Thread %s: end %s,time = %dns:",t,r,taskTime));

        }finally
        {
            super.afterExecute(r,t);
        }
    }

    @Override
    protected void terminated()
    {
        try
        {
            log.info(String.format("Terminated: avg time = %dms",totalTime.get() / numTasks.get() / 1000000));
        }finally
        {
            super.terminated();
        }
    }
    public static void main(String[] args) throws InterruptedException
    {
        /**
         l  如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
         l  如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
         l  如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
         l  如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
         l  当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
         */
        BlockingQueue queue = new LinkedBlockingDeque(1);
        TimingThreadPool pool = new TimingThreadPool(1,2,5,TimeUnit.SECONDS,queue);
        pool.setRejectedExecutionHandler(new RejectedExecutionHandler()
        {
            // 当抛出RejectedExecutionException异常时，会调用rejectedExecution方法
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("他妈的慢啦  滚草");
            }
        });
        for (int i = 0; i < 5; i++)
        {
            final int j = i;
            pool.execute(new Runnable()
            {
                public void run() {
                    System.out.println("T-" + j);
                    try
                    {
                        Thread.sleep(2000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        Thread.sleep(2100);
        pool.terminated();
    }
}
