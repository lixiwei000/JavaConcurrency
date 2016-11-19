package JavaMultiThreadProgramming.chapter05;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author NikoBelic
 * @create 17/11/2016 21:44
 */
public class TimerTest1
{
    public static void main(String[] args)
    {
        System.out.println("当先时间为" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        Calendar calendar = Calendar.getInstance();
        // 1.计划时间晚于当前时间
        //calendar.add(Calendar.SECOND,10);


        // 2.计划时间早于当前时间
        calendar.set(Calendar.SECOND,calendar.get(Calendar.SECOND) - 10);
        Date runDate = calendar.getTime();
        System.out.println("计划时间为" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(runDate));

        // 3.执行任务
        MyTask task = new MyTask();
        Timer timer = new Timer();
        //Timer timer = new Timer(true); // 守护线程
        timer.schedule(task,runDate);
    }
}


class MyTask extends TimerTask
{
    @Override
    public void run()
    {
        System.out.println("My Timer Task is running..." + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}

