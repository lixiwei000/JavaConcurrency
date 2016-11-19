package JavaConcurrencyInAction.chapter07;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用volatile类型的域来保存取消状态
 *
 * @author NikoBelic
 * @create 16/6/14 14:29
 */
public class PrimeGenerator implements Runnable
{
    private final List<BigInteger> primes = new ArrayList<BigInteger>();
    private volatile boolean cancelled;
    BigInteger p = BigInteger.ONE;
    public void run() {

        while (!cancelled)
        {
            synchronized (this)
            {
                p = p.nextProbablePrime();
                primes.add(p);
            }
        }
    }
    public void cancel()
    {
        cancelled = true;
    }

    public synchronized List<BigInteger> get()
    {
        return new ArrayList<BigInteger>(primes);
    }

    public static void main(String[] args) throws InterruptedException
    {
        PrimeGenerator generator = new PrimeGenerator();
        for (int i = 0; i < 10 ; i++)
        {
            new Thread(generator).start();
            Thread.sleep(10);
        }
        generator.cancel();
        List<BigInteger> primes = generator.get();
        for (BigInteger prime : primes)
        {
            System.out.println(prime);
        }

    }
}
