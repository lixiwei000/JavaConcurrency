package JavaConcurrencyInAction.chapter04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 虽然加了锁,但并不是线程安全的
 *
 * @author NikoBelic
 * @create 16/6/7 21:10
 */
public class ListHelper<E>
{
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());
    //public List<E> list = new ArrayList<E>(); // 使用这个线程不安全

    public boolean putIfAbsent(E x)
    {
        synchronized (list)
        {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
    public static void main(String[] args)
    {
        for (int time = 0; time < 100; time++)
        {
            final ListHelper<Integer> lister = new ListHelper<Integer>();
            for (int i = 0; i < 1000; i++)
            {
                final int c = i;
                for (int tno = 0;tno < 10;tno++)
                {
                    new Thread(new Runnable()
                    {
                        public void run()
                        {
                            new Thread(new Runnable()
                            {
                                public void run() {
                                    lister.putIfAbsent(c);
                                }
                            }).start();

                        }
                    }).start();
                }
            }
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println("length:" + lister.list.size());
        }
    }
}
