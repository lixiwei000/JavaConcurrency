package JavaMultiThreadProgramming.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NikoBelic
 * @create 09/11/2016 13:57
 */
public class SuspendResumeTest
{

    static class MyThread extends Thread
    {
        int  c = 0;
        public void run()
        {
            while (true)
            {
                c++;
                //System.out.println(c);
            }
        }
        public int getC()
        {
            return c;
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        List<MyThread> threadList = new ArrayList();
        for (int i = 0; i < 10; i++)
        {
            MyThread t = new MyThread();
            t.start();
            threadList.add(t);
        }

        for (MyThread t : threadList)
        {
            t.suspend();
        }
        for (MyThread t : threadList)
        {
            System.out.println(t.getName() + "-" + t.getC());
        }
        Thread.sleep(1000);
        System.out.println("");
        for (MyThread t : threadList)
        {
            System.out.println(t.getName() + "-" + t.getC());
            t.resume();
        }
        System.out.println("----------------------");
        for (MyThread t : threadList)
        {
            t.suspend();
        }
        for (MyThread t : threadList)
        {
            System.out.println(t.getName() + "-" + t.getC());
        }
    }
}
