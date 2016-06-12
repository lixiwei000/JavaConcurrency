package chapter03;

import java.sql.Connection;

/**
 * @author NikoBelic
 * @create 16/6/5 23:55
 */
public class ThreadLocalTest
{
    public static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue() {
            System.out.println("初始化Connection对象");
            return null;
        }
    };
    public static Connection getConnection()
    {
        return connectionHolder.get();
    }
    public static void main(String[] args)
    {
        for (int i=0; i<5; i++)
        {
            new Thread(new Runnable()
            {
                public void run() {
                    getConnection();
                    getConnection();
                }
            }).start();
        }
    }
}
