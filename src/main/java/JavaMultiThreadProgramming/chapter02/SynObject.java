package JavaMultiThreadProgramming.chapter02;

/**
 * @author NikoBelic
 * @create 10/11/2016 09:32
 */
public class SynObject
{
    public void methodA(String name) throws InterruptedException
    {
        System.out.println(name + "MethodA begin at " + System.currentTimeMillis());
        System.out.println(name + "Sleeping...");
        Thread.sleep(5000);
        System.out.println(name + "MethodA end at " + System.currentTimeMillis());
    }

    public void methodB(String name) throws InterruptedException
    {
        System.out.println(name + "MethodA begin at " + System.currentTimeMillis());
        System.out.println(name + "Sleeping...");
        Thread.sleep(5000);
        System.out.println(name + "MethodA end at " + System.currentTimeMillis());
    }
}
