package chapter01;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 非线程安全Demo
 * @author NikoBelic
 * @create 09:07
 */
public class UnsafeSequence
{
    /*
        value++分3步操作:
        1.读取value值
        2.将value+1
        3.结果写入value
        可能导致第一个线程读取完value,还没有进行+1操作,第二个线程也读到了value,导致getNext获得同样的值
     */
    private int count = 0;
    private final AtomicLong safeCounter = new AtomicLong(0);

    public int getCount()
    {
        return this.count;
    }

    public AtomicLong getSafeCounter() {
        return safeCounter;
    }

    public int getNext()
    {
        return ++count;
    }

    public void test() {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try
                    {
                        Thread.sleep(1);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    //safeCounter.getAndIncrement();
                    getNext();
                }
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i<20; i++)
        {
            chapter01.UnsafeSequence obj = new chapter01.UnsafeSequence();
            obj.test();
            Thread.sleep(100);
            //System.out.println(obj.getSafeCounter());//期望输出500，可有时输出498、496,是因为print时候线程还没有执行完
            System.out.println(obj.getCount());
        }
    }
}
