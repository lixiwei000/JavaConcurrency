package JavaMultiThreadProgramming.chapter01;

/**
 * @author NikoBelic
 * @create 09/11/2016 09:38
 */
public class IntertuptedTest
{

    /**
     * interrupted() 和 isInterrupted()测试
     * @Author NikoBelic
     * @Date 09/11/2016 09:38
     * 000169
     */
    public static void main(String[] args) throws InterruptedException
    {
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("sleep");
        thread.sleep(100);
        thread.interrupt();
        thread.sleep(100);
        System.out.println("thread.isInterrupted()-1" + thread.isInterrupted());
        System.out.println("thread.isInterrupted()-2" + thread.isInterrupted());
        System.out.println("thread.isInterrupted()-3" + thread.isInterrupted());
        System.out.println("Main End");

    }
}
