package chapter02;

/**
 * 死锁测试
 * @author NikoBelic
 * @create 16/6/1 11:40
 */
public class Widget
{
    public synchronized  void doSth()
    {
        System.out.println("Widget.doSth");
    }

    public static void main(String[] args)
    {
        LoggingWidget loggingWidget = new LoggingWidget();
        loggingWidget.doSth();
    }
}

class LoggingWidget extends Widget
{
    public synchronized void doSth()
    {
        System.out.println("LoggingWidget.doSth");
        super.doSth();
    }
}

