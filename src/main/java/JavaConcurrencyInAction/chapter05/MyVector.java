package JavaConcurrencyInAction.chapter05;

import java.util.Vector;

/**
 * 列表,测试多线程并发导致的混乱
 *
 * @author NikoBelic
 * @create 16/6/8 13:00
 */
public class MyVector extends Vector
{
    public static Object getLast(MyVector list)
    {
        synchronized (list)
        {
            int lastIndex = list.size() - 1;
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return list.get(lastIndex);
        }
    }
    public static void deleteLast(MyVector list)
    {
        System.out.println("进入删除方法");
        int lastIndex = list.size() - 1;
        System.out.println("准备删除");
        list.remove(lastIndex);
        System.out.println("删除完成" + list);
    }

    public static void main(String[] args)
    {
        final MyVector list = new MyVector();
        final MyVector list2 = new MyVector();
        for (int i = 0; i < 10; i++)
        {
            list.add(i,i);
            list2.add(i,i);
        }
        new Thread(new Runnable()
        {
            public void run() {
                MyVector.deleteLast(list);
            }
        }).start();
        new Thread(new Runnable()
        {
            public void run() {
                System.out.println("获取最后一个元素:"+MyVector.getLast(list));
            }
        }).start();


    }
}
