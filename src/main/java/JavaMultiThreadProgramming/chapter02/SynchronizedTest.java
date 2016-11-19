package JavaMultiThreadProgramming.chapter02;

/**
 * 解决了我之前的一个误区
 * 我原本以为synchronized关键字的会直接将对象作为一把锁,一个线程调用它时,其他线程无法调用其中的任何方法
 * 但是通过实验证明,如果对obj中的一个方法上锁,只是访问这个方法时才会同步,另一个线程可以随时调用没有同步的方法。
 * @author NikoBelic
 * @create 10/11/2016 09:24
 */
public class SynchronizedTest
{
    public static void main(String[] args)
    {
        final MyObject o = new MyObject();
        Thread t1 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    o.methodA("线程1");
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
        });
        Thread t2 = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    o.methodB("线程2");
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
