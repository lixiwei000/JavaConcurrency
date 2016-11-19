package JavaMultiThreadProgramming.chapter03.thread_communicate;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author NikoBelic
 * @create 15/11/2016 19:42
 */
public class PipeInOut
{
    public static void main(String[] args) throws InterruptedException, IOException
    {

        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();

        outputStream.connect(inputStream);

        InputThread inThread = new InputThread(inputStream);
        OutputThread outThread = new OutputThread(outputStream);

        outThread.start();
        Thread.sleep(2000);
        inThread.start();
    }

}


class InputThread extends Thread
{
    PipedInputStream pipedInputStream;
    public InputThread(PipedInputStream pipedInputStream)
    {
        this.pipedInputStream = pipedInputStream;
    }

    @Override
    public void run()
    {
        byte[] buff = new byte[20];
        int len = 0;
        try
        {
            len = pipedInputStream.read(buff);

            while (len != -1)
            {
                System.out.println("Read:" + new String(buff));
                len = pipedInputStream.read(buff);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            pipedInputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

class OutputThread extends Thread
{
    PipedOutputStream pipedOutputStream;
    public OutputThread(PipedOutputStream pipedOutputStream)
    {
        this.pipedOutputStream = pipedOutputStream;
    }

    @Override
    public void run()
    {
        System.out.print("Write:");
        for (int i = 0; i < 280; i++)
        {
            try
            {
                pipedOutputStream.write((i + ",").getBytes());
                System.out.print(i);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("");
        try
        {
            pipedOutputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}