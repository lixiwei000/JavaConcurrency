package JavaConcurrencyInAction.chapter06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * 串行的Web服务器
 *
 * @author NikoBelic
 * @create 16/6/12 09:52
 */
public class SingleThreadWebServer
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket socket = new ServerSocket(80);
        // 单线程处理请求
        //while (true)
        //{
        //    Socket connection = socket.accept();
        //    connection.getRemoteSocketAddress();
        //}

        // 多线程处理请求
        while (true)
        {
            final Socket connection = socket.accept();
            Runnable task = new Runnable()
            {
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }
    private static void handleRequest(Socket connection)
    {
        SocketAddress remoteSocketAddress = connection.getRemoteSocketAddress();
        System.out.println("IP:" + remoteSocketAddress.toString());
    }
}
