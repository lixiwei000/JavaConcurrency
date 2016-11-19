package JavaMiddleWare;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author NikoBelic
 * @create 16/8/10 19:59
 */
public class FutureTest
{
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,2,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(5));
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        FutureTest t = new FutureTest();
        Future<HashMap> future = t.getDataFromRemote();
        HashMap hashMap = future.get();
        System.out.println("成功获取远程数据");
        System.out.println(hashMap.keySet());
        System.out.println(hashMap.values());
    }
    public Future<HashMap> getDataFromRemote()
    {
        return threadPool.submit(new Callable<HashMap>()
        {
            public HashMap call() throws Exception
            {
                HashMap map = new HashMap();
                map.put("Niki",20);
                map.put("Tom",18);
                Thread.sleep(5000);
                return map;
            }
        });
    }
}
