package JavaConcurrencyInAction.chapter04;

/**
 * @author NikoBelic
 * @create 16/6/7 20:42
 */
public class DiffLockTest
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            new Thread(new Runnable()
            {
                public void run() {
                    //Singleton.getInstance().show();
                    new Multiple().show();
                }
            }).start();
        }
    }
}
