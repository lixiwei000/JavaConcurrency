package JavaMultiThreadProgramming.chapter01;

/**
 * @author NikoBelic
 * @create 09/11/2016 09:44
 */
public class MyThread extends Thread
{
    @Override
    public void run()
    {
        try
        {
            for (int i = 0; i < 50000; i++)
            {
                if (this.interrupted())
                {
                    System.out.println("线程被停止了 我要 退出了" + this.getName());
                    //break;
                    throw new InterruptedException();
                }
                System.out.println(i);
            }
            System.out.println("for循环外的输出:表示线程并未被停止");
        }catch (InterruptedException e)
        {
            System.out.println("进入MyThread.java类run方法中的catch了");
        }

    }
}
