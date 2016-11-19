package JavaConcurrencyInAction.chapter04;

import JavaConcurrencyInAction.chapter02.Widget;

/**
 * 通过一个私有锁来保护状态
 *
 * @author NikoBelic
 * @create 16/6/7 13:30
 */
public class PrivateLock
{
    private final Object myLock = new Object();
    Widget widget;
    void someMethod()
    {
        synchronized (myLock)
        {
            widget = new Widget();
            widget.doSth();
        }
    }
    public static void main(String[] args)
    {
        PrivateLock lock = new PrivateLock();
        lock.someMethod();
    }
}
