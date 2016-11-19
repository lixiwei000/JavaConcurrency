package JavaMultiThreadProgramming.chapter07;

/**
 * @author NikoBelic
 * @create 18/11/2016 14:51
 */
public class ThreadGroupTest
{
    public static void main(String[] args)
    {
        Runnable runnable = new Runnable(){
            public void run() {
                while (!Thread.currentThread().isInterrupted())
                {
                    System.out.println(Thread.currentThread().getName());
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
        ThreadGroup groupA = new ThreadGroup("线程组A");
        Thread t1 = new Thread(groupA,runnable);
        Thread t2 = new Thread(groupA,runnable);
        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();
        System.out.println("活动的线程数为:" + groupA.activeCount());
        System.out.println("线程组的名称为:" + groupA.getName() );

    }
}
