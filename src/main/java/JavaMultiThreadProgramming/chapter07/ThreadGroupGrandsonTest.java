package JavaMultiThreadProgramming.chapter07;

/**
 * @author NikoBelic
 * @create 18/11/2016 14:59
 */
public class ThreadGroupGrandsonTest
{
    public static void main(String[] args) throws InterruptedException
    {
        Runnable runnable = new Runnable()
        {
            public void run()
            {
                while (!Thread.currentThread().isInterrupted())
                {
                    System.out.println(Thread.currentThread().getName() + " is running...");
                    try
                    {
                        Thread.sleep(3000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };


        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup groupA = new ThreadGroup(mainGroup,"groupA");

        Thread t1 = new Thread(groupA,runnable);
        t1.setName("线程1");
        t1.start();

        // 获取主线程组中活动线程数量,按照此数量创建线程组数组
        ThreadGroup[] listGroup = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(listGroup);
        Thread.sleep(1000);
        System.out.println("main线程中有多少个子线程组:" + listGroup.length + " 名字为:" + listGroup[0].getName());

        // 获取主线程组中的子线程组中的所有线程
        Thread[] listThread = new Thread[listGroup[0].activeCount()];
        listGroup[0].enumerate(listThread);
        System.out.println("Main线程组中的第1个线程组的第一个线程为:" + listThread[0].getName());




    }
}
