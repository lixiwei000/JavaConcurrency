package JavaConcurrencyInAction.chapter10;

/**
 * 测试锁的作用范围
 *
 * @author NikoBelic
 * @create 16/6/20 14:49
 */
public class ObjectSynMethodTest
{
    public synchronized void testA() throws InterruptedException
    {
        System.out.println("线程A准备睡眠10秒");
        Thread.sleep(10000);
        System.out.println("线程A睡眠10秒完毕");
    }
    public synchronized void testB() throws InterruptedException
    {
        System.out.println("线程B抢占CPU资源20秒");
        Thread.sleep(20000);
        System.out.println("线程B运行完毕");
    }

    public static void main(String[] args) throws InterruptedException
    {
        final ObjectSynMethodTest t = new ObjectSynMethodTest();
        for (int i = 0; i < 3; i++)
        {

            new Thread(new Runnable()
            {
                public void run() {
                    try
                    {
                        t.testA();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        t.testB();
    }
}
