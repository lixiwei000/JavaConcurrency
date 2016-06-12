package chapter06;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 错误的Timer行为
 *
 * @author NikoBelic
 * @create 16/6/12 14:12
 */
public class TImerTest
{
    public static void main(String[] args) throws Exception
    {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(1000);
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(5000);
        System.out.println("执行完毕");
    }
    static class ThrowTask extends TimerTask
    {
        public void run()
        {
            throw new RuntimeException();
        }
    }
}
