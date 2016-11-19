package JavaMultiThreadProgramming.chapter07;

/**
 * @author NikoBelic
 * @create 18/11/2016 17:19
 */
public class RecurGroupTest
{
    public static void main(String[] args)
    {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup groupA = new ThreadGroup(mainGroup,"groupA");
        Runnable runnable = new Runnable()
        {
            public void run() {
                System.out.println("method running...");
                try
                {
                    Thread.sleep(10000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        ThreadGroup groupB = new ThreadGroup(groupA,"groupB");

        ThreadGroup groupX = new ThreadGroup(mainGroup,"groupX");

        System.out.println("mainThread 中线程组数量:" + Thread.currentThread().getThreadGroup().activeGroupCount());

        ThreadGroup[] listGroup1 = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];

        Thread.currentThread().getThreadGroup().enumerate(listGroup1,true);// 主线程组中的线程组递归复制到listGroup1

        for (ThreadGroup threadGroup : listGroup1)
        {
            if (threadGroup != null)
            {
                System.out.println("当前线程组名称:" + threadGroup.getName());
            }
        }

        System.out.println("--------------------");

        ThreadGroup[] listGroup2 = new ThreadGroup[Thread.currentThread().getThreadGroup().activeGroupCount()];

        Thread.currentThread().getThreadGroup().enumerate(listGroup2,false); // 非递归复制,只复制直接子线程组

        for (ThreadGroup threadGroup : listGroup2)
        {
            if (threadGroup != null)
            {
                System.out.println("当前线程组名称:" + threadGroup.getName());
            }
        }


    }
}
