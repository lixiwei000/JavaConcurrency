package JavaConcurrencyInAction.chapter10;

/**
 * 简单的锁顺序死锁

 Write a function that takes a string as input and returns the string reversed.

 Example:
 Given s = "hello", return "olleh".

 * @author NikoBelic
 * @create 16/6/20 12:44
 */
public class LeftRightDeadlock
{
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() throws InterruptedException
    {
        synchronized (left)
        {
            Thread.sleep(100);
            synchronized (right)
            {
                System.out.println("LeftRightLock");
            }
        }
    }

    public void rightLeft() throws InterruptedException
    {
        synchronized (right)
        {
            Thread.sleep(100);
            synchronized (left)
            {
                System.out.println("RightLeftLock");
            }
        }
    }
    public static void main(String[] args)
    {
        final LeftRightDeadlock lock = new LeftRightDeadlock();
        new Thread(new Runnable()
        {
            public void run() {
                try
                {
                    lock.leftRight();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable()
        {
            public void run() {
                try
                {
                    lock.rightLeft();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
