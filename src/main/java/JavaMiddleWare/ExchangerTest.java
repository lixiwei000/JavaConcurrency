package JavaMiddleWare;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author NikoBelic
 * @create 16/8/10 19:35
 */
public class ExchangerTest
{
    public static void main(String[] args)
    {
        final Exchanger<List<Integer>> exchanger = new Exchanger();
        new Thread(new Runnable()
        {
            public void run() {
                List<Integer> l = new ArrayList<Integer>();
                l.add(1);
                l.add(2);
                try
                {
                   l = exchanger.exchange(l);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Thread1" + l);
            }
        }).start();
        new Thread(){
            @Override
            public void run()
            {
                List<Integer> l = new ArrayList<Integer>();
                l.add(4);
                l.add(5);
                l.add(6);
                try
                {
                    l = exchanger.exchange(l);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Thread2"+l);
            }
        }.start();

    }
}
