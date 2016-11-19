package JavaMultiThreadProgramming.chapter05;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author NikoBelic
 * @create 18/11/2016 09:35
 */
public class TimerTest2
{
    public static void main(String[] args)
    {
        Timer timer = new Timer();
        MyTask2 task2 = new MyTask2();

        System.out.println("当前系统时间" + new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND) - 10);
        Date runDate = calendar.getTime();
        System.out.println("计划启动时间" + runDate);
        //timer.schedule(task2,runDate,2000); // 以任务开始时间为起点 + 周期时间 执行任务


        timer.scheduleAtFixedRate(task2,runDate,5000); // 如果计划时间早于当前时间,计划期会补上过时的任务

    }
}

class MyTask2 extends TimerTask
{
    @Override
    public void run()
    {
        System.out.println("任务被执行 " + new Date());
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("任务执行完毕" + new Date());
        System.out.println("---------------------");
    }
}
