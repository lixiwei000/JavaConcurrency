package JavaMultiThreadProgramming.chapter03;

/**
 * @author NikoBelic
 * @create 16/11/2016 14:52
 */
public class ThreadLocalTest
{
    static ThreadLocal threadLocal = new MyInheritThreadLocal();
    public static void main(String[] args)
    {
        threadLocal.set("我是主线程的ThreadLocal");// 这里虽然改变了,但是子线程继承下来的下的值不会发生变化
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        new Thread(new Runnable()
        {
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            }
        }).start();
    }
}





class MyThreadLocal extends ThreadLocal
{
    @Override
    protected Object initialValue()
    {
        return "我是默认的ThredLocal值";
    }
}

class MyInheritThreadLocal extends InheritableThreadLocal
{
    @Override
    protected Object childValue(Object parentValue)
    {
        return "我是从父线程继承下来的ThreadLocal-Value";
    }
}